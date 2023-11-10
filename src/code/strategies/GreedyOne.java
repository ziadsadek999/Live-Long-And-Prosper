package code.strategies;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import code.artifacts.LLAPNode;
import code.artifacts.Node;

public class GreedyOne extends GenericSearch {

    public GreedyOne(Node root) {
        super(root);
    }

    public int GetHueristicValue(Node o) {
        if (o.isGoal()) return 0;
        return (100 - ((LLAPNode) o).getProsperity());
    }

    public Node solve() {
        //priority queue that sorts on node cost 
        PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) ->
                GetHueristicValue(o1) - GetHueristicValue(o2)
        );

        queue.add(root);
        return handleQueue(queue);
    }
}
