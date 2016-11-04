package com.sanfrancisco.whatsgame;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import java.util.HashMap;

import static com.sanfrancisco.whatsgame.Constant.threadWork;

public class GameActivity extends Activity {
    static final int START_GAME = 0;//?}?l??????Message?s??
    static final int GAME_WIN = 1;//??????Q????s??
    static final int GAME_LOAD = 2;//???J????s??
    static final int MAINMENU = 3;//?D????s??
    static MediaPlayer mpBack;//?????I?????????
    static MediaPlayer mpWin;//????????????
    static SoundPool soundPool;//?????
    static HashMap<Integer, Integer> soundPoolMap; //???????????ID?P??q????ID??Map
    static boolean isXNJP = true;//?O?_?O?????????
    static float screenHeight;//???????
    static float screenWidth;//????e??
    Handler hd;//?T???B?z??
    boolean isWin = false;//?O?_????
    int viewFlag = -1;//??e???????
    private MySurfaceView mGLSurfaceView;
    private ViewMainMenu vmm;//?D?????

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //?????
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //?]?w?????????
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //???o?????R??
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenHeight = dm.heightPixels;
        screenWidth = dm.widthPixels;

        initSound();//?_?l?????

        hd = new Handler()//?_?l??T???B?z??
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case START_GAME:
                        mpBack.start();//?}??????I????????
                        mGLSurfaceView = new MySurfaceView(GameActivity.this);
                        mGLSurfaceView.requestFocus();//???o?J?I
                        mGLSurfaceView.setFocusableInTouchMode(true);//?]?w???i???
                        setContentView(mGLSurfaceView);
                        viewFlag = START_GAME;
                        new KeyThread(mGLSurfaceView).start();
                        break;
                    case GAME_WIN:
                        setContentView(R.layout.win);//????????????
                        viewFlag = GAME_WIN;
                        break;
                    case GAME_LOAD:
                        setContentView(R.layout.load);//?????????J???
                        viewFlag = GAME_LOAD;
                        new Thread() {
                            @Override
                            public void run() {
                                waitTwoSeconds();
                                //?o?T?????J????
                                hd.sendEmptyMessage(START_GAME);
                            }
                        }.start();
                        break;
                    case MAINMENU:
                        vmm = new ViewMainMenu(GameActivity.this);//?D?????
                        setContentView(vmm);//???D??????D?????
                        ThreadSetView tsv = new ThreadSetView(vmm);
                        ThreadSetView.flag = true;
                        tsv.start();
                        vmm.setFocusableInTouchMode(true);//?]?w???i???
                        vmm.requestFocus();//???o?J?I
                        viewFlag = MAINMENU;
                        break;
                }
            }
        };

        vmm = new ViewMainMenu(this);//?D?????
        setContentView(vmm);
        ThreadSetView tsv = new ThreadSetView(vmm);
        tsv.start();
        viewFlag = MAINMENU;
    }

    @Override
    protected void onResume() {
        super.onResume();
        threadWork = true;
        if (mGLSurfaceView != null) {
//        	mGLSurfaceView.onResume();
            if (isWin) {
                mpWin.start();
            } else {
                mpBack.start();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        threadWork = false;
//        if(mGLSurfaceView!=null)
//        {
//        	mGLSurfaceView.onPause();
//        }
        if (mpBack != null) {
            mpBack.pause();
//        	mpBack=null;
        }
        if (mpWin != null) {
            mpWin.pause();
//        	mpWin=null;
        }
    }

    public void waitTwoSeconds() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initSound() {
        if (mpBack != null) {
            return;
        }

        //?I??????
        mpBack = MediaPlayer.create(this, R.raw.gameback);
        mpBack.setLooping(true);

        //?????
        mpWin = MediaPlayer.create(this, R.raw.win);
        mpWin.setLooping(true);

        //?????
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
        //?Y?F????
        soundPoolMap.put(1, soundPool.load(this, R.raw.gotobject, 1));
    }

    public void playSound(int sound, int loop) {
        AudioManager mgr = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeCurrent / streamVolumeMax;

        soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent e) {
        if (keyCode == 4) {
            if (viewFlag == GAME_LOAD) {
                return false;//???_?@??
            } else {
                switch (viewFlag) {
                    case START_GAME:
                        hd.sendEmptyMessage(MAINMENU);//???D??D?????
                        mpBack.pause();
                        break;
                    case GAME_WIN:
                        mpWin.pause();
                        hd.sendEmptyMessage(MAINMENU);//???D??D?????
                        break;
                    case MAINMENU:
                        System.exit(0);//???}????
                        break;
                }
            }
        }

        if (mGLSurfaceView == null) {
            return false;
        }

        switch (keyCode) {
            case 19://?V?W??N??e?i
                KeyThread.keyState = 1;
                break;
            case 20://?V?U??N???h
                KeyThread.keyState = 2;
                break;
            case 21:  //?V???N????
                KeyThread.keyState = 3;
                break;
            case 22:  //?V?k?N??k??
                KeyThread.keyState = 4;
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent e) {
        if (keyCode >= 19 && keyCode <= 22) {
            KeyThread.keyState = 0;
        }
        return true;
    }
}



