package top.lizhengxian.event_lib;


import android.app.Activity;
import android.util.SparseArray;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ZxMessager {

    private static class Holder {
        private static final ZxMessager INSTANCE = new ZxMessager();
    }

    private ZxMessager() {
        mDescInfo = new HashMap<>();
        mMethod = new SparseArray<>();
        mClass = new HashMap<>();
        mControllers = new SparseArray<>();
        mConfig = new Config();
    }

    private Map<Integer, DescriptionInfo> mDescInfo;
    private SparseArray<Method> mMethod;
    private SparseArray<BaseController> mControllers;
    private Map<String, Class> mClass;
    private Config mConfig;

    public static void installContact(IContacts contacts) {
        getInstance().mDescInfo.putAll(contacts.getContactsMap());
    }

    public static void withActivity(Activity activity){
        getInstance().mConfig.setActivity(activity);
    }

    private static ZxMessager getInstance() {
        return Holder.INSTANCE;
    }

    public static void post(int id, Object data) {
        Method method = getInstance().getMethod(id);
        Object ownObj = getInstance().getController(id);
        try {
            method.invoke(ownObj, data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Method getMethod(int id) {
        Method method = mMethod.get(id);
        if (method == null) {
            DescriptionInfo info = mDescInfo.get(id);
            try {
                Class ownClass = getClass(info.className);
                Class argClass = getClass(info.paramName);
                method = ownClass.getMethod(info.methodName, argClass);
                mMethod.put(id,method);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return method;
    }

    private BaseController getController(int id) {
        BaseController controller = mControllers.get(id);
        if (controller == null) {
            try {
                controller = (BaseController) getClass(mDescInfo.get(id).className).newInstance();
                controller.setActivity(mConfig.getBaseActivity());
                mControllers.put(id, controller);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return controller;
    }

    private Class getClass(String className) {
        Class clazz = mClass.get(className);
        if (clazz == null) {
            try {
                clazz = Class.forName(className);
                mClass.put(className, clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return clazz;
    }
}
