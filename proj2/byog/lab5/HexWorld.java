package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static class Position {
        public int x;
        public int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    //s == size; p == lower left corner
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t){
        Position upperP = new Position(p.x, p.y + 2 * s - 1);
        drawTopHexagon(world, upperP, s, t);
        drawBtmHexagon(world, p, s, t);
    }

    //p == upper left corner;
    private static void drawTopHexagon(TETile[][] world, Position p, int s, TETile t){
        int i = 0;
        for (int y = p.y; y > p.y - s; y--){
            for (int x = p.x - i; x < p.x + s + i; x++){
                world[x][y] = t;
            }
            i += 1;
        }

    }

    //p == lower left corner
    public static void drawBtmHexagon(TETile[][] world, Position p, int s, TETile t){
        int i = 0;
        for (int y = p.y; y < p.y + s; y++){
            for (int x = p.x - i; x < p.x + s + i; x++){
                world[x][y] = t;
            }
            i += 1;
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        final int WIDTH = 60;
        final int HEIGHT = 30;

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        TETile t = Tileset. FLOWER;
        // initialize tiles

        Position p = new Position(10, 10);
        addHexagon(world, p, 5, t);
        // draws the world to the screen
        ter.renderFrame(world);
    }

}
