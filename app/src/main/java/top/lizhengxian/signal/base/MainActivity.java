package top.lizhengxian.signal.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import top.lizhengxian.event_lib.zx.ZxMessager;
import top.lizhengxian.signal.BuildConfig;
import top.lizhengxian.signal.Contacts;
import top.lizhengxian.signal.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ZxMessager.init(this, new Contacts());
        ZxMessager.setDebuggable(BuildConfig.DEBUG);
        ZxMessager.post(ID.START,1);
        Log.e("lee..","hello");
    }

    @Override
    public void onBackPressed() {
        ZxMessager.post(ID.BACK_PRESSED);
    }
}
