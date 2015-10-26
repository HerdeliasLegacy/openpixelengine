package com.herdeliaslegacy.openpixelengine.Manager;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.List;

/**
 * Created by skad on 26/10/15.
 * COPYRIGHT
 * thank to http://stackoverflow.com/a/27552576
 */
public class SoundManager {
    private SoundPool mSoundPool;
    private HashMap<String,Integer> mSoundMap;
    private AudioManager mAudioManager;

    public SoundManager(Activity context){
        context.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mSoundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        mSoundMap = new HashMap<String, Integer>();
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void Add(String name, String file){
        int soundID = mSoundPool.load(file, 1);
        mSoundMap.put(name,soundID);
    }

    public void Remove(String name){
        int soundID = mSoundMap.get(name);
        mSoundMap.remove(name);
        mSoundPool.unload(soundID);

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
