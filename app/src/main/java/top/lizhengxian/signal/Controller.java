package top.lizhengxian.signal;


import android.util.Log;

import top.lizhengxian.event_lib.BaseController;
import top.lizhengxian.event_lib.Subscribe;

public class Controller extends BaseController{
    @Subscribe(ID.START)
    public void start(String name){
        Log.e("lee..",name);
        pushWindow(new MainWindow());
    }
}
