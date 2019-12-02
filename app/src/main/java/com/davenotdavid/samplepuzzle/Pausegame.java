package com.davenotdavid.samplepuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Pausegame extends AppCompatActivity {

    public static final String Extra_text="Ext";
    private Button conti;
    private EditText editText;
    private TextView questions;
    int checkasked=0;
    private String checkans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pausegame);

        conti=(Button)findViewById(R.id.continuegame);


        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openfalse();
            }
        });

    }


    public void openfalse()
    {
        Intent intent=new Intent(this, homeActivity.class);
        intent.putExtra(Extra_text,checkans);
        setResult(RESULT_CANCELED,intent);
        finish();
    }


}
