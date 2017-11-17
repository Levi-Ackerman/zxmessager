package top.lizhengxian.signal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.lizhengxian.event_lib.ZxMessager;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn) protected Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    protected void onClick(View view){
        ZxMessager.post(1,"Hello world!");
    }
}
