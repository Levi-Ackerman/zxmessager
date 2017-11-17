package top.lizhengxian.event_lib.window;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Window extends Fragment {
    private View mContentView;
    protected abstract View onCreateContent();

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mContentView == null){
            mContentView = onCreateContent();
        }
        return mContentView;
    }

    public View getmContentView() {
        return mContentView;
    }

    protected Context getContext(){
        return getActivity();
    }
}