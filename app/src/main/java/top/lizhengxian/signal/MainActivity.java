package top.lizhengxian.signal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import top.lizhengxian.event_lib.ZxMessager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZxMessager.post(1,"Hello world!");
    }
}
