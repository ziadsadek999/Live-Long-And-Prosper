package code.strategies;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import code.artifacts.Node;

public class UniformCostSearch extends GenericSearch {


    public UniformCostSearch(Node root) {
        super(root);
    }

    public Node solve() {
        //priority queue that sorts on node cost
        PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> o1.getCost() - o2.getCost());

        queue.add(root);
        return handleQueue(queue);
    }
}
