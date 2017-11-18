package top.lizhengxian.signal.home;

import android.view.View;
import android.widget.TextView;

import top.lizhengxian.event_lib.window.Window;

/**
 * Created by zhengxianlzx on 17-11-18.
 */

public class HomeWindow extends Window {
    @Override
    public View onCreateContent() {
        TextView textView = new TextView(getContext());
        textView.setText("Hello world!");
        return textView;
    }
}
