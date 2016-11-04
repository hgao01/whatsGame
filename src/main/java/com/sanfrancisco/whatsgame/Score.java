package com.sanfrancisco.whatsgame;

import javax.microedition.khronos.opengles.GL10;

import static com.sanfrancisco.whatsgame.Constant.ICON_HEIGHT;
import static com.sanfrancisco.whatsgame.Constant.ICON_WIDTH;

//?????o?????q?????O
public class Score {
    MySurfaceView mv;
    TextureRect[] numbers = new TextureRect[10];

    public Score(int texId, MySurfaceView mv) {
        this.mv = mv;

        //????0-9?Q???r?????z?x??
        for (int i = 0; i < 10; i++) {
            numbers[i] = new TextureRect
                    (
                            texId,
                            ICON_WIDTH * 0.7f / 2,
                            ICON_HEIGHT * 0.7f / 2,
                            new float[]
                                    {
                                            0.1f * i, 0, 0.1f * i, 1, 0.1f * (i + 1), 0,
                                            0.1f * i, 1, 0.1f * (i + 1), 1, 0.1f * (i + 1), 0
                                    }
                    );
        }
    }

    public void drawSelf(GL10 gl) {
        String scoreStr = mv.objectCount + "";
        for (int i = 0; i < scoreStr.length(); i++) {//?N?o???????C???r?r?????
            char c = scoreStr.charAt(i);
            gl.glPushMatrix();
            gl.glTranslatef(i * ICON_WIDTH * 0.7f, 0, 0);
            numbers[c - '0'].drawSelf(gl);
            gl.glPopMatrix();
        }
    }
}
