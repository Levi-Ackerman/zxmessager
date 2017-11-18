package top.lizhengxian.event_lib.zx;


import android.app.Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import top.lizhengxian.event_lib.Config;
import top.lizhengxian.event_lib.IContacts;

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

    private Config mConfig;
    private ZxMethodParser mParser;

    public static void installContact(IContacts contacts) {
        getInstance().mParser.withContact(contacts);
    }

    public static void withActivity(Activity activity){
        getInstance().mConfig.setActivity(activity);
        getInstance().mParser.withActivity(activity);
    }


    public static void post(int id, Object data) {
        Method method = getInstance().mParser.getMethod(id);
        Object ownObj = getInstance().mParser.getController(id);
        try {
            method.invoke(ownObj, data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
