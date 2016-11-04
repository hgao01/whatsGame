package com.sanfrancisco.whatsgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ViewMainMenu extends SurfaceView implements SurfaceHolder.Callback {

    GameActivity activity;//??i???
    Bitmap mainMenu;//??i???
    Bitmap biankuang;//?I?????????I?}??
    int dianjiFlag = -1;//?I????m????
    //    ThreadSetView tsv;
    Paint paint;//???e??

    public ViewMainMenu(GameActivity activity) {
        super(activity);
        this.activity = activity;
        this.getHolder().addCallback(this);    //?]?w??R?P?????f
        paint = new Paint();                        //???e??
        paint.setAntiAlias(true);                //?}??????
        mainMenu = BitmapFactory.decodeResource(this.getResources(), R.drawable.zjm);//???J?????
        biankuang = BitmapFactory.decodeResource(this.getResources(), R.drawable.biankuang);//?I?????????I?}??
//		tsv=new ThreadSetView(this);
//	    tsv.start();
    }

    //??N???^???k
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();//???oX?y??
        float y = e.getY();//???oY?y??
        float yRatio = y / GameActivity.screenHeight;
        float xRatio = x / GameActivity.screenWidth;
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (yRatio > 0.491f && yRatio < 0.581f && xRatio > 0.031f && xRatio < 0.55f) {//???U?}?l????
                    dianjiFlag = 0;
                    ThreadSetView.flag = false;
                    activity.hd.sendEmptyMessage(GameActivity.GAME_LOAD);//???J????

                }
                if (yRatio > 0.619f && yRatio < 0.708f && xRatio > 0.031f && xRatio < 0.55f) {//???U?????
                    dianjiFlag = 1;
                    GameActivity.isXNJP = false;
                }
                if (yRatio > 0.746f && yRatio < 0.836f && xRatio > 0.031f && xRatio < 0.55f) {//???U????????
                    dianjiFlag = 2;
                    GameActivity.isXNJP = true;
                }
                if (yRatio > 0.871f && yRatio < 0.961f && xRatio > 0.031f && xRatio < 0.55f) {//???U???}????
                    dianjiFlag = 3;
                    System.exit(0);
                }
                break;
        }
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mainMenu, 0, 0, paint);

        switch (dianjiFlag) {
            case 0:
                canvas.drawBitmap(biankuang, 14.88f, 394.4f, paint);
                dianjiFlag = -1;
                break;
            case 1:
                canvas.drawBitmap(biankuang, 14.88f, 495.2f, paint);
                dianjiFlag = -1;
                break;
            case 2:
                canvas.drawBitmap(biankuang, 14.88f, 596.8f, paint);
                dianjiFlag = -1;
                break;
            case 3:
                canvas.drawBitmap(biankuang, 14.88f, 696.8f, paint);
                dianjiFlag = -1;
                break;
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        Canvas canvas = holder.lockCanvas();//???o?e??
        try {
            synchronized (holder) {
                onDraw(canvas);//???
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }

}
