package com.davenotdavid.samplepuzzle;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button instruct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button=(Button)findViewById(R.id.button1);
        instruct=(Button)findViewById(R.id.instruction);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });
        instruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openinstructions();
            }
        });
    }

    public void openinstructions()
    {
        Intent intent=new Intent(this, Instruction.class);
        startActivity(intent);
    }

    public void openActivity1()
    {
        Intent intent=new Intent(this, SelectLevel.class);
        startActivity(intent);
    }
}
