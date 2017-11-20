package top.lizhengxian.signal.home;


import android.util.Log;

import top.lizhengxian.event_lib.BaseController;
import top.lizhengxian.event_lib.anno.Subscribe;
import top.lizhengxian.event_lib.anno.ThreadMode;
import top.lizhengxian.signal.base.ID;

public class HomeController extends BaseController {
    @Subscribe(id = ID.START)
    public String start(int i) {
        Log.e("lee..", "sha mo");
        pushWindow(new HomeWindow(),false);
        return "Success";
    }

    @Subscribe(id = ID.BACK_PRESSED)
    public void exit(){
        System.exit(0);
    }
}
