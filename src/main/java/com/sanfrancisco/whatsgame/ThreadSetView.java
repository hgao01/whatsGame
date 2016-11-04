package com.sanfrancisco.whatsgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class ThreadSetView extends Thread {
    static boolean flag = true;
    ViewMainMenu setView;//?????
    SurfaceHolder holder;

    public ThreadSetView(ViewMainMenu setView) {
        this.setView = setView;
        this.holder = setView.getHolder();
    }

    public void run() {
        Canvas canvas;

        while (flag) {
            canvas = null;
            if (true) {
                try {

                    canvas = this.holder.lockCanvas();
                    synchronized (this.holder) {
                        setView.onDraw(canvas);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        this.holder.unlockCanvasAndPost(canvas);
                    }
                }
            }
            try {
                sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
