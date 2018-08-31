package com.lm.lminject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lm.annotation.inject.Inject;
import com.lm.annotation.inject.Injectors;
import com.lm.annotation.provide.Provider;
import com.lm.annotation.provide.Providers;

public class MainActivity extends AppCompatActivity {

    @Inject(provideName = "userName")
    String userName;
    @Inject(provideName = "pwd")
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Injectors.put(MainActivity.class, new MainActivityInjector());
        Providers.put(MainProvide.class, new MainProvideProvider());

        Injectors.get(MainActivity.class).inject(this, new MainProvide());

        TextView tv = findViewById(R.id.tv);
        tv.setText("userName = " + userName + "\n" + "pwd = " + pwd);
    }
}
