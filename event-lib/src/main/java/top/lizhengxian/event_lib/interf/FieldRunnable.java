package top.lizhengxian.event_lib.interf;


public abstract class FieldRunnable implements Runnable {
    public Object getData() {
        return mData;
    }

    protected Object mData;
}
