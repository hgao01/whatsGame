package com.sanfrancisco.whatsgame;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import static com.sanfrancisco.whatsgame.Constant.FLOOR_Y;
import static com.sanfrancisco.whatsgame.Constant.MAP;
import static com.sanfrancisco.whatsgame.Constant.UNIT_SIZE;
import static com.sanfrancisco.whatsgame.Constant.WALL_HEIGHT;

//????????O
public class Wall {
    int vCount;//???I??q
    int texId;//???zId
    private FloatBuffer mVertexBuffer;//???I?y????w?R
    private FloatBuffer mTextureBuffer;//???I???z???w?R
    private FloatBuffer mNormalBuffer;//???I?k?V?q???w?R

    public Wall(int texId) {
        this.texId = texId;

        //???I?y??????_?l??================begin============================
        int rows = MAP.length;
        int cols = MAP[0].length;

        ArrayList<Float> alVertex = new ArrayList<Float>();
        ArrayList<Float> alNormal = new ArrayList<Float>();
        ArrayList<Float> alTexture = new ArrayList<Float>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {//??a??????C?@???i??B?z
                if (MAP[i][j] == 1) {//?Y?O?i?z?L???h?n??{??????D

                    //????{?????W???O?_??n????
                    if (i == 0 || MAP[i - 1][j] == 0) {//?Y?O??W???@???W???@?????i?z?L?h?????W????n????
                        float x1 = j * UNIT_SIZE;
                        float y1 = FLOOR_Y;
                        float z1 = i * UNIT_SIZE;

                        float x2 = j * UNIT_SIZE;
                        float y2 = FLOOR_Y + WALL_HEIGHT;
                        float z2 = i * UNIT_SIZE;

                        float x3 = (j + 1) * UNIT_SIZE;
                        float y3 = FLOOR_Y + WALL_HEIGHT;
                        float z3 = i * UNIT_SIZE;

                        float x4 = (j + 1) * UNIT_SIZE;
                        float y4 = FLOOR_Y;
                        float z4 = i * UNIT_SIZE;

                        alVertex.add(x1);
                        alVertex.add(y1);
                        alVertex.add(z1);
                        alVertex.add(x3);
                        alVertex.add(y3);
                        alVertex.add(z3);
                        alVertex.add(x2);
                        alVertex.add(y2);
                        alVertex.add(z2);

                        alVertex.add(x1);
                        alVertex.add(y1);
                        alVertex.add(z1);
                        alVertex.add(x4);
                        alVertex.add(y4);
                        alVertex.add(z4);
                        alVertex.add(x3);
                        alVertex.add(y3);
                        alVertex.add(z3);

                        alTexture.add(0f);
                        alTexture.add(2f);
                        alTexture.add(2f);
                        alTexture.add(0f);
                        alTexture.add(0f);
                        alTexture.add(0f);

                        alTexture.add(0f);
                        alTexture.add(2f);
                        alTexture.add(2f);
                        alTexture.add(2f);
                        alTexture.add(2f);
                        alTexture.add(0f);

                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(1f);

                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(1f);
                    }

                    //?A??{?????U???O?_??n????
                    if (i == rows - 1 || MAP[i + 1][j] == 0) {//?Y?O??U???@???U???@?????i?z?L?h?????U????n????
                        float x1 = j * UNIT_SIZE;
                        float y1 = FLOOR_Y;
                        float z1 = (i + 1) * UNIT_SIZE;

                        float x2 = j * UNIT_SIZE;
                        float y2 = FLOOR_Y + WALL_HEIGHT;
                        float z2 = (i + 1) * UNIT_SIZE;

                        float x3 = (j + 1) * UNIT_SIZE;
                        float y3 = FLOOR_Y + WALL_HEIGHT;
                        float z3 = (i + 1) * UNIT_SIZE;

                        float x4 = (j + 1) * UNIT_SIZE;
                        float y4 = FLOOR_Y;
                        float z4 = (i + 1) * UNIT_SIZE;

                        alVertex.add(x2);
                        alVertex.add(y2);
                        alVertex.add(z2);
                        alVertex.add(x3);
                        alVertex.add(y3);
                        alVertex.add(z3);
                        alVertex.add(x1);
                        alVertex.add(y1);
                        alVertex.add(z1);

                        alVertex.add(x3);
                        alVertex.add(y3);
                        alVertex.add(z3);
                        alVertex.add(x4);
                        alVertex.add(y4);
                        alVertex.add(z4);
                        alVertex.add(x1);
                        alVertex.add(y1);
                        alVertex.add(z1);

                        alTexture.add(0f);
                        alTexture.add(0f);
                        alTexture.add(2f);
                        alTexture.add(0f);
                        alTexture.add(0f);
                        alTexture.add(2f);

                        alTexture.add(2f);
                        alTexture.add(0f);
                        alTexture.add(2f);
                        alTexture.add(2f);
                        alTexture.add(0f);
                        alTexture.add(2f);

                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(-1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(-1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(-1f);

                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(-1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(-1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(-1f);
                    }

                    //?A??{?????????O?_??n????
                    if (j == 0 || MAP[i][j - 1] == 0) {//?Y?O??????@?C??????@?????i?z?L?h??????????n????
                        float x1 = j * UNIT_SIZE;
                        float y1 = FLOOR_Y;
                        float z1 = (i + 1) * UNIT_SIZE;

                        float x2 = j * UNIT_SIZE;
                        float y2 = FLOOR_Y + WALL_HEIGHT;
                        float z2 = (i + 1) * UNIT_SIZE;

                        float x3 = j * UNIT_SIZE;
                        float y3 = FLOOR_Y + WALL_HEIGHT;
                        float z3 = i * UNIT_SIZE;

                        float x4 = j * UNIT_SIZE;
                        float y4 = FLOOR_Y;
                        float z4 = i * UNIT_SIZE;

                        alVertex.add(x1);
                        alVertex.add(y1);
                        alVertex.add(z1);
                        alVertex.add(x3);
                        alVertex.add(y3);
                        alVertex.add(z3);
                        alVertex.add(x2);
                        alVertex.add(y2);
                        alVertex.add(z2);

                        alVertex.add(x1);
                        alVertex.add(y1);
                        alVertex.add(z1);
                        alVertex.add(x4);
                        alVertex.add(y4);
                        alVertex.add(z4);
                        alVertex.add(x3);
                        alVertex.add(y3);
                        alVertex.add(z3);

                        alTexture.add(0f);
                        alTexture.add(2f);
                        alTexture.add(2f);
                        alTexture.add(0f);
                        alTexture.add(0f);
                        alTexture.add(0f);

                        alTexture.add(0f);
                        alTexture.add(2f);
                        alTexture.add(2f);
                        alTexture.add(2f);
                        alTexture.add(2f);
                        alTexture.add(0f);

                        alNormal.add(1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(1f);
                        alNormal.add(0f);
                        alNormal.add(0f);

                        alNormal.add(1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                    }

                    //?A??{?????k???O?_??n????
                    if (j == cols - 1 || MAP[i][j + 1] == 0) {//?Y?O??k???@?C??k???@?????i?z?L?h?????k????n????
                        float x1 = (j + 1) * UNIT_SIZE;
                        float y1 = FLOOR_Y;
                        float z1 = (i + 1) * UNIT_SIZE;

                        float x2 = (j + 1) * UNIT_SIZE;
                        float y2 = FLOOR_Y + WALL_HEIGHT;
                        float z2 = (i + 1) * UNIT_SIZE;

                        float x3 = (j + 1) * UNIT_SIZE;
                        float y3 = FLOOR_Y + WALL_HEIGHT;
                        float z3 = i * UNIT_SIZE;

                        float x4 = (j + 1) * UNIT_SIZE;
                        float y4 = FLOOR_Y;
                        float z4 = i * UNIT_SIZE;

                        alVertex.add(x2);
                        alVertex.add(y2);
                        alVertex.add(z2);
                        alVertex.add(x3);
                        alVertex.add(y3);
                        alVertex.add(z3);
                        alVertex.add(x1);
                        alVertex.add(y1);
                        alVertex.add(z1);

                        alVertex.add(x3);
                        alVertex.add(y3);
                        alVertex.add(z3);
                        alVertex.add(x4);
                        alVertex.add(y4);
                        alVertex.add(z4);
                        alVertex.add(x1);
                        alVertex.add(y1);
                        alVertex.add(z1);

                        alTexture.add(0f);
                        alTexture.add(0f);
                        alTexture.add(2f);
                        alTexture.add(0f);
                        alTexture.add(0f);
                        alTexture.add(2f);

                        alTexture.add(2f);
                        alTexture.add(0f);
                        alTexture.add(2f);
                        alTexture.add(2f);
                        alTexture.add(0f);
                        alTexture.add(2f);

                        alNormal.add(-1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(-1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(-1f);
                        alNormal.add(0f);
                        alNormal.add(0f);

                        alNormal.add(-1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(-1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                        alNormal.add(-1f);
                        alNormal.add(0f);
                        alNormal.add(0f);
                    }
                }
            }
        }


        vCount = alVertex.size() / 3;
        float vertices[] = new float[alVertex.size()];
        for (int i = 0; i < alVertex.size(); i++) {
            vertices[i] = alVertex.get(i);
        }

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

        //???I?k?V?q???_?l??================begin============================
        float normals[] = new float[vCount * 3];
        for (int i = 0; i < vCount * 3; i++) {
            normals[i] = alNormal.get(i);
        }

        ByteBuffer nbb = ByteBuffer.allocateDirect(normals.length * 4);
        nbb.order(ByteOrder.nativeOrder());//?]?w???????
        mNormalBuffer = nbb.asFloatBuffer();//??int???w?R
        mNormalBuffer.put(normals);//?V?w?R?????J???I?????
        mNormalBuffer.position(0);//?]?w?w?R??_?l??m
        //???I?k?V?q???_?l??================end============================

        //???I???z?????_?l??================begin============================
        float textures[] = new float[alTexture.size()];
        for (int i = 0; i < alTexture.size(); i++) {
            textures[i] = alTexture.get(i);
        }


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

        //???????z
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }
}
