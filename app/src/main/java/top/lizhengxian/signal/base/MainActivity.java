package top.lizhengxian.signal.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import top.lizhengxian.event_lib.ZxMessager;
import top.lizhengxian.signal.Contacts;
import top.lizhengxian.signal.base.ID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZxMessager.installContact(new Contacts());
        ZxMessager.withActivity(this);
        ZxMessager.post(ID.START,"Hello");
    }
}
