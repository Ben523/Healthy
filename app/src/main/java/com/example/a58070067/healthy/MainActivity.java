package com.example.a58070067.healthy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.fragment_login);
        //setContentView(R.layout.fragment_register);
        //setContentView(R.layout.fragment_bmi);
        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view,new LoginFragment())
                    .commit();
        }
    }
}
