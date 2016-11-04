package com.sanfrancisco.whatsgame;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import static com.sanfrancisco.whatsgame.Constant.UNIT_SIZE;

//?????I3???A_O
public class Ceil {
    int vCount = 0;//3?AI??q
    float yAngle;//yb?UA?"?x
    int xOffset;//x?_?_q
    int zOffset;//z?-?_q
    int texId;//__?zID
    int width;//?I3???-width-O3??
    int height;//?I3????Lheight-O3??
    private FloatBuffer mVertexBuffer;//3?AIry?D,?r??w"R
    private FloatBuffer mNormalBuffer;//3?AI?k?Vq,?r??w"R
    private FloatBuffer mTextureBuffer;//3?AI__?z,?r??w"R

    public Ceil(int xOffset, int zOffset, float scale, float yAngle, int texId, int width, int height) {
        this.xOffset = xOffset;
        this.zOffset = zOffset;
        this.yAngle = yAngle;
        this.texId = texId;
        this.width = width;//?C??
        this.height = height;//???

        //3?AIry?D,?r????_cl?================begin============================
        vCount = width * height * 6;//"C-O?I3??6-O3?AI

        float vertices[] = new float[vCount * 3];
        int k = 0;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {//"C-O?I3????"?-OT"I6-O3?AI?c?"
                vertices[k++] = (i + 1) * UNIT_SIZE * scale;
                vertices[k++] = 0;
                vertices[k++] = (j + 1) * UNIT_SIZE * scale;

                vertices[k++] = i * UNIT_SIZE * scale;
                vertices[k++] = 0;
                vertices[k++] = (j + 1) * UNIT_SIZE * scale;

                vertices[k++] = i * UNIT_SIZE * scale;
                vertices[k++] = 0;
                vertices[k++] = j * UNIT_SIZE * scale;

                vertices[k++] = i * UNIT_SIZE * scale;
                vertices[k++] = 0;
                vertices[k++] = j * UNIT_SIZE * scale;

                vertices[k++] = (i + 1) * UNIT_SIZE * scale;
                vertices[k++] = 0;
                vertices[k++] = j * UNIT_SIZE * scale;

                vertices[k++] = (i + 1) * UNIT_SIZE * scale;
                vertices[k++] = 0;
                vertices[k++] = (j + 1) * UNIT_SIZE * scale;
            }
        ;

        //?O??3?AIry?D,?r??w"R
        //vertices.length*4?O?]??@-OFloat?|-O?,?O
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());//3]cw?,?O?
        mVertexBuffer = vbb.asFloatBuffer();//A???float???w"R
        mVertexBuffer.put(vertices);//?V?w"R?Ic?J3?AIry?D,?r?
        mVertexBuffer.position(0);//3]cw?w"R?I?_cl?,m
        //_SO'????G??c???P?-?x?,?O???P,?r?3?,??O?,?O??@cw-n,g??ByteBuffer
        //A?'??AA?A??O-n3z1LByteOrder3]cwnativeOrder()?A_?h?3?i_??|?X?YAD
        //3?AIry?D,?r????_cl?================end============================

        //3?AI?k?Vq,?r????_cl?================begin============================
        float normals[] = new float[vCount * 3];
        for (int i = 0; i < vCount; i++) {
            normals[i * 3] = 0;
            normals[i * 3 + 1] = -1;
            normals[i * 3 + 2] = 0;
        }

        ByteBuffer nbb = ByteBuffer.allocateDirect(normals.length * 4);
        nbb.order(ByteOrder.nativeOrder());//3]cw?,?O?
        mNormalBuffer = nbb.asFloatBuffer();//A???int???w"R
        mNormalBuffer.put(normals);//?V?w"R?Ic?J3?AI?U?,?r?
        mNormalBuffer.position(0);//3]cw?w"R?I?_cl?,m
        //_SO'????G??c???P?-?x?,?O???P,?r?3?,??O?,?O??@cw-n,g??ByteBuffer
        //A?'??AA?A??O-n3z1LByteOrder3]cwnativeOrder()?A_?h?3?i_??|?X?YAD
        //3?AI?U?,?r????_cl?================end============================

        //__?z ry?D,?r??_cl?
        float[] texST = new float[vCount * 2];
        for (int i = 0; i < vCount * 2 / 12; i++) {
            texST[i * 12] = 0;
            texST[i * 12 + 1] = 0;

            texST[i * 12 + 2] = 0;
            texST[i * 12 + 3] = 2;

            texST[i * 12 + 4] = 2;
            texST[i * 12 + 5] = 2;

            texST[i * 12 + 6] = 2;
            texST[i * 12 + 7] = 2;

            texST[i * 12 + 8] = 2;
            texST[i * 12 + 9] = 0;

            texST[i * 12 + 10] = 0;
            texST[i * 12 + 11] = 0;
        }
        ;
        ByteBuffer tbb = ByteBuffer.allocateDirect(texST.length * 4);
        tbb.order(ByteOrder.nativeOrder());//3]cw?,?O?
        mTextureBuffer = tbb.asFloatBuffer();//A???int???w"R
        mTextureBuffer.put(texST);//?V?w"R?Ic?J3?AI?U?,?r?
        mTextureBuffer.position(0);//3]cw?w"R?I?_cl?,m
    }

    public void drawSelf(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//?O?I3?AIry?D?}?C
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);//?O?I3?AI?k?Vq?}?C

        gl.glPushMatrix();// ?O?@?{3o
        gl.glTranslatef(xOffset * UNIT_SIZE, 0, 0);
        gl.glTranslatef(0, 0, zOffset * UNIT_SIZE);
        gl.glRotatef(yAngle, 0, 1, 0);

        //???e???cw3?AIry?D,?r?
        gl.glVertexPointer
                (
                        3,                //"C-O3?AI??ry?D??q??3  xyz
                        GL10.GL_FLOAT,    //3?AIry?D-E?????A?? GL_FIXED
                        0,                //3s??3?AIry?D,?r?????1j
                        mVertexBuffer    //3?AIry?D,?r?
                );

        //???e???cw3?AI?k?Vq,?r?
        gl.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer);

        //}?O__?z
        gl.glEnable(GL10.GL_TEXTURE_2D);
        //re3\"I?I__?zSTry?D?w"R
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //???e???cw__?zSTry?D?w"R
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
        //,jcw?O?e__?z
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texId);


        //A,"?1II
        gl.glDrawArrays
                (
                        GL10.GL_TRIANGLES,        //?HT"I?O???R
                        0,                        //}clAI?s,1
                        vCount                    //3?AI????q
                );

        gl.glPopMatrix();//AU-??{3o
    }
}
