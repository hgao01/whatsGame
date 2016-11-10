package com.sanfrancisco.whatsgame;

import static com.sanfrancisco.whatsgame.Constant.HALF_COLL_SIZE;
import static com.sanfrancisco.whatsgame.Constant.MAP;
import static com.sanfrancisco.whatsgame.Constant.MOVE_XZ;
import static com.sanfrancisco.whatsgame.Constant.OBJECT_COUNT;
import static com.sanfrancisco.whatsgame.Constant.UNIT_SIZE;
import static com.sanfrancisco.whatsgame.Constant.threadinWork;

//??????????
public class KeyThread extends Thread {
    static int keyState = 0;//0-?S??????U 1-?W 2-?U 3-?? 4-?k
    MySurfaceView mGLSurfaceView;

    public KeyThread(MySurfaceView mGLSurfaceView) {
        this.mGLSurfaceView = mGLSurfaceView;
    }

    public void run() {
        while (threadinWork) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (keyState == 0) {
                continue;
            }
            try {


                if (keyState == 1 || keyState == 2) {//?W?U????e?i???h
                    int tempDirection = 0;
                    switch (keyState) {
                        case 1://?V?W??N??e?i
                            tempDirection = mGLSurfaceView.currentDirection;//?e?i??B???V?P??e??V??P
                            break;
                        case 2://?V?U??N???h
                            tempDirection = (mGLSurfaceView.currentDirection + 2) % 4;//??h??B???V?P??e??V???
                            break;
                    }
                    //?p??B???XZ??
                    mGLSurfaceView.heroX = mGLSurfaceView.heroX + MOVE_XZ[tempDirection][0];
                    mGLSurfaceView.heroZ = mGLSurfaceView.heroZ + MOVE_XZ[tempDirection][1];

                    boolean backFlag = false;//?????

                    //?I?????W???I=======================begin
                    float tempX = mGLSurfaceView.heroX - HALF_COLL_SIZE;//???W???IXY?y??
                    float tempZ = mGLSurfaceView.heroZ - HALF_COLL_SIZE;
                    int tempCol = (int) ((tempX / UNIT_SIZE >= 0) ? (tempX / UNIT_SIZE) : -1);//???W???I??C
                    int tempRow = (int) ((tempZ / UNIT_SIZE >= 0) ? (tempZ / UNIT_SIZE) : -1);

                    if (tempCol == -1 || tempCol == MAP[0].length ||
                            tempRow == -1 || tempRow == MAP.length ||
                            MAP[tempRow][tempCol] == 0) {//?Y?I???h?]?w?????
                        backFlag = true;
                    }
                    //?I?????W???I=======================end

                    //?I????k?W???I=======================begin
                    tempX = mGLSurfaceView.heroX + HALF_COLL_SIZE;//?k?W???IXY?y??
                    tempZ = mGLSurfaceView.heroZ - HALF_COLL_SIZE;
                    tempCol = (int) ((tempX / UNIT_SIZE >= 0) ? (tempX / UNIT_SIZE) : -1);//?k?W???I??C
                    tempRow = (int) ((tempZ / UNIT_SIZE >= 0) ? (tempZ / UNIT_SIZE) : -1);
                    if (tempCol == -1 || tempCol == MAP[0].length ||
                            tempRow == -1 || tempRow == MAP.length ||
                            MAP[tempRow][tempCol] == 0) {//?Y?I???h?]?w?????
                        backFlag = true;
                    }
                    //?I????k?W???I=======================end

                    //?I?????U???I=======================begin
                    tempX = mGLSurfaceView.heroX - HALF_COLL_SIZE;//???U???IXY?y??
                    tempZ = mGLSurfaceView.heroZ + HALF_COLL_SIZE;
                    tempCol = (int) ((tempX / UNIT_SIZE >= 0) ? (tempX / UNIT_SIZE) : -1);//???U???I??C
                    tempRow = (int) ((tempZ / UNIT_SIZE >= 0) ? (tempZ / UNIT_SIZE) : -1);
                    if (tempCol == -1 || tempCol == MAP[0].length ||
                            tempRow == -1 || tempRow == MAP.length ||
                            MAP[tempRow][tempCol] == 0) {//?Y?I???h?]?w?????
                        backFlag = true;
                    }
                    //?I?????U???I=======================end

                    //?I????k?U???I=======================begin
                    tempX = mGLSurfaceView.heroX + HALF_COLL_SIZE;//?k?U???IXY?y??
                    tempZ = mGLSurfaceView.heroZ + HALF_COLL_SIZE;
                    tempCol = (int) ((tempX / UNIT_SIZE >= 0) ? (tempX / UNIT_SIZE) : -1);//?k?U???I??C
                    tempRow = (int) ((tempZ / UNIT_SIZE >= 0) ? (tempZ / UNIT_SIZE) : -1);
                    if (tempCol == -1 || tempCol == MAP[0].length ||
                            tempRow == -1 || tempRow == MAP.length ||
                            MAP[tempRow][tempCol] == 0) {//?Y?I???h?]?w?????
                        backFlag = true;
                    }
                    //?I????k?U???I=======================end

                    if (backFlag) {//???e?\?h???h???
                        mGLSurfaceView.heroX = mGLSurfaceView.heroX - MOVE_XZ[tempDirection][0];
                        mGLSurfaceView.heroZ = mGLSurfaceView.heroZ - MOVE_XZ[tempDirection][1];
                    }

                    //?P?_???_?I????
                    tempCol = (int) (mGLSurfaceView.heroX / UNIT_SIZE);//?p???e??M?C
                    tempRow = (int) (mGLSurfaceView.heroZ / UNIT_SIZE);

                    if (mGLSurfaceView.mRenderer.tpg != null && mGLSurfaceView.mRenderer.tpg.objectMap[tempRow][tempCol] == 1) {//?I????
                        GameActivity ma = (GameActivity) mGLSurfaceView.getContext();
                        ma.playSound(1, 0);//????Y??????
                        mGLSurfaceView.mRenderer.tpg.objectMap[tempRow][tempCol] = 0;//???????B????
                        mGLSurfaceView.objectCount++;//?w?Y??q?[1
                        if (mGLSurfaceView.objectCount == OBJECT_COUNT) {//??F
                            GameActivity.mpBack.pause();//???????????I??????
                            GameActivity.mpWin.start();//????????I??????
                            ma.hd.sendEmptyMessage(GameActivity.GAME_WIN);
                            mGLSurfaceView.mRenderer.tpg.flag = false;//???A??????
                        }
                    }
                } else {
                    switch (keyState) {
                        case 3:  //?V???N????
                            mGLSurfaceView.currentDirection = (mGLSurfaceView.currentDirection - 1 + 4) % 4;
                            break;
                        case 4:  //?V?k?N??k??
                            mGLSurfaceView.currentDirection = (mGLSurfaceView.currentDirection + 1) % 4;
                            break;
                    }
                }
                //?]?w?s???[?????IXZ?y??
                mGLSurfaceView.heroXT = mGLSurfaceView.heroX + MOVE_XZ[mGLSurfaceView.currentDirection][0];
                mGLSurfaceView.heroZT = mGLSurfaceView.heroZ + MOVE_XZ[mGLSurfaceView.currentDirection][1];

                if (keyState == 3 || keyState == 4) {
                    Thread.sleep(200);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
