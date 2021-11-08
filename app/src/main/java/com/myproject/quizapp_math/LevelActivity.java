package com.myproject.quizapp_math;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LevelActivity extends AppCompatActivity implements View.OnClickListener{

    public static Context context;

    Button btLevel1,btLevel2,btLevel3,btback;

    String CategoryValue = "";

    int AddL1, AddL2, AddL3;


    private long backPressedTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_level);

        btLevel1 = findViewById(R.id.btLevel1);
        btLevel2 = findViewById(R.id.btLevel2);
        btLevel3 = findViewById(R.id.btLevel3);
        btback  = findViewById(R.id.btback);

        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);




//        txtLevel1 = findViewById(R.id.textView);
//        txtLevel2 = findViewById(R.id.textView2);
//        txtLevel3 = findViewById(R.id.textView3);


        lockandUnlockLevels();


        Intent intentCategory = getIntent();
        CategoryValue = intentCategory.getStringExtra("Category");


        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);

                Intent intent = new Intent(LevelActivity.this, MainActivity.class);
                startActivity(intent);

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

    private void lockandUnlockLevels() {

        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PREFFILE,
                        Context.MODE_PRIVATE);

        AddL1  = sharedPreferences.getInt(Constant.KEY_LEVEL_EASY,1);
        AddL2  = sharedPreferences.getInt(Constant.KEY_LEVEL_NORMAL,0);
        AddL3  = sharedPreferences.getInt(Constant.KEY_LEVEL_HARD,0);

        if(AddL1 == 1 ){

            btLevel1.setClickable(true);
            btLevel1.setOnClickListener(this);

        }else if (AddL1 == 0){

            btLevel1.setClickable(false);
            btLevel1.setBackground(ContextCompat.getDrawable(this,R.drawable.butonblack));

        }

        if(AddL2 == 1 ){
            btLevel2.setClickable(true);
            btLevel2.setOnClickListener(this);
        }else if(AddL2 == 0 ){
            btLevel2.setClickable(false);
            btLevel2.setBackground(ContextCompat.getDrawable(this,R.drawable.butonblack));

            final Context context2 = this;
            btLevel2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // custom dialog
                    final Dialog dialog = new Dialog(context2);
                    dialog.setContentView(R.layout.note_dialog);

                    TextView textViewCorrectAnswer = (TextView) dialog.findViewById(R.id.notetext);
                    textViewCorrectAnswer.setText("Achieved 70pts or 7 correct answer in EASY LEVEL to unlock this level!");

                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            v.startAnimation(animScale);
                        }
                    });
                    dialog.show();
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
            });

        }

        if(AddL3 == 1 ){
            btLevel3.setClickable(true);
            btLevel3.setOnClickListener(this);
        }else if(AddL3  == 0 ){
            btLevel3.setClickable(false);
            btLevel3.setBackground(ContextCompat.getDrawable(this,R.drawable.butonblack));

            final Context context2 = this;
            btLevel3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // custom dialog
                    final Dialog dialog = new Dialog(context2);
                    dialog.setContentView(R.layout.note_dialog);

                    TextView textViewCorrectAnswer = (TextView) dialog.findViewById(R.id.notetext);
                    textViewCorrectAnswer.setText("Achieved 70pts or 7 correct answer in NORMAL LEVEL to unlock this level!");

                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            v.startAnimation(animScale);
                        }
                    });
                    dialog.show();
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
            });

        }


    }

//    public void LoadData(View view) {
//
//        txtLevel1.setText(String.valueOf(AddL1));
//        txtLevel2.setText(String.valueOf(AddL2));
//        txtLevel3.setText(String.valueOf(AddL3));
//
//    }

    @Override
    public void onClick(View v) {
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        v.startAnimation(animScale);

            switch (v.getId()) {

                case R.id.btLevel1:

                    Intent intentMathLevel1 = new Intent(LevelActivity.this, QuizActivity.class);
                    intentMathLevel1.putExtra("Level", Questions.EASYLEVEL);
                    startActivity(intentMathLevel1);

                    break;

                case R.id.btLevel2:
                    Intent intentMathLevel2 = new Intent(LevelActivity.this, QuizActivity.class);
                    intentMathLevel2.putExtra("Level", Questions.NORMALLEVEL);
                    startActivity(intentMathLevel2);

                    break;

                case R.id.btLevel3:
                    Intent intentMathLevel3 = new Intent(LevelActivity.this, QuizActivity.class);
                    intentMathLevel3.putExtra("Level", Questions.HARDLEVEL);
                    startActivity(intentMathLevel3);
                    break;

            }

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