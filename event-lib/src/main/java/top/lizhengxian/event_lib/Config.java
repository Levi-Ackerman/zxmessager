package top.lizhengxian.event_lib;


import android.app.Activity;

public class Config {
    private Activity mBaseActivity;

    public Activity getBaseActivity() {
        return mBaseActivity;
    }

    public void setActivity(Activity baseActivity) {
        mBaseActivity = baseActivity;
    }
}
