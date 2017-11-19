package top.lizhengxian.signal.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import top.lizhengxian.event_lib.zx.ZxMessager;
import top.lizhengxian.signal.Contacts;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZxMessager.init(this, new Contacts());
        ZxMessager.post(ID.START,1);
    }
}
