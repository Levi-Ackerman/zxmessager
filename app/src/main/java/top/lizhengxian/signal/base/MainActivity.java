package top.lizhengxian.signal.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import top.lizhengxian.event_lib.window.Window;
import top.lizhengxian.event_lib.zx.ZxMessager;
import top.lizhengxian.signal.BuildConfig;
import top.lizhengxian.signal.Contacts;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZxMessager.init(this, new Contacts());
        ZxMessager.setDebuggable(BuildConfig.DEBUG);
        ZxMessager.post(ID.START);
        Log.e("lee..","hello");
    }

    @Override
    public void onBackPressed() {
        Window curWindow = (Window) getFragmentManager().findFragmentById(android.R.id.content);
        curWindow.onBackPressed();
    }
}
