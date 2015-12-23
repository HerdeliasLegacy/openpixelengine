package com.herdeliaslegacy.openpixelengine.Manager;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by skad on 26/10/15.
 * Class for Managing sound into the game
 *
 */
public class SoundManager {
    private final SoundPool mSoundPool;
    private final HashMap<String,Integer> mSoundMap;
    private final AudioManager mAudioManager;
    private final MediaPlayer mBackgroundMusic;
    private boolean mBackgroundMusicReady;

    public SoundManager(Activity context){
        context.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mSoundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        mSoundMap = new HashMap<>();
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mBackgroundMusic = new MediaPlayer();
        mBackgroundMusicReady = false;
    }

    public void finalize() throws Throwable {
        super.finalize();
        mBackgroundMusic.release();
    }
    public void AddSound(String name, String file){
        int soundID = mSoundPool.load(file, 1);
        mSoundMap.put(name,soundID);
    }

    public void SetBackgroundMusic(String name) throws IOException {
        mBackgroundMusic.setDataSource(name);
        mBackgroundMusic.prepare();
        mBackgroundMusic.setLooping(true);
        mBackgroundMusicReady = true;
    }

    public void RemoveSound(String name){
        int soundID = mSoundMap.get(name);
        mSoundMap.remove(name);
        mSoundPool.unload(soundID);
    }

    public void PlayBackground(){
        if(mBackgroundMusicReady){
            mBackgroundMusic.start();
        }
    }

    public void StopBackground(){
        if(mBackgroundMusic.isPlaying()){
            mBackgroundMusic.stop();
            mBackgroundMusic.reset();
            mBackgroundMusicReady = false;
        }
    }

    public int Play(String name){
        return Playing(name,0);
    }
    public int Loop(String name){
        return Playing(name,-1);
    }

    protected int Playing(String name,int loop){
        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        return mSoundPool.play(mSoundMap.get(name),currentVolume,currentVolume,0,loop,1);
    }

}
