package com.lm.lminject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lm.annotation.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject(provideName = "userName")
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);
        tv.setText("userName = " + userName);
    }
}
