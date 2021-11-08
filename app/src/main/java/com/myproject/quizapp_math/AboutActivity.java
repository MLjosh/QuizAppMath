package com.myproject.quizapp_math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    public static Context context;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_about);


        context = getApplicationContext();
        AppController.currentActivity = this;
        if(SettingsPreferences.getMusic(context)){

            try{
                AppController.playsound();
            }catch (IllegalStateException e){
                e.printStackTrace();
            }

        }



        Button buttonbb =  findViewById(R.id.buttonbb);
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);

        buttonbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                Intent intent = new Intent(AboutActivity.this, Settings.class);
                startActivity(intent);
                finish();
            }
        });
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