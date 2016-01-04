/*
 *  This file is part of  OpenPixelEngine.
 *
 *     OpenPixelEngine is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or any later version.
 *
 *     OpenPixelEngine is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with OpenPixelEngine.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (c) 2015
 */

package com.herdeliaslegacy.openpixelengine.Manager;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author skad on 26/10/15.
 * Class for Managing sound into the game
 */
public class SoundManager {

    protected static final String TAG = "Sound Manager";                       // Tag for login echo
    private final SoundPool mSoundPool;                                        // SoundPool
    private final HashMap<String,Integer> mSoundMap;                           // Has map for linking name of sound and id into sound pool ["fire":2]
    private final AudioManager mAudioManager;                                  // Internal audio manager for adjusting sound playing
    private final MediaPlayer mBackgroundMusic;                                // Backhound music
    private boolean mBackgroundMusicReady;                                     // State of the background music

    /**
     * Constructor
     *
     * @param context parent Activity
     */
    public SoundManager(Activity context){
        context.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mSoundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        mSoundMap = new HashMap<>();
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mBackgroundMusic = new MediaPlayer();
        mBackgroundMusicReady = false;
    }

    /**
     * Destroy all music before closing
     * @throws Throwable
     */
    public void finalize() throws Throwable {
        super.finalize();
        mBackgroundMusic.release();
    }

    /**
     * Add sound to the SoundPool and to the Hash Map
     * @param name Name of the sound ie : "fire"
     * @param file Filename of the sound : "fire.ogg" (this must be the real path of the file)
     */
    public void AddSound(String name, String file){
        int soundID = mSoundPool.load(file, 1);
        mSoundMap.put(name,soundID);
    }

    /**
     * Load the background music
     * @param name Filename of the music : "back.ogg" (this must be the real path of the file)
     * @throws IOException
     */
    public void SetBackgroundMusic(String name) throws IOException {
        mBackgroundMusic.setDataSource(name);
        mBackgroundMusic.prepare();
        mBackgroundMusic.setLooping(true);
        mBackgroundMusicReady = true;
    }

    /**
     * Remove sound from the SoundPool and from the Hash Map
     * @param name Name of the sound ie : "fire"
     */
    public void RemoveSound(String name){
        int soundID = mSoundMap.get(name);
        mSoundMap.remove(name);
        mSoundPool.unload(soundID);
    }

    /**
     * Start playing background
     */
    public void PlayBackground(){
        if(mBackgroundMusicReady){
            mBackgroundMusic.start();
        }
    }

    /**
     * Stop playing background and reset to the beginning of the file
     */
    public void StopBackground(){
        if(mBackgroundMusic.isPlaying()){
            mBackgroundMusic.stop();
            mBackgroundMusic.reset();
            mBackgroundMusicReady = false;
        }
    }

    /**
     * Play sound named
     * @param name Sound name into the hasmap
     * @return status of playing
     */
    public int Play(String name){
        return Playing(name,0);
    }

    /**
     * Loop sound named
     * @param name Sound name into the hasmap
     * @return status of playing
     */
    public int Loop(String name){
        return Playing(name,-1);
    }

    /**
     * Play sound with the good StreamVolume
     * @param name Sound name into the hasmap
     * @param loop looping status
     * @return status of playing
     */
    private int Playing(String name, int loop){
        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        return mSoundPool.play(mSoundMap.get(name),currentVolume,currentVolume,0,loop,1);
    }

}
