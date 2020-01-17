package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.List;

public class Solver {
    private class SearchNode {
        WorldState ws;
        SearchNode prevNode;
        int moveSoFar;


        public SearchNode(WorldState ws, int moveSoFar, SearchNode prevNode) {
            this.ws = ws;
            this.moveSoFar = moveSoFar;
            this.prevNode = prevNode;
        }

    }

//    Comparator<SearchNode> searchNodeComparator = new Comparator<SearchNode>() {
//        @Override
//        public int compare(SearchNode o1, SearchNode o2) {
//            return (o1.moveSoFar + o1.ws.estimatedDistanceToGoal())
//            - (o2.moveSoFar + o2.ws.estimatedDistanceToGoal());
//        }
//    };

    private MinPQ<SearchNode> moveSequence = new MinPQ<SearchNode>(1, (o1, o2) ->
            ((o1.moveSoFar + o1.ws.estimatedDistanceToGoal())
                    - (o2.moveSoFar + o2.ws.estimatedDistanceToGoal())));
    
    private List<WorldState> res = new ArrayList<WorldState>();
    /**
     * Constructor which solves the puzzle.
     * Computing everything necessary for moves() and solution()
     * to not have to solve the problem again.
     * Solves the problem using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        SearchNode firstNode = new SearchNode(initial, 0, null);
        moveSequence.insert(firstNode);
//        while (true) {
//            SearchNode min = moveSequence.delMin();
//            res.add(min.ws); //bug is here
//            additionCnt += 1;
//            if (min.ws.estimatedDistanceToGoal() == 0) {
//                moveCnt = min.moveSoFar;
//                break;
//            } else {
//                Iterable<WorldState> neighbors = min.ws.neighbors();
//                for (WorldState tempWS : neighbors) {
//                    SearchNode tempNode = new SearchNode(tempWS, min.moveSoFar + 1, min);
//                    //optimization: do not enqueue a neighbor if the
//                    //same as the prev search node's worldState
//                    if (tempNode.ws.equals(min.prevNode.ws)){
//                        continue;
//                    }
//                    moveSequence.insert(tempNode);
//                }
//            }
//        }
        while (!moveSequence.min().ws.isGoal()) {
            SearchNode min = moveSequence.delMin();
            for (WorldState neighbor : min.ws.neighbors()) {
                SearchNode tempNode = new SearchNode(neighbor, min.moveSoFar + 1, min);
                    //optimization: do not enqueue a neighbor
                // if the same as the prev search node's worldState
                if (min.prevNode == null || !neighbor.equals(min.prevNode.ws)) {
                    moveSequence.insert(tempNode);
                }
            }
        }
        SearchNode s = moveSequence.min();
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
