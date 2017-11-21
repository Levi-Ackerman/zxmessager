package top.lizhengxian.event_lib.window;


import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import top.lizhengxian.event_lib.zx.ZxMessager;

public abstract class Window extends LinearLayout implements IWindow {
    private View mContentView;
    private View mTitleView;
    private final String mId;
    private IUICallback mCallback;

    public abstract View onCreateContent();

    public abstract View onCreateTitle();

    protected Window(IUICallback callback) {
        super(ZxMessager.getContext());
        this.mCallback = callback;
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if (mCallback!=null){
                return mCallback.onBackPressed();
            }
        }
        return false;
    }
}
