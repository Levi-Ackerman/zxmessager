package top.lizhengxian.event_lib;


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
        mOwnObj = new SparseArray<>();
    }

    private Map<Integer, DescriptionInfo> mDescInfo;
    private SparseArray<Method> mMethod;
    private SparseArray<Object> mOwnObj;
    private Map<String, Class> mClass;

    public static void installContact(IContacts contacts) {
        getInstance().mDescInfo.putAll(contacts.getContactsMap());
    }

    private static ZxMessager getInstance() {
        return Holder.INSTANCE;
    }

    public static void post(int id, Object data) {
        Method method = getInstance().getMethod(id);
        Object ownObj = getInstance().getOwnObj(id);
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

    private Object getOwnObj(int id) {
        Object ownObj = mOwnObj.get(id);
        if (ownObj == null) {
            try {
                ownObj = getClass(mDescInfo.get(id).className).newInstance();
                mOwnObj.put(id, ownObj);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return ownObj;
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
