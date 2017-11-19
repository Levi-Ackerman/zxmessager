package top.lizhengxian.event_lib.zx;


import android.app.Activity;
import android.util.SparseArray;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import top.lizhengxian.event_lib.BaseController;
import top.lizhengxian.event_lib.Description;
import top.lizhengxian.event_lib.IContacts;
import top.lizhengxian.event_lib.anno.ThreadMode;

class ZxMethodParser {

    private Map<Integer, Description> mDesc;
    private SparseArray<Method> mMethod;
    private SparseArray<BaseController> mControllers;
    private Activity mActivity;

    ZxMethodParser() {
        mDesc = new HashMap<>();
        mMethod = new SparseArray<>();
        mControllers = new SparseArray<>();
    }

    void withContact(IContacts contacts) {
        mDesc.putAll(contacts.getContactsMap());
    }
    void withActivity(Activity activity){
        mActivity = activity;
    }

    Method getMethod(int id) {
        Method method = mMethod.get(id);
        if (method == null) {
            Description desc = mDesc.get(id);
            try {
                Class ownClass = desc.ownClass;
                Class argClass = desc.paramClass;
                if (argClass == null){
                    method = ownClass.getMethod(desc.methodName);
                }else {
                    method = ownClass.getMethod(desc.methodName, argClass);
                }
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
                controller = (BaseController) mDesc.get(id).ownClass.newInstance();
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

    public ThreadMode getThreadType(int id) {
        return mDesc.get(id).mThreadMode;
    }
}
