package code.strategies;

import code.artifacts.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch extends GenericSearch {
    public BreadthFirstSearch(Node root) {
        super(root);
    }

    @Override
    public Node solve() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        return handleQueue(queue);
    }
}
