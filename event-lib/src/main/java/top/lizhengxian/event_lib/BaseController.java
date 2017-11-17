package top.lizhengxian.event_lib;


import android.app.Activity;

import top.lizhengxian.event_lib.window.Window;

public abstract class BaseController {
    private Config mConfig = new Config();

    public void setActivity(Activity activity){
        mConfig.setActivity(activity);
    }

    public void pushWindow(Window window) {
        mConfig.getBaseActivity().getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, window)
                .addToBackStack(null)
                .commit();
    }

    public void popWindow() {
        mConfig.getBaseActivity().getFragmentManager().popBackStack();
    }
}
