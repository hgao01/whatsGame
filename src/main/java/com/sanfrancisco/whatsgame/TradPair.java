package com.sanfrancisco.whatsgame;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import static com.sanfrancisco.whatsgame.Constant.UNIT_SIZE;
import static com.sanfrancisco.whatsgame.Constant.WALL_HEIGHT;

//????@???????O
public class TradPair {
    int vCount;//???I??q
    int texId;//???zID
    private FloatBuffer mVertexBuffer;//???I?y????w?R
    private FloatBuffer mNormalBuffer;//???I?k?V?q???w?R
    private FloatBuffer mTextureBuffer;//???I?????w?R

    public TradPair(int texId) {
        this.texId = texId;

        //???I?y??????_?l??================begin============================
        vCount = 24;//???????|????8??T????24????I
        float vScale = 0.6f;
        float hScale = 0.15f;
        float vertices[] = new float[]
                {
                        0, WALL_HEIGHT * vScale, 0,
                        UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, UNIT_SIZE * hScale,
                        UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, -UNIT_SIZE * hScale,

                        0, WALL_HEIGHT * vScale, 0,
                        UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, -UNIT_SIZE * hScale,
                        -UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, -UNIT_SIZE * hScale,

                        0, WALL_HEIGHT * vScale, 0,
                        -UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, -UNIT_SIZE * hScale,
                        -UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, UNIT_SIZE * hScale,

                        0, WALL_HEIGHT * vScale, 0,
                        -UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, UNIT_SIZE * hScale,
                        UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, UNIT_SIZE * hScale,

                        0, 0, 0,
                        UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, -UNIT_SIZE * hScale,
                        UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, UNIT_SIZE * hScale,

                        0, 0, 0,
                        -UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, -UNIT_SIZE * hScale,
                        UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, -UNIT_SIZE * hScale,

                        0, 0, 0,
                        -UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, UNIT_SIZE * hScale,
                        -UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, -UNIT_SIZE * hScale,

                        0, 0, 0,
                        UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, UNIT_SIZE * hScale,
                        -UNIT_SIZE * hScale, WALL_HEIGHT * vScale / 2, UNIT_SIZE * hScale,
                };

        //?????I?y????w?R
        //vertices.length*4?O?]???@????|?????
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());//?]?w???????
        mVertexBuffer = vbb.asFloatBuffer();//??int???w?R
        mVertexBuffer.put(vertices);//?V?w?R?????J???I?y????
        mVertexBuffer.position(0);//?]?w?w?R??_?l??m
        //?S?O????G???P???x?????????P???????O??????@?w?n?g??ByteBuffer
        //???A????O?n?z?LByteOrder?]?wnativeOrder()?A?_?h???i??|?X???D
        //???I?y??????_?l??================end============================

        //???I?k?V?q?????_?l??================begin========================
        float normals[] = new float[]
                {
                        1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
                        0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1,
                        -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0,
                        0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1,

                        1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
                        0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1,
                        -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0,
                        0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1,
                };

        ByteBuffer nbb = ByteBuffer.allocateDirect(normals.length * 4);
        nbb.order(ByteOrder.nativeOrder());//?]?w???????
        mNormalBuffer = nbb.asFloatBuffer();//??int???w?R
        mNormalBuffer.put(normals);//?V?w?R?????J???I?????
        mNormalBuffer.position(0);//?]?w?w?R??_?l??m
        //?S?O????G???P???x?????????P???????O??????@?w?n?g??ByteBuffer
        //???A????O?n?z?LByteOrder?]?wnativeOrder()?A?_?h???i??|?X???D
        //???I???????_?l??================end============================

        //???I???z?????_?l??================begin============================
        float textures[] = new float[]
                {
                        0.5f, 0f, 0f, 1f, 1f, 1f,
                        0.5f, 0f, 0f, 1f, 1f, 1f,
                        0.5f, 0f, 0f, 1f, 1f, 1f,
                        0.5f, 0f, 0f, 1f, 1f, 1f,
                        0.5f, 0f, 0f, 1f, 1f, 1f,
                        0.5f, 0f, 0f, 1f, 1f, 1f,
                        0.5f, 0f, 0f, 1f, 1f, 1f,
                        0.5f, 0f, 0f, 1f, 1f, 1f,
                };


        //?????I???z???w?R
        ByteBuffer tbb = ByteBuffer.allocateDirect(textures.length * 4);
        tbb.order(ByteOrder.nativeOrder());//?]?w???????
        mTextureBuffer = tbb.asFloatBuffer();//??Float???w?R
        mTextureBuffer.put(textures);//?V?w?R?????J???I?????
        mTextureBuffer.position(0);//?]?w?w?R??_?l??m
        //?S?O????G???P???x?????????P???????O??????@?w?n?g??ByteBuffer
        //???A????O?n?z?LByteOrder?]?wnativeOrder()?A?_?h???i??|?X???D
        //???I???z?????_?l??================end============================
    }

    public void drawSelf(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//?????I?y??}?C
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);//?????I?k?V?q?}?C

        //???e?????w???I?y????
        gl.glVertexPointer
                (
                        3,                //?C????I???y???q??3  xyz
                        GL10.GL_FLOAT,    //???I?y???????A?? GL_FIXED
                        0,                //?s???I?y????????????j
                        mVertexBuffer    //???I?y????
                );

        //???e?????w???I?k?V?q???
        gl.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer);

        //?}????z
        gl.glEnable(GL10.GL_TEXTURE_2D);
        //?e?\?????zST?y??w?R
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //???e?????w???zST?y??w?R
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
        //?j?w??e???z
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texId);

        //?????
        gl.glDrawArrays
                (
                        GL10.GL_TRIANGLES,        //?H?T????????R
                        0,
                        vCount
                );
    }
}
