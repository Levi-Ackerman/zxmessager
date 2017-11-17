package top.lizhengxian.signal;


import android.util.Log;

import top.lizhengxian.event_lib.BaseController;
import top.lizhengxian.event_lib.Subscribe;

public class Controller extends BaseController{
    @Subscribe(1)
    public void print(String name){
        Log.e("lee..",name);
    }
}
