package com.myproject.quizapp_math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

import static com.myproject.quizapp_math.AppController.*;

public class Settings extends AppCompatActivity {

    Button aboutbutton , backbutton;

    Switch playbgmusic;
    private boolean isMusicOn;
    private Context mContext;
    private SeekBar volumeSeekbar;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_settings);

        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);

        mContext = Settings.this;
        AppController.currentActivity = this;
        playbgmusic = findViewById(R.id.switch1);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();

        Button aboutbutton =  findViewById(R.id.aboutbutton);
        aboutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        Button backbutton =  findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
                finish();
                Intent intent = new Intent(Settings.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


    private void initControls() {

        try {
            volumeSeekbar = findViewById(R.id.sb);
            audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE
            );
            volumeSeekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }catch (Exception e){
            e.printStackTrace();

        }

    }

    private void switchMusic(){

        isMusicOn = !isMusicOn;
        if (isMusicOn){
            SettingsPreferences.setMusic(mContext, true);
            AppController.playsound();
        }else{
            SettingsPreferences.setMusic(mContext, false);
            StopSound();
        }

        populateMusic();

    }

    private void populateMusic() {
        if (SettingsPreferences.getMusic(mContext)){

            AppController.playsound();
            playbgmusic.setChecked(true);
        } else{

            StopSound();
            playbgmusic.setChecked(false);
        }

        isMusicOn = SettingsPreferences.getMusic(mContext);
    }


    public void viewClick(View view) {

        switch (view.getId()){
            case R.id.switch1:
                switchMusic();
                break;
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


}