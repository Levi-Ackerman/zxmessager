package top.lizhengxian.event_lib.zx;


import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import top.lizhengxian.event_lib.Config;
import top.lizhengxian.event_lib.IContacts;
import top.lizhengxian.event_lib.anno.Thread;
import top.lizhengxian.event_lib.interf.FieldRunnable;

public class ZxMessager {

    private static class Holder {
        private static final ZxMessager INSTANCE = new ZxMessager();
    }

    private ZxMessager() {
        mConfig = new Config();
        mParser = new ZxMethodParser();
        HandlerThread thread = new HandlerThread("ZxMessager");
        thread.start();
        mMsgHandler = new Handler(thread.getLooper());
    }

    private static ZxMessager getInstance() {
        return Holder.INSTANCE;
    }

    private Config mConfig;
    private ZxMethodParser mParser;
    private Handler mMsgHandler;

    public static void installContact(IContacts contacts) {
        getInstance().mParser.withContact(contacts);
    }

    public static void withActivity(Activity activity) {
        getInstance().mConfig.setActivity(activity);
        getInstance().mParser.withActivity(activity);
    }

    public static Object post(int id) {
        return post(id, null);
    }

    public static Object post(int id, Object data) {
        final Method method = getInstance().mParser.getMethod(id);
        final Object ownObj = getInstance().mParser.getController(id);
        Thread threadType = getInstance().mParser.getThreadType(id);
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
        ZxExecutor.execute(threadType, runnable);
        return runnable.getData();
    }
}
