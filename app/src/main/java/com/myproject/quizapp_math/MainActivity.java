package com.myproject.quizapp_math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    public static Context context;
    private long backPressedTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Button btplay =  findViewById(R.id.bt_play);
        Button button3 = findViewById(R.id.button3);
        Button buttonsettings = findViewById(R.id.buttonsettings);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);

        btplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                startActivity(intent);
            }
        });

        buttonsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                finish();
                System.exit(0);
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


    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            finish();
            System.exit(0);

        }else {
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();

        }
        backPressedTime = System.currentTimeMillis();
    }


}