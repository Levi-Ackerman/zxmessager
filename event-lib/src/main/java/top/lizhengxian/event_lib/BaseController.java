package top.lizhengxian.event_lib;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import top.lizhengxian.event_lib.window.IUICallback;
import top.lizhengxian.event_lib.window.Window;

public abstract class BaseController implements IUICallback{
    protected Activity mActivity;
    private Window mPreWindow;

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    protected void pushWindow(Window window) {
        pushWindow(window, true);
    }

    protected void pushWindow(Window window, boolean rememberPre) {
        FragmentTransaction transaction = mActivity.getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, window);
        if (rememberPre) {
            String tag = mPreWindow == null?null:mPreWindow.getStackTag();
            transaction.addToBackStack(tag);
            mPreWindow =window;
        }
        transaction.commit();
    }

    protected boolean popWindow() {
        FragmentManager manager = mActivity.getFragmentManager();
        if (manager.getBackStackEntryCount() == 0) {
            mActivity.finish();
            System.exit(0);
            return false;
        } else {
            manager.popBackStack();
            return true;
        }
    }

    public Activity getContext(){
        return mActivity;
    }

    @Override
    public boolean onBackPressed() {
        popWindow();
        return true;
    }
}
