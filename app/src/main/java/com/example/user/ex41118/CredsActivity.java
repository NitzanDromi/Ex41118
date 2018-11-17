package com.example.user.ex41118;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CredsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creds);
    }

    public void return1(View view) {
        Intent t=new Intent(this, MainActivity.class);
        startActivity(t);
    }
}
