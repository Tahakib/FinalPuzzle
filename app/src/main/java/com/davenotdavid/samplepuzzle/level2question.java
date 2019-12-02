package com.davenotdavid.samplepuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class level2question extends AppCompatActivity {

    public static final String Extra_text="Ext";
    private Button button;
    private Button nextquestion;
    private EditText editText;
    private TextView questions;
    int checkasked=0;
    private String checkans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2question);

        button=(Button)findViewById(R.id.button2);
        questions=(TextView)findViewById(R.id.textView3);
        nextquestion=(Button)findViewById(R.id.otherquestion);
        editText=(EditText)findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result=editText.getText().toString();
                if(checkasked==0)
                {
                    if (result.equals("Adolf Hitler")) {
                        checkans = "True";
                        Toast.makeText(getApplicationContext(), "Correct Answer, Time added!", Toast.LENGTH_SHORT).show();
                        opentrue();
                    }

                    else if(result.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please enter an answer :/", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        checkans="False";
                        Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                        openfalse();
                    }
                }
                else
                {
                    if (result.equals("Germany")) {
                        checkans = "True";
                        Toast.makeText(getApplicationContext(), "Correct Answer, Time added! :)", Toast.LENGTH_SHORT).show();
                        opentrue();
                    }

                    else if(result.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please enter an answer :/", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        checkans="False";
                        Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                        openfalse();
                    }

                }

            }
        });

        nextquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkasked==0)
                {
                    questions.setText("Which country started world war 2?");
                    checkasked=1;
                }
                else if(checkasked==1)
                {
                    questions.setText("What was the full name of Hitler?");
                    checkasked=0;
                }
            }
        });
    }
    public void opentrue()
    {
        Intent intent=new Intent(this, level2.class);
        intent.putExtra(Extra_text,checkans);
        setResult(RESULT_OK,intent);
        finish();
        //(intent,1);
    }

    public void openfalse()
    {
        Intent intent=new Intent(this, level2.class);
        intent.putExtra(Extra_text,checkans);
        setResult(RESULT_CANCELED,intent);
        finish();
        //(intent,1);
    }
}
