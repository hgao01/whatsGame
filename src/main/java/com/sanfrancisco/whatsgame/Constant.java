package com.sanfrancisco.whatsgame;

public class Constant {
    public static final float SPEED = 0.05f; //?h???t??
    public static final float[][] MOVE_XZ = //??v???C???V???h????
            {
                    {0, -SPEED},        //0-North  Z?b?t?V
                    {SPEED, 0},    //1-EAST   X?b???V
                    {0, SPEED},    //2-SOUTH  Z?b???V
                    {-SPEED, 0},    //3-WEST   X?b?t?V
            };
    public static final float NEAR = 0.45f;//?i????????
    public static final float ICON_DIS = NEAR;//????????I???Z??
    public static final float ICON_WIDTH = 0.05f;//????o
    public static final float ICON_HEIGHT = 0.1f;
    //?g?c?a??
    public static final int[][] MAP =//0 ???i?z?L 1?i?z?L
            {
                    {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0}
            };
    public static final int[][] MAP_OBJECT =//???i?J?????m???x?}

            {
                    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            };
    public static final int OBJECT_COUNT = 6;//?i?J??????q
    public static final float WALL_HEIGHT = 2.8f;//?????
    public static final float UNIT_SIZE = 1.4f;//?a???C???l???j?p
    public static final float FLOOR_Y = -1.0f;//?a????Y?y??
    public static final float CELL_Y = FLOOR_Y + WALL_HEIGHT;//?????Y?y??
    public static final int CAMERA_COL = 1;//?_?l??Camera??m
    public static final int CAMERA_ROW = 9;
    public static final float HALF_COLL_SIZE = UNIT_SIZE / 2 - 0.2f;//?I?????o
    public static boolean threadWork = false;//???????????u?@????
}
