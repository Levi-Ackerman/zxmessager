package top.lizhengxian.signal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import top.lizhengxian.event_lib.Subscribe;

public class MainActivity extends AppCompatActivity {
    @Override
    @Subscribe(0)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
