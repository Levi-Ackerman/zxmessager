package top.lizhengxian.signal.home;


import android.util.Log;

import top.lizhengxian.event_lib.BaseController;
import top.lizhengxian.event_lib.Subscribe;
import top.lizhengxian.signal.base.ID;

public class HomeController extends BaseController{
    @Subscribe(ID.START)
    public void start(){
        Log.e("lee..","sha mo");
        pushWindow(new HomeWindow());
    }
}
