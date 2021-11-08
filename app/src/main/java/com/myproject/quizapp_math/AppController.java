package com.myproject.quizapp_math;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class AppController extends Application {

    public static Context context;

    private static Context mContext;
    public static Activity currentActivity;
    private static MediaPlayer player;


    @Override
    public void onCreate() {
        super.onCreate();

        setmContext(getApplicationContext());
        player = new MediaPlayer();
        MediaInitializer();

    }

    public static void MediaInitializer() {

        try{
            player = MediaPlayer.create(getmContext(),R.raw.bg);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setLooping(true);
            player.setVolume(1f,1f);

        }catch (IllegalStateException e){
            e.printStackTrace();
        }

    }

    public static Context getmContext() {
        return mContext;
    }

    public static void setmContext(Context mContext) {
        AppController.mContext = mContext;
    }


    public static void playsound(){

        try{
            if (SettingsPreferences.getMusic(mContext) && !player.isPlaying()){
                player.start();
            }

        }catch (IllegalStateException e){
            e.printStackTrace();
            MediaInitializer();
            player.start();
        }

    }


    public static void StopSound(){

        if(player.isPlaying()){
            player.pause();
        }

    }

}
