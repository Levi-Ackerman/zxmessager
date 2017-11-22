package top.lizhengxian.event_lib.window;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public abstract class Window extends Fragment implements IWindow {
    private View mContentView;
    private View mTitleView;
    private LinearLayout mRootLayout;
    private final String mId;
    private IUICallback mCallback;

    public abstract View onCreateContent();

    public abstract View onCreateTitle();

    protected Window() {
        super();
        mId = WindowUtil.genStackTag();
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mRootLayout == null) {
            mTitleView = onCreateTitle();
            mContentView = onCreateContent();
            mRootLayout = new LinearLayout(getContext());
            mRootLayout.setOrientation(LinearLayout.VERTICAL);
            if (mTitleView != null) {
                mRootLayout.addView(mTitleView);
            }
            if (mContentView != null) {
                mRootLayout.addView(mContentView);
            }
        }
        return mRootLayout;
    }

    public View getContentView() {
        return mContentView;
    }

    public View getTitleView() {
        return mTitleView;
    }

    protected Context getContext() {
        return getActivity();
    }

    @Override
    public String getStackTag() {
        return mId;
    }

    public void setCallback(IUICallback callback) {
        mCallback = callback;
    }

    public void onBackPressed() {
        if (mCallback!=null){
            mCallback.onBackPressed();
        }
    }
}
