package code.strategies;

import code.LLAPSearch;
import code.actions.Action;
import code.artifacts.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

abstract public class GenericSearch {
    Node root;
    private int nodesExpanded = 0;

    public GenericSearch(Node root) {
        this.root = root;
    }

    public Node solve() {
        return null;
    }

    public List<Node> expand(Node node) {
        List<Node> children = new ArrayList<>();
        for (Action action : LLAPSearch.getActions()) {
            Node child = action.perform(node);
            if (child != null)
                children.add(child);
        }
        nodesExpanded++;
        return children;
    }

    protected Node handleQueue(Queue<Node> queue) {
        HashSet<String> explored = new HashSet<>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            LLAPSearch.print("Current Node: " + node);
            if (node.isGoal()) {
                LLAPSearch.print("Goal Reached!");
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

    public int getNodesExpanded() {
        return nodesExpanded;
    }
}
