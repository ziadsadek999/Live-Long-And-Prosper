package code.strategies;

import code.artifacts.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch extends GenericSearch {
    HashSet<String> explored = new HashSet<>();

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
            if (explored.contains(node.toString())) {
                continue;
            }
            explored.add(node.toString());
            List<Node> children = expand(node);
            for (Node child : children) {
                queue.add(child);
            }
        }
        return null;
    }
}
