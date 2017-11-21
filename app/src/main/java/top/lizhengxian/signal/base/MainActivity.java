package top.lizhengxian.signal.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import top.lizhengxian.event_lib.window.Window;
import top.lizhengxian.event_lib.zx.ZxMessager;
import top.lizhengxian.signal.BuildConfig;
import top.lizhengxian.signal.Contacts;
import top.lizhengxian.signal.home.HomeController;
import top.lizhengxian.signal.home.HomeWindow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZxMessager.init(this, new Contacts());
        ZxMessager.setDebuggable(BuildConfig.DEBUG);
        HomeController controller = (HomeController) ZxMessager.post(ID.START);
//        Window window = new HomeWindow(controller);
//        controller.setHomeWindow(window);
//        setContentView(window);
        Log.e("lee..","hello");
    }
}
