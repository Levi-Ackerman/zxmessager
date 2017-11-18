package top.lizhengxian.event_lib.zx;


import android.app.Activity;
import android.util.SparseArray;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import top.lizhengxian.event_lib.BaseController;
import top.lizhengxian.event_lib.DescriptionInfo;
import top.lizhengxian.event_lib.IContacts;

class ZxMethodParser {

    private Map<String, Class> mClass;
    private Map<Integer, DescriptionInfo> mDescInfo;
    private SparseArray<Method> mMethod;
    private SparseArray<BaseController> mControllers;
    private Activity mActivity;

    ZxMethodParser() {
        mClass = new HashMap<>();
        mDescInfo = new HashMap<>();
        mMethod = new SparseArray<>();
        mControllers = new SparseArray<>();
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

    void withContact(IContacts contacts) {
        mDescInfo.putAll(contacts.getContactsMap());
    }
    void withActivity(Activity activity){
        mActivity = activity;
    }

    Method getMethod(int id) {
        Method method = mMethod.get(id);
        if (method == null) {
            DescriptionInfo info = mDescInfo.get(id);
            try {
                Class ownClass = getClass(info.className);
                Class argClass = getClass(info.paramName);
                method = ownClass.getMethod(info.methodName, argClass);
                mMethod.put(id, method);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return method;
    }

    BaseController getController(int id) {
        BaseController controller = mControllers.get(id);
        if (controller == null) {
            try {
                if (mActivity == null){
                    throw new RuntimeException("Set the activity instance first!");
                }
                controller = (BaseController) getClass(mDescInfo.get(id).className).newInstance();
                controller.setActivity(mActivity);
                mControllers.put(id, controller);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return controller;
    }

}
