package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;

public class Solver {
    public class searchNode {
        WorldState ws;
        searchNode prevNode;
        int moveSoFar;


        public searchNode(WorldState ws, int moveSoFar, searchNode prevNode) {
            this.ws = ws;
            this.moveSoFar = moveSoFar;
            this.prevNode = prevNode;
        }

    }

//    Comparator<searchNode> searchNodeComparator = new Comparator<searchNode>() {
//        @Override
//        public int compare(searchNode o1, searchNode o2) {
//            return (o1.moveSoFar + o1.ws.estimatedDistanceToGoal()) - (o2.moveSoFar + o2.ws.estimatedDistanceToGoal());
//        }
//    };

    MinPQ<searchNode> moveSequence = new MinPQ<searchNode>(1, (o1, o2) ->
            ((o1.moveSoFar + o1.ws.estimatedDistanceToGoal()) - (o2.moveSoFar + o2.ws.estimatedDistanceToGoal())));
    //Set<WorldState> res;
    List<WorldState> res = new ArrayList<WorldState>();
    int moveCnt;
    int additionCnt = 0;
    /**
     * Constructor which solves the puzzle.
     * Computing everything necessary for moves() and solution() to not have to solve the problem again.
     * Solves the problem using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        searchNode firstNode = new searchNode(initial, 0, null);
        moveSequence.insert(firstNode);
//        while (true) {
//            searchNode min = moveSequence.delMin();
//            res.add(min.ws); //bug is here
//            additionCnt += 1;
//            if (min.ws.estimatedDistanceToGoal() == 0) {
//                moveCnt = min.moveSoFar;
//                break;
//            } else {
//                Iterable<WorldState> neighbors = min.ws.neighbors();
//                for (WorldState tempWS : neighbors) {
//                    searchNode tempNode = new searchNode(tempWS, min.moveSoFar + 1, min);
//                    //optimization: do not enqueue a neighbor if the same as the prev search node's worldState
//                    if (tempNode.ws.equals(min.prevNode.ws)){
//                        continue;
//                    }
//                    moveSequence.insert(tempNode);
//                }
//            }
//        }
        while (!moveSequence.min().ws.isGoal()) {
            searchNode min = moveSequence.delMin();
            for (WorldState neighbor : min.ws.neighbors()) {
                searchNode tempNode = new searchNode(neighbor, min.moveSoFar + 1, min);
                    //optimization: do not enqueue a neighbor if the same as the prev search node's worldState
                if (min.prevNode == null || !neighbor.equals(min.prevNode.ws)){
                    moveSequence.insert(tempNode);
                }
            }
        }
        searchNode s = moveSequence.min();
        while (s != null) {
            //add new item to the front
            res.add(0, s.ws);
            s = s.prevNode;
        }
    }

    /**
     * Returns the min number of moves to solve the puzzle starting at the initial WorldState.
     */
    public int moves() {
        return res.size() - 1;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState to the solution.
     */
    public Iterable<WorldState> solution() {
        return res;
    }

}
