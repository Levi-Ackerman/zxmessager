package top.lizhengxian.event_lib;


import java.lang.reflect.Method;
import java.util.Map;

public class ZxMessager {
    private static class Holder {
        private static final ZxMessager INSTANCE = new ZxMessager();
    }

    private ZxMessager() {
    }

    private Map<Integer,DescriptionInfo> mDescInfo;
    private Map<Integer,Method> mMethod;

    public static void installContact(IContacts contacts) {
        Holder.INSTANCE.mDescInfo = contacts.getContactsMap();
    }

    public static void post(int id, Object data) {
        DescriptionInfo info = Holder.INSTANCE.mDescInfo.get(id);
        try {
            Class ownClass = Class.forName(info.className);
            Class argClass = Class.forName(info.paramName);
            Method method = ownClass.getMethod(info.methodName,argClass);
            method.invoke(ownClass.newInstance(),data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
