package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.HashMap;
import java.util.Random;

import byog.TileEngine.Tileset;
import byog.lab5.HexWorld;

public class MapGenerator {

    //horizontal direction
    //verical direction
    static int Width = 100;
    static int Height = 50;
    static int seed = 5000;
    static int step = 0;
    TETile[][] world = new TETile[Width][Height];
    //pointer
    Position ptr = new Position(0, 0);
    static HashMap<Integer, Position> cacheHMap = new HashMap<>();
    static int[] cacheArr = new int[10];

    private static class Position {
        public int x;
        public int y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private static void storeToCache(int step, int len, Position p){
        cacheArr[step] = len;
        cacheHMap.put(step, p);
        step += 1;
    }

//    private static void generateFloor(TETile[][] world){
//        for (int i = 0; i <= step; i++){
//            Position tempPtr = cacheHMap.get(i);
//            int tempLen = cacheArr[i];
//            drawFloor(world, tempPtr, tempLen);
//        }
//    }
    private static void generateWall(TETile[][] world){
        int cnt = 0, len = 0;
        int minHeight = 15;
        int lowY = Integer.MAX_VALUE;
        TETile t = Tileset.WALL;
        TETile floor = Tileset.FLOOR;
        Random r = new Random(seed);
        //generate the starting point, @ top left
        Position ptr = new Position(  r.nextInt() % 10, Height - r.nextInt() % 10);
        Position startPtr = new Position(ptr.x, ptr.y);
        while (ptr.x < Width - 10){
            len = r.nextInt(seed);
            //move rightwards
            len = len % 10 + 3;
            drawRow(world, ptr, len, t);
            if (cnt == 0) {
                storeToCache(step, len, ptr);
            } else {
                storeToCache(step, len - 2, ptr);
            }
            drawFloor(world, ptr, len, minHeight - 1);
            ptr.x += len;
            //2nd step: draw downwards
            if (cnt % 2 == 0){
                len = r.nextInt(seed);
                len = -(len % 5 + 3);
                if (len % 2 == 1){
                    len = -minHeight;
                }
                drawCol(world, ptr, len, t);
                ptr.y += len;
                lowY = Math.min(ptr.y, lowY);
                //3rd step: draw upwards
            } else {
                len = r.nextInt(seed);
                len = len % 5 + 3;
                drawCol(world, ptr, len, t);
                ptr.y += len;
                lowY = Math.min(ptr.y, lowY);
            }
             cnt += 1;
        }

        len = r.nextInt(seed);
        len = len % 10;
        drawRow(world, ptr, -len, t);
        //draw end row
        Position endPtr = new Position(Width-2, minHeight);
        Position tempPtr = new Position(startPtr.x, minHeight);
        drawRow(world, tempPtr, endPtr.x - startPtr.x, Tileset.WALL);
        drawCol(world, startPtr, endPtr.y - startPtr.y, Tileset.WALL);

    }
        private static void drawFloor(TETile[][] world, Position ptr, int width, int height){
            //int height = 0;
            Position tempPtr = new Position(ptr.x + 1, ptr.y - 1);
            //Random r = new Random(seed);
            //height = r.nextInt(seed);
            //height = height % 5 + 2;
            while (tempPtr.y > height){
                drawRow(world, tempPtr, width, Tileset.FLOOR);
                //height -= 1;
                tempPtr.y -= 1;
            }

        }


    //draw Vertical line, +ve == upwards, -ve == downwards
    private static void drawCol(TETile[][] world, Position start, int len, TETile t){
        int x = start.x;
        int y = start.y;
        //edge cases
        if (!checkValidPos(x, y)) return;
        while (len!= 0 && 0 < y  && y < Height) {
            if (len > 0) {
                world[x][y++] = t;
                len -= 1;
            } else {
                world[x][y--] = t;
                len += 1;
            }
        }
    }

    //draw Horizontal line: +ve == rightwards, -ve == leftwards
    private static void drawRow(TETile[][] world, Position start, int len, TETile t){
        int x = start.x;
        int y = start.y;
        //edge cases
        if (!checkValidPos(x, y)) return;
        while (len!= 0 && 0 < x && x < Width) {
            if (len > 0) {
                world[x++][y] = t;
                len -= 1;

            } else {
                world[x--][y] = t;
                len += 1;
            }
        }
    }

    private static boolean checkValidDraw(TETile[][] world, int x, int y){
        if (world[x][y] == Tileset.WALL){
            return false;
        }
        return true;
    }

    private static boolean checkValidPos(int x, int y){
        if (x < 0 || x > Width || y < 0 || y > Height){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(Width, Height);
        TETile[][] world = new TETile[Width][Height];
        for (int x = 0; x < Width; x += 1) {
            for (int y = 0; y < Height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Position p = new Position(5, 5);
        TETile t = Tileset.WALL;
        MapGenerator.generateWall(world);
        //MapGenerator.generateFloor(world);
        //drawRow(world, p, 110, t);
        //drawCol(world, p, -110, t);
        // draws the world to the screen
        ter.renderFrame(world);
    }

}


