package top.lizhengxian.event_lib;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.Stack;

import top.lizhengxian.event_lib.window.Window;

public abstract class BaseController {
    private Activity mActivity;
    private static Stack<Window> mWindowStack = new Stack<>();

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    protected void pushWindow(Window window) {
        pushWindow(window, true);
    }

    protected void pushWindow(Window window, boolean rememberPre) {
        ViewGroup content = (ViewGroup) mActivity.findViewById(android.R.id.content);
        content.addView(window, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (rememberPre) {
            mWindowStack.push(window);
        }
    }

    protected boolean popWindow() {
        if (mWindowStack.empty()) {
            return false;
        }
        Window window = mWindowStack.pop();
        ViewGroup content = (ViewGroup) mActivity.findViewById(android.R.id.content);
        content.removeView(window);
        return true;
    }
}
