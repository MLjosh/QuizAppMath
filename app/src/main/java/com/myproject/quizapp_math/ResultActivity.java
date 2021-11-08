package com.myproject.quizapp_math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    public static Context context;

    TextView txtHighScore;
    TextView txtTotalQuizQues, txtCorrectQues, txtWrongQues, result_levelmode;

    Button btStartQuiz;
    Button btMainMenu, btNextLevel;


    private int highScore;
    public static final String SHARED_PREFERRENCE = "shread_prefrence";
    public static final String SHARED_PREFERRENCE_HIGH_SCORE = "shread_prefrence_high_score";

    private long backPressedTime;

    String CategoryAgainValue = "";
    int levelsId = 0;
    String levelsmode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_result);

        btMainMenu = findViewById(R.id.result_bt_mainmenu);
        btStartQuiz = findViewById(R.id.result_bt_playAgain);
        txtHighScore = findViewById(R.id.result_text_High_Score);
        txtTotalQuizQues = findViewById(R.id.result_total_Ques);
        txtCorrectQues = findViewById(R.id.result_Correct_Ques);
        txtWrongQues = findViewById(R.id.result_Wrong_Ques);
        result_levelmode = findViewById(R.id.result_levelmode);

        btNextLevel = findViewById(R.id.result_bt_nextlevel);

        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);

        btMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        btStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(animScale);
                Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
                intent.putExtra("Level", levelsId);
                startActivity(intent);
            }
        });

        btNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(animScale);
                Intent intentMaths = new Intent(ResultActivity.this, LevelActivity.class);
                intentMaths.putExtra("Level", levelsId);
                startActivity(intentMaths);

            }

        });




        loadHighScore();

        Intent intent = getIntent();

        int score = intent.getIntExtra("UserScore", 0);
        int totalQuestion = intent.getIntExtra("TotalQuestion", 0);
        int correctQues = intent.getIntExtra("CorrectQues", 0);
        int wrongQues = intent.getIntExtra("WrongQues", 0);


        CategoryAgainValue = intent.getStringExtra("Category");
        levelsId = intent.getIntExtra("Level", 0);

        levelsmode = intent.getStringExtra("Levelsmode");


        txtTotalQuizQues.setText("Total Questions: " + String.valueOf(totalQuestion));
        txtCorrectQues.setText("Correct Answer: " + String.valueOf(correctQues));
        txtWrongQues.setText("Total Score: " + String.valueOf(wrongQues));
        result_levelmode.setText("Level Mode: " + String.valueOf(levelsmode));


        if (score > highScore) {

            updatHighScore(score);
        } btMainMenu = findViewById(R.id.result_bt_mainmenu);
        btStartQuiz = findViewById(R.id.result_bt_playAgain);
        txtHighScore = findViewById(R.id.result_text_High_Score);
        txtTotalQuizQues = findViewById(R.id.result_total_Ques);
        txtCorrectQues = findViewById(R.id.result_Correct_Ques);
        txtWrongQues = findViewById(R.id.result_Wrong_Ques);
        result_levelmode = findViewById(R.id.result_levelmode);

        btNextLevel = findViewById(R.id.result_bt_nextlevel);


        btMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        btStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
                intent.putExtra("Level", levelsId);
                startActivity(intent);
            }
        });

        btNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentMaths = new Intent(ResultActivity.this, LevelActivity.class);
                intentMaths.putExtra("Level", levelsId);
                startActivity(intentMaths);

            }

        });


        context = getApplicationContext();
        AppController.currentActivity = this;
        if(SettingsPreferences.getMusic(context)){

            try{
                AppController.playsound();
            }catch (IllegalStateException e){
                e.printStackTrace();
            }

        }



    }

    private void updatHighScore(int newHighScore) {

        highScore = newHighScore;
        txtHighScore.setText("High Score: " + String.valueOf(highScore));

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERRENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFERRENCE_HIGH_SCORE, highScore);
        editor.apply();


    }

    private void loadHighScore() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERRENCE, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(SHARED_PREFERRENCE_HIGH_SCORE, 0);
        txtHighScore.setText("High Score: " + String.valueOf(highScore));

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();

        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in PlayActivity");
        finish();
    }



    @Override
    protected void onPause() {
        AppController.StopSound();
        super.onPause();
    }
    //bg
    @Override
    protected void onResume() {
        super.onResume();
        AppController.playsound();
    }




}