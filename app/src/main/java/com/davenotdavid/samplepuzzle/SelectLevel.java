package com.davenotdavid.samplepuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLevel extends AppCompatActivity {

    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        button1=(Button)findViewById(R.id.button3);
        button2=(Button)findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlevel1();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlevel2();
            }
        });

    }


    public void openlevel1()
    {
        Intent intent=new Intent(this, homeActivity.class);
        startActivity(intent);
    }

    public void openlevel2()
    {
        Intent intent=new Intent(this, level2.class);
        startActivity(intent);
    }

}
