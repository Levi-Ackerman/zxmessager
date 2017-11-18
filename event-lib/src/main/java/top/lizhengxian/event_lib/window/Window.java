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
    public abstract View onCreateContent();
    public abstract View onCreateTitle();

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mRootLayout == null) {
            mTitleView = onCreateTitle();
            mContentView = onCreateContent();
            mRootLayout = new LinearLayout(getContext());
            mRootLayout.setOrientation(LinearLayout.VERTICAL);
            if (mTitleView!=null){
                mRootLayout.addView(mTitleView);
            }
            if (mContentView!=null){
                mRootLayout.addView(mContentView);
            }
        }
        return mRootLayout;
    }

    public View getContentView() {
        return mContentView;
    }

    public View getTitleView(){
        return mTitleView;
    }

    protected Context getContext(){
        return getActivity();
    }
}
