package com.sanfrancisco.whatsgame;

import javax.microedition.khronos.opengles.GL10;

import static com.sanfrancisco.whatsgame.Constant.FLOOR_Y;
import static com.sanfrancisco.whatsgame.Constant.MAP_OBJECT;
import static com.sanfrancisco.whatsgame.Constant.UNIT_SIZE;

//??????s??????O
public class TradPairGroup {
    TradPair tp;//????
    float yAngle = 0;//????uY?b????????
    boolean flag = true;//?O?_???????
    int[][] objectMap;//?????m?a??

    public TradPairGroup(int texId) {
        tp = new TradPair(texId);

        //??????m?a??=======begin==============
        int rows = MAP_OBJECT.length;
        int cols = MAP_OBJECT[0].length;

        objectMap = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                objectMap[i][j] = MAP_OBJECT[i][j];
            }
        }
        //??????m?a??======end=================

        new Thread() {//?N?????Y?b?????????
            @Override
            public void run() {
                while (flag) {
                    yAngle += 5.0;
                    if (yAngle >= 360) {
                        yAngle = 0;
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void drawSelf(GL10 gl) {
        int rows = objectMap.length;
        int cols = objectMap[0].length;

        //???y?????m?a????C???l?A?Y????????h???
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (objectMap[i][j] == 1) {
                    gl.glPushMatrix();//?O?@?{??
                    //?h????????????????m
                    gl.glTranslatef((j + 0.5f) * UNIT_SIZE, FLOOR_Y + 0.45f, (i + 0.5f) * UNIT_SIZE);
                    //?N?????Y?b????
                    gl.glRotatef(yAngle, 0, 1, 0);
                    //?????
                    tp.drawSelf(gl);
                    gl.glPopMatrix();//???{??
                }
            }
        }
    }
}
