package com.davenotdavid.samplepuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class question extends AppCompatActivity {

    public static final String Extra_text="Ext";
    private Button button;
    private Button nextquestion;
    private EditText editText;
    private TextView questions;
    int checkasked=0;
    int checkans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        button=(Button)findViewById(R.id.button2);
        questions=(TextView)findViewById(R.id.textView3);
        nextquestion=(Button)findViewById(R.id.otherquestion);
        editText=(EditText)findViewById(R.id.editText);
        //String result=editText.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fresult=editText.getText().toString();
                if(checkasked==0)
                {
                    if (fresult.equals("1939")) {
                        Toast.makeText(getApplicationContext(), "Correct Answer, Time added!", Toast.LENGTH_SHORT).show();
                        opentrue();
                    }

                    else if(fresult.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please enter an answer :/", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                        openfalse();
                    }
                }
                else
                {
                    if (fresult.equals("1942")) {
                        Toast.makeText(getApplicationContext(), "Correct Answer, Time added! :)", Toast.LENGTH_SHORT).show();
                        opentrue();
                    }

                    else if(fresult.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please enter an answer :/", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
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
                    questions.setText("When was battle of Stalingrad?");
                    checkasked=1;
                }
                else if(checkasked==1)
                {
                    questions.setText("When did world war 2 start?");
                    checkasked=0;
                }
            }
        });
    }

    public void opentrue()
    {
        Intent intent=new Intent(this, homeActivity.class);
        intent.putExtra(Extra_text,checkans);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void openfalse()
    {
        Intent intent=new Intent(this, homeActivity.class);
        intent.putExtra(Extra_text,checkans);
        setResult(RESULT_CANCELED,intent);
        finish();
    }

}
