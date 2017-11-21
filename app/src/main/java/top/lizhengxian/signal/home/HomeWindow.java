package top.lizhengxian.signal.home;

import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import top.lizhengxian.event_lib.window.IUICallback;
import top.lizhengxian.event_lib.window.Window;
import top.lizhengxian.signal.R;

/**
 * Created by zhengxianlzx on 17-11-18.
 */

public class HomeWindow extends Window {

    public HomeWindow(IUICallback callback) {
        super(callback);
    }

    @Override
    public View onCreateContent() {
        TextView textView = new TextView(getContext());
        textView.setText("Hello world!");
        return textView;
    }

    @Override
    public View onCreateTitle() {
        Toolbar toolbar = new Toolbar(getContext());
        toolbar.setTitle(R.string.app_name);
        toolbar.setBackgroundResource(R.color.colorPrimary);
        return toolbar;
    }
}
