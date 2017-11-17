package top.lizhengxian.signal;


import android.app.Application;

import top.lizhengxian.event_lib.ZxMessager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZxMessager.installContact(new Contacts());
    }
}
