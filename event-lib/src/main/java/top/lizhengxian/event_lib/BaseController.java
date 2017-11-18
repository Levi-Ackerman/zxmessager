package top.lizhengxian.event_lib;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import top.lizhengxian.event_lib.window.Window;

public abstract class BaseController {
    private Activity mActivity;

    public void setActivity(Activity activity){
        mActivity = activity;
    }

    protected void pushWindow(Window window){
        mActivity.getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, window)
                .addToBackStack(window.getStackTag())
                .commit();
    }

    protected boolean popWindow() {
        FragmentManager manager = mActivity.getFragmentManager();
        if (manager.getBackStackEntryCount() == 0){
            return false;
        }else{
            manager.popBackStack();
            return true;
        }
    }
}
