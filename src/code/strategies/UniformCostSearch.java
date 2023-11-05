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

    public Node solve(){
        HashSet<String> explored = new HashSet<>();
        //priority queue that sorts on node cost 
        PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> o1.getCost() - o2.getCost());

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
                if(!explored.contains(child.toString())){
                    queue.add(child);
                }
            }
        }
        return null;
    }
}
