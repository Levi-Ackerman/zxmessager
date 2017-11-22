package top.lizhengxian.signal.home;


import android.util.Log;

import top.lizhengxian.event_lib.BaseController;
import top.lizhengxian.event_lib.anno.Subscribe;
import top.lizhengxian.event_lib.window.Window;
import top.lizhengxian.signal.base.ID;

public class HomeController extends BaseController {
    private Window mWindow;

    @Subscribe(id = ID.START)
    public BaseController start() {
        Log.e("lee..", "sha mo");
        Window window = new HomeWindow();
        window.setCallback(this);
        pushWindow(window,false);
        return this;
    }

    public void setHomeWindow(Window window) {
        this.mWindow = window;
    }
}
