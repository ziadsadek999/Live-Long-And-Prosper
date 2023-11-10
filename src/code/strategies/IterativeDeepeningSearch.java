package code.strategies;

import code.LLAPSearch;
import code.artifacts.Node;

import java.util.HashSet;
import java.util.Stack;

public class IterativeDeepeningSearch extends GenericSearch {
    public IterativeDeepeningSearch(Node root) {
        super(root);
    }

    public Node solve() {
        int depth = 0;
        while (true) {
            Node result = depthLimitedSearch(root, depth);

            if (result != null) {
                return result;
            }

            depth++;
        }
    }

    private Node depthLimitedSearch(Node startNode, int depthLimit) {
        HashSet<String> explored = new HashSet<>();

        Stack<NodeWithDepth> stack = new Stack<>();
        stack.push(new NodeWithDepth(startNode, 0));

        while (!stack.isEmpty()) {
            NodeWithDepth nodeWithDepth = stack.pop();
            Node currentNode = nodeWithDepth.node;
            int currentDepth = nodeWithDepth.depth;
            LLAPSearch.print("Current Node: " + currentNode);
            if (currentNode.isGoal()) {
                LLAPSearch.print("Goal Reached!");
                return currentNode;
            }

            explored.add(currentNode.toString());

            for (Node child : expand(currentNode)) {
                if (currentDepth + 1 <= depthLimit && !explored.contains(child.toString())) {
                    stack.push(new NodeWithDepth(child, currentDepth + 1));
                }
            }
        }

        return null;
    }


    private class NodeWithDepth {
        Node node;
        int depth;

        public NodeWithDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
