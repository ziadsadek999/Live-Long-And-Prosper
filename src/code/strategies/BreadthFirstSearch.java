package code.strategies;

import code.artifacts.Node;

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
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.isGoal()) {
                return node;
            }
            List<Node> children = expand(node);
            for (Node child : children) {
                queue.add(child);
            }
        }
        return null;
    }
}
