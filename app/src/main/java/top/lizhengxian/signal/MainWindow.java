package top.lizhengxian.signal;

import android.view.View;
import android.widget.TextView;

import top.lizhengxian.event_lib.window.Window;

/**
 * Created by zhengxianlzx on 17-11-18.
 */

public class MainWindow extends Window {
    @Override
    protected View onCreateContent() {
        TextView textView = new TextView(getContext());
        textView.setText("Hello world!");
        return textView;
    }
}
