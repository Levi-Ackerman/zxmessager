package top.lizhengxian.event_lib;


import android.app.Activity;

import top.lizhengxian.event_lib.window.Window;

public abstract class BaseController {
    private Activity mActivity;

    public void setActivity(Activity activity){
        mActivity = activity;
    }

    public void pushWindow(Window window) {
        mActivity.getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, window)
                .addToBackStack(null)
                .commit();
    }

    public void popWindow() {
        mActivity.getFragmentManager().popBackStack();
    }
}
