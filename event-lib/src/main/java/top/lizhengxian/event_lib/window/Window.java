package top.lizhengxian.event_lib.window;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import top.lizhengxian.event_lib.zx.ZxMessager;

public abstract class Window extends LinearLayout implements IWindow {
    private View mContentView;
    private View mTitleView;
    private final String mId;

    public abstract View onCreateContent();

    public abstract View onCreateTitle();

    protected Window() {
        super(ZxMessager.getContext());
        mId = WindowUtil.genStackTag();
        mTitleView = onCreateTitle();
        mContentView = onCreateContent();
        setOrientation(LinearLayout.VERTICAL);
        if (mTitleView != null) {
            addView(mTitleView);
        }
        if (mContentView != null) {
            addView(mContentView);
        }
    }

    public View getContentView() {
        return mContentView;
    }

    public View getTitleView() {
        return mTitleView;
    }

    @Override
    public String getStackTag() {
        return mId;
    }
}
