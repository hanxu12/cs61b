package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import java.util.Random;

import byog.TileEngine.Tileset;
import byog.lab5.HexWorld;

public class MapGenerator {
    //horizontal direction
    //verical direction
    static int Width = 100;
    static int Height = 50;
    static int seed = 5000;
    TETile[][] world = new TETile[Width][Height];
    //pointer
    Position ptr = new Position(0, 0);

    private static class Position {
        public int x;
        public int y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static void generateWall(TETile[][] world){
        int cnt = 0, len = 0;
        TETile t = Tileset.WALL;
        TETile floor = Tileset.FLOOR;
        Random r = new Random(seed);
        //generate the starting point, @ top left
        Position ptr = new Position(  r.nextInt() % 10, Height - r.nextInt() % 10);
        while (ptr.x <= Width){
            len = r.nextInt(seed);
            //move rightwards
            len = len % 10+2;
            drawRow(world, ptr, len, t);
            //drawFloor(world, ptr, len-2);
            ptr.x += len;
            //2nd step: draw downwards
            if (cnt % 2 == 0){
                len = r.nextInt(seed);
                len = -(len % 5);
                drawCol(world, ptr, len, t);
                ptr.y += len;
                //3rd step: draw upwards
            } else {
                len = r.nextInt(seed);
                len = len % 5;
                drawCol(world, ptr, len, t);
                ptr.y += len;
            }
             cnt += 1;
        }
        len = r.nextInt(seed);
        len = len % 10;
        drawRow(world, ptr, -len, t);
        //go left
//        while (ptr.x >= 0){
//            len = r.nextInt(seed);
//            //move rightwards
//            len = len % 10+1;
//            drawRow(world, ptr, -len, t);
//            ptr.x -= len;
//            //2nd step: draw downwards
//            if (cnt % 2 == 0){
//                len = r.nextInt(seed);
//                len = -(len % 5);
//                drawCol(world, ptr, len, t);
//                ptr.y += len;
//                //3rd step: draw upwards
//            } else {
//                len = r.nextInt(seed);
//                len = len % 5;
//                drawCol(world, ptr, len, t);
//                ptr.y += len;
//            }
//            cnt += 1;
//        }
    }
        private static void drawFloor(TETile[][] world, Position ptr, int width){
            Random r = new Random(seed);
            TETile floor = Tileset.FLOOR;
            int height = r.nextInt(seed) % 10;
            ptr.x += 1;
            ptr.y -= 1;
            while (height > 0) {
                drawRow(world, ptr, width, floor);
                height -= 1;
                ptr.y -= 1;
            }
        }
//    private static int returnLen(TETile[][] world, Position ptr, TETile t){
//        int len = 0;
//
//        while (world[ptr.x][ptr.y + len + 2] != t && checkValidPos(ptr.x, ptr.y + len + 2)){
//            len += 1;
//        }
//        return len;
//    }

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
        //drawRow(world, p, 110, t);
        //drawCol(world, p, -110, t);
        // draws the world to the screen
        ter.renderFrame(world);
    }

}


