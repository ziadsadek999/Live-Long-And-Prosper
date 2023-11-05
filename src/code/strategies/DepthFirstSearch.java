package code.strategies;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import code.artifacts.Node;

public class DepthFirstSearch extends GenericSearch {

    public DepthFirstSearch(Node root) {
        super(root);
    }

    public Node solve(){
        HashSet<String> explored = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.isGoal()) {
                return node;
            }
            if (explored.contains(node.toString())) {
                continue;
            }
            explored.add(node.toString());
            List<Node> children = expand(node);
            for (Node child : children) {
                if(!explored.contains(child.toString())){
                    stack.push(child);
                }
            }
        }
        return null;
    }

}
