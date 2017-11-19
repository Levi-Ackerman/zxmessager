package top.lizhengxian.event_lib.anno;


public @interface Receive {
    int id();
    ThreadMode threadMode() default ThreadMode.SYNC;
}
