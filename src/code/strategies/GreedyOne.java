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
        return (100 - ((LLAPNode)o).getProsperity());
    }

    public Node solve(){
        HashSet<String> explored = new HashSet<>();
        //priority queue that sorts on node cost 
        PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> 
            GetHueristicValue(o1) - GetHueristicValue(o2)
        );

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
