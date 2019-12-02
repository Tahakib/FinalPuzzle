package com.davenotdavid.samplepuzzle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class level2 extends AppCompatActivity {

    private TextView mTextViewCountDown;
    private long START_TIME_IN_MILLIS = 60000;
    private boolean mTimerRunning;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private static Gesture2 mGridView;

    private SharedPreferences mPreference;
    private SharedPreferences.Editor mEditor;
    private TextView scores;
    int questionsans=0;

    private static final int COLUMNS = 3;
    private static final int DIMENSIONS = COLUMNS * COLUMNS;
    private static int mColumnWidth, mColumnHeight;
    private TextView pauseact;
    private Button button;
    //String temp="False";
    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";
    //private static TextView textView;
    private static String[] tileList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        scores=(TextView)findViewById(R.id.scoree);
        mTextViewCountDown=(TextView)findViewById(R.id.Timer);
        button=(Button) findViewById(R.id.button);
        pauseact=(TextView)findViewById(R.id.pause);
        startTimer();
        init();
        // textView=(TextView)findViewById(R.id.textView);
        scramble();
        setDimensions();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText().equals("Question 1")) {
                    if(questionsans==0) {
                        pauseTimer();
                        questionsans = 1;
                        openActivity2();
                    }
                    else if(questionsans==1)
                    {
                        Toast.makeText(getApplicationContext(), "You have already tried to answer questions!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(button.getText().equals("Play video"))
                {
                    openyoutube();
                }
            }
        });

        pauseact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer();
                openpause();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK)
            {
                //temp=data.getStringExtra(question.Extra_text);
                resetTimer();
                startTimer();
                display(getApplicationContext());

            }
            else
            {
                //temp=data.getStringExtra(question.Extra_text);
                startTimer();
                display(getApplicationContext());
            }
            //display(getApplicationContext());
        }
    }

    public void openActivity2()
    {
        Intent intent=new Intent(this, level2question.class);
        startActivityForResult(intent,1);
    }

    public void openActivity3()
    {
        Intent intent=new Intent(this, ques2.class);
        startActivityForResult(intent,1);
    }

    public void openpause()//////////////////////////////////
    {
        Intent intent=new Intent(this, Pausegame.class);
        startActivityForResult(intent,1);
    }

    public void openyoutube()
    {
        Intent intent=new Intent(this, Youtube2.class);
        startActivity(intent);
    }

    private void resetTimer() {
        mTimeLeftInMillis = mTimeLeftInMillis+15000;
        updateCountDownText();
    }

    public void openActivity1()
    {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void init() {
        mGridView = (Gesture2) findViewById(R.id.grid);
        mGridView.setNumColumns(COLUMNS);

        tileList = new String[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++) {
            tileList[i] = String.valueOf(i);
        }
    }

    private void scramble() {
        int index;
        String temp;
        Random random = new Random();

        for (int i = tileList.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }

    private void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth / COLUMNS;
                mColumnHeight = requiredHeight / COLUMNS;

                display(getApplicationContext());

            }
        });
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private static void display(Context context) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        for (int i = 0; i < tileList.length; i++) {
            button = new Button(context);

            if (tileList[i].equals("0"))
                button.setBackgroundResource(R.drawable.ship_1);
            else if (tileList[i].equals("1"))
                button.setBackgroundResource(R.drawable.ship_2);
            else if (tileList[i].equals("2"))
                button.setBackgroundResource(R.drawable.ship_3);
            else if (tileList[i].equals("3"))
                button.setBackgroundResource(R.drawable.ship_4);
            else if (tileList[i].equals("4"))
                button.setBackgroundResource(R.drawable.ship_5);
            else if (tileList[i].equals("5"))
                button.setBackgroundResource(R.drawable.ship_6);
            else if (tileList[i].equals("6"))
                button.setBackgroundResource(R.drawable.ship_7);
            else if (tileList[i].equals("7"))
                button.setBackgroundResource(R.drawable.ship_8);
            else if (tileList[i].equals("8"))
                button.setBackgroundResource(R.drawable.ship_9);

            buttons.add(button);
            //textView.setText(Arrays.toString(tileList));
        }

        mGridView.setAdapter(new CustomAdapter(buttons, mColumnWidth, mColumnHeight));
    }

    private static void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);

        //if (isSolved()) Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
    }

    public static void moveTiles(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                position % COLUMNS == 0) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == COLUMNS * 2 - 1 || position == COLUMNS * 3 - 1) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                        COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else swap(context, position, COLUMNS);
        }
    }


    private static boolean isSolved() {
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            }
            else
            {
                solved = false;
                break;
            }
        }

        return solved;
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                mTimerRunning = false;
                Toast.makeText(getApplicationContext(), "You Lose :(", Toast.LENGTH_SHORT).show();
                openActivity1();
            }
        }.start();

        mTimerRunning = true;
    }

    private void updateCountDownText() {

        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String score= String.valueOf(seconds);
        if(isSolved())
        {
            Toast.makeText(getApplicationContext(),"Your score was: "+ score, Toast.LENGTH_SHORT).show();
            questionsans=2;
            pauseTimer();
            scores.setText("Your score: "+score);
            button.setText("Play video");
        }
        //if(score.equals("00"))
        //  {
        //    Toast.makeText(getApplicationContext(), "You Lose :(", Toast.LENGTH_SHORT).show();
        //   openActivity1();
        //   }
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;

    }
}
