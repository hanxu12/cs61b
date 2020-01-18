package lab11.graphs;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        //Don't forget to update distTo, edgeTo, marked, announce()
        if (s == t) {
            return;
        }
        Queue<Integer> fringe = new LinkedList<>();
        fringe.offer(s);
        marked[s] = true;
        //edgeTo[s] = 0;
        distTo[s] = 0;
        announce();
        while (!fringe.isEmpty()) {
            int curr = fringe.remove();
            for (int adj : maze.adj(curr)) {
                if (!marked[adj]) {
                    fringe.offer(adj);
                    marked[adj] = true;
                    edgeTo[adj] = curr;
                    distTo[adj] = distTo[curr] + 1;
                    announce();
                    if (adj == t) {
                        return;
                    }
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs();
    }
}

