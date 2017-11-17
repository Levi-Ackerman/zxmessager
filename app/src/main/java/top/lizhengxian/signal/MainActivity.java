package top.lizhengxian.signal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

import top.lizhengxian.event_lib.Subscribe;

public class MainActivity extends AppCompatActivity {
    @Override
    @Subscribe(0)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add("5");
    }

//    @Subscribe(1)
    @Override
    protected void onResume() {
        super.onResume();
    }

//    @Subscribe(2)
    @Override
    protected void onStart() {
        super.onStart();
    }
}
