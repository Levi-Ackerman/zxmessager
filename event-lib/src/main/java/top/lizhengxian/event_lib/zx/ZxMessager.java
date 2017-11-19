package top.lizhengxian.event_lib.zx;


import android.app.Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import top.lizhengxian.event_lib.Config;
import top.lizhengxian.event_lib.IContacts;
import top.lizhengxian.event_lib.anno.ThreadMode;
import top.lizhengxian.event_lib.interf.FieldRunnable;

public class ZxMessager {

    private static class Holder {
        private static final ZxMessager INSTANCE = new ZxMessager();
    }

    private ZxMessager() {
        mConfig = new Config();
        mParser = new ZxMethodParser();
    }

    private static ZxMessager getInstance() {
        return Holder.INSTANCE;
    }

    private boolean mDebuggable = false;
    public static boolean debuggable(){
        return getInstance().mDebuggable;
    }
    public static void setDebuggable(boolean debuggable){
        getInstance().mDebuggable = debuggable;
    }

    private Config mConfig;
    private ZxMethodParser mParser;

    public static void init(Activity activity, IContacts contacts) {
        getInstance().mParser.withContact(contacts);
        getInstance().mConfig.setActivity(activity);
        getInstance().mParser.withActivity(activity);
    }

    public static Object post(int id) {
        return post(id, null);
    }

    public static Object post(int id, Object data) {
        final Method method = getInstance().mParser.getMethod(id);
        final Object ownObj = getInstance().mParser.getController(id);
        ThreadMode threadMode = getInstance().mParser.getThreadType(id);
        FieldRunnable runnable = new FieldRunnable() {
            @Override
            public void run() {
                try {
                    if (data == null) {
                        this.mData = method.invoke(ownObj);
                    } else {
                        this.mData = method.invoke(ownObj, data);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        };
        ZxExecutor.execute(threadMode, runnable);
        return runnable.getData();
    }
}
