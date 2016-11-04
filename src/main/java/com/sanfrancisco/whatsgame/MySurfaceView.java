package com.sanfrancisco.whatsgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.view.MotionEvent;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.sanfrancisco.whatsgame.Constant.CAMERA_COL;
import static com.sanfrancisco.whatsgame.Constant.CAMERA_ROW;
import static com.sanfrancisco.whatsgame.Constant.ICON_DIS;
import static com.sanfrancisco.whatsgame.Constant.ICON_HEIGHT;
import static com.sanfrancisco.whatsgame.Constant.ICON_WIDTH;
import static com.sanfrancisco.whatsgame.Constant.NEAR;
import static com.sanfrancisco.whatsgame.Constant.SPEED;
import static com.sanfrancisco.whatsgame.Constant.UNIT_SIZE;

class MySurfaceView extends GLSurfaceView {

    public SceneRenderer mRenderer;//�����ۦ⾹

    public int currentDirection = 0; //�_�l��V��NORTH

    public float heroX = CAMERA_COL * UNIT_SIZE + UNIT_SIZE / 2;    //�H����XYZ�y��
    public float heroY = 0.4f;
    public float heroZ = CAMERA_ROW * UNIT_SIZE + UNIT_SIZE / 2;

    public float heroXT = heroX;    //�H���ت��I��XYZ�y��
    public float heroYT = 0.4f;
    public float heroZT = heroZ - SPEED;

    public int floorTexId;//�a�O���zID
    public int ceilTexId;//�γ����zID
    public int wallTexId;//�𯾲zID
    public int robotTexId;//���鯾�zID
    public int iconTexId;//����ϥܯ��zID
    public int numberTexId;//�o���Ʀr���zID
    public int controlTexId;//�����n�쯾�zID
    public int redDotTexId;//�����n����I���zID

    public int objectCount = 0;//�w�Y�쪺����ƶq

    public MySurfaceView(Context context) {
        super(context);
        mRenderer = new SceneRenderer();    //�إ߳����ۦ⾹
        setRenderer(mRenderer);                //�]�w�ۦ
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//�]�w�ۦ�Ҧ����D�ʵۦ�
    }

    //Ĳ�N�ƥ�^�դ�k
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (GameActivity.isXNJP == false)//�Y�G���O�P�����Ҧ��A������Ф��i�ΡC
        {
            return true;
        }
        float y = e.getY();
        float x = e.getX();
        float yRatio = y / GameActivity.screenHeight;
        float xRatio = x / GameActivity.screenWidth;
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN://���U�ʧ@
                if (yRatio > 0.77f && yRatio < 0.831f && xRatio > 0.756f && xRatio < 0.85f) {//���U�e�i�������s
                    KeyThread.keyState = 1;
                } else if (yRatio > 0.886f && yRatio < 0.948f && xRatio > 0.756f && xRatio < 0.85f) {//���U��h�������s
                    KeyThread.keyState = 2;
                } else if (yRatio > 0.831f && yRatio < 0.886f && xRatio > 0.656f && xRatio < 0.752f) {//���U����������s
                    KeyThread.keyState = 3;
                } else if (yRatio > 0.831f && yRatio < 0.886f && xRatio > 0.85f && xRatio < 0.958f) {//���U�k��������s
                    KeyThread.keyState = 4;
                } else {
                    KeyThread.keyState = 0;
                }
                break;
            case MotionEvent.ACTION_UP://��_�ʧ@
                KeyThread.keyState = 0;
                break;
        }
        return true;
    }

    //�_�l�Ư��z
    public int initTexture(GL10 gl, int drawableId)//textureId
    {
        //���ͯ��zID
        int[] textures = new int[1];
        gl.glGenTextures(1, textures, 0);
        int currTextureId = textures[0];
        gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

        InputStream is = this.getResources().openRawResource(drawableId);
        Bitmap bitmapTmp;
        try {
            bitmapTmp = BitmapFactory.decodeStream(is);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmapTmp, 0);
        bitmapTmp.recycle();

        return currTextureId;
    }

    //�_�l�ƥզ�O
    private void initWhiteLight(GL10 gl) {
        gl.glEnable(GL10.GL_LIGHT1);//�}��1���O

        //���ҥ��]�w
        float[] ambientParams = {0.2f, 0.2f, 0.05f, 1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT, ambientParams, 0);

        //���g���]�w
        float[] diffuseParams = {0.9f, 0.9f, 0.2f, 1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, diffuseParams, 0);

        //�Ϯg���]�w
        float[] specularParams = {1f, 1f, 1f, 1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPECULAR, specularParams, 0);
    }

    //�_�l�Ƨ���
    private void initMaterial(GL10 gl) {//���謰�զ�ɤ���m�⪺�����b�W���N�N��{�X����m��
        //���ҥ����զ����
        float ambientMaterial[] = {1.0f, 1.0f, 1.0f, 1.0f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, ambientMaterial, 0);
        //���g�����զ����
        float diffuseMaterial[] = {1.0f, 1.0f, 1.0f, 1.0f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, diffuseMaterial, 0);
        //�������謰�զ�
        float specularMaterial[] = {1f, 1f, 1f, 1.0f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, specularMaterial, 0);
        gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 4.0f);
    }

    class SceneRenderer implements Renderer {
        Floor floor;//�a�O
        Ceil ceil;//�γ�
        Wall wall;//��
        TradPairGroup tpg;//����s��
        TextureRect icon;//����ϥ�
        Score score;//�ثe��o������ƶq
        TextureRect control;//�����n��
        TextureRect redDot;//�����n����I

        public void onDrawFrame(GL10 gl) {
            //���Υ����ۦ�
            gl.glShadeModel(GL10.GL_SMOOTH);
            //�]�w���}�ҭI���ŵ�
            gl.glEnable(GL10.GL_CULL_FACE);
            //�M���m��֨���`�ק֨�
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            //�]�w�ثe�x�}���Ҧ��x�}
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            //�]�w�ثe�x�}�����x�}
            gl.glLoadIdentity();
            //�e�\����
            gl.glEnable(GL10.GL_LIGHTING);
            //�]�w�զ��������m
            float[] positionParams = {heroX, heroY, heroZ, 1};//���Ωw���
            gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_POSITION, positionParams, 0);
            //�]�w���I��m��V
            GLU.gluLookAt
                    (
                            gl,
                            heroX,   //�H����m��X
                            heroY,    //�H����m��Y
                            heroZ,   //�H����m��Z
                            heroXT,    //�H�����ݪ��IX
                            heroYT,   //�H�����ݪ��IY
                            heroZT,   //�H�����ݪ��IZ
                            0,
                            1,
                            0
                    );

            //ø��a�O
            gl.glPushMatrix();
            gl.glTranslatef(0, Constant.FLOOR_Y, 0);
            floor.drawSelf(gl);
            gl.glPopMatrix();

            //ø��γ�
            gl.glPushMatrix();
            gl.glTranslatef(0, Constant.CELL_Y, 0);
            ceil.drawSelf(gl);
            gl.glPopMatrix();

            //ø����
            gl.glPushMatrix();
            wall.drawSelf(gl);
            gl.glPopMatrix();

            //ø���s��
            gl.glPushMatrix();
            tpg.drawSelf(gl);
            gl.glPopMatrix();


            //�٭즨�_�l���Aø���ϥܻP�ƶq
            //�]�w�ثe�x�}���Ҧ��x�}
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            //�]�w�ثe�x�}�����x�}
            gl.glLoadIdentity();

            //�T�����
            gl.glDisable(GL10.GL_LIGHTING);
            //�}�ҲV�X
            gl.glEnable(GL10.GL_BLEND);
            //�]�w���V�X�]�l�P�ت��V�X�]�l
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

            gl.glPushMatrix();
            gl.glTranslatef(-0.3f, 0.90f, -ICON_DIS - 0.01f);
            icon.drawSelf(gl);//ø���ϥ�
            gl.glTranslatef(ICON_WIDTH, 0, 0);
            score.drawSelf(gl);//ø��w��o����ƶq
            gl.glPopMatrix();

            if (GameActivity.isXNJP)//������O������Ҧ�
            {
                gl.glPushMatrix();
                gl.glTranslatef(0.225f, -0.75f, -ICON_DIS - 0.015f);
                control.drawSelf(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                switch (KeyThread.keyState) {
                    case 0://�S������U
                        gl.glTranslatef(0.22f, -0.735f, -ICON_DIS - 0.01f);
                        break;
                    case 1://�V�W
                        gl.glTranslatef(0.22f, -0.63f, -ICON_DIS - 0.01f);
                        break;
                    case 2://�V�U
                        gl.glTranslatef(0.22f, -0.84f, -ICON_DIS - 0.01f);
                        break;
                    case 3://�V��
                        gl.glTranslatef(0.145f, -0.735f, -ICON_DIS - 0.01f);
                        break;
                    case 4://�V�k
                        gl.glTranslatef(0.295f, -0.735f, -ICON_DIS - 0.01f);
                        break;
                }
                redDot.drawSelf(gl);
                gl.glPopMatrix();
            }
            //�T��V�X
            gl.glDisable(GL10.GL_BLEND);

        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //�]�w�����j�p�Φ�m
            gl.glViewport(0, 0, width, height);
            //�]�w�ثe�x�}����v�x�}
            gl.glMatrixMode(GL10.GL_PROJECTION);
            //�]�w�ثe�x�}�����x�}
            gl.glLoadIdentity();
            //�p��z����v�����
            float ratio = (float) width / height;
            //�I�s����k�p�ⲣ�ͳz����v�x�}
            gl.glFrustumf(-ratio * 0.6f, ratio * 0.6f, -1, 1, NEAR, 100);
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //�����ܧݰ�
            gl.glDisable(GL10.GL_DITHER);
            //�]�w�S�wHint�M�ת��Ҧ��A�o�̬��]�w���ϥΧֳt�Ҧ�
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
            //�]�w�ù��I����¦�RGBA
            gl.glClearColor(0, 0, 0, 0);
            //�ҥβ`�״���
            gl.glEnable(GL10.GL_DEPTH_TEST);

            //�_�l�Ư��z
            floorTexId = initTexture(gl, R.drawable.floor);
            ceilTexId = initTexture(gl, R.drawable.ceil);
            wallTexId = initTexture(gl, R.drawable.wall);
            robotTexId = initTexture(gl, R.drawable.robot);
            iconTexId = initTexture(gl, R.drawable.bb);
            numberTexId = initTexture(gl, R.drawable.number);
            controlTexId = initTexture(gl, R.drawable.control);
            redDotTexId = initTexture(gl, R.drawable.reddot);

            //�إ߭nø�����
            floor = new Floor(0, 0, 1, 0, floorTexId, Constant.MAP[0].length, Constant.MAP.length);
            ceil = new Ceil(0, 0, 1, 0, ceilTexId, Constant.MAP[0].length, Constant.MAP.length);
            wall = new Wall(wallTexId);
            tpg = new TradPairGroup(robotTexId);
            icon = new TextureRect
                    (
                            iconTexId,
                            ICON_WIDTH / 2,
                            ICON_HEIGHT / 2,
                            new float[]
                                    {
                                            0, 0, 0, 1, 1, 0,
                                            0, 1, 1, 1, 1, 0
                                    }
                    );
            score = new Score(numberTexId, MySurfaceView.this);
            control = new TextureRect
                    (
                            controlTexId,
                            0.12f,
                            0.2f,
                            new float[]
                                    {
                                            0, 0, 0, 1, 1, 0,
                                            0, 1, 1, 1, 1, 0
                                    }
                    );
            redDot = new TextureRect
                    (
                            redDotTexId,
                            0.03f,
                            0.05f,
                            new float[]
                                    {
                                            0, 0, 0, 1, 1, 0,
                                            0, 1, 1, 1, 1, 0
                                    }
                    );

            //�_�l�ƥզ�O
            initWhiteLight(gl);
            //�_�l�Ƨ���
            initMaterial(gl);
        }
    }
}
