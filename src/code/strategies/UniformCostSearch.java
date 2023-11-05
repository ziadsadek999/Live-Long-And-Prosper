package code.strategies;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import code.artifacts.Node;

public class UniformCostSearch extends GenericSearch {


    public UniformCostSearch(Node root) {
        super(root);
    }

    public Node solve(){
        HashMap<String, Integer> explored = new HashMap<>();
         explored.put(root.toString(),root.getCost());
        //priority  Queue that sorts Node on cost 
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.getCost() - o2.getCost());
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.isGoal()) {
                return node;
            }
            if (explored.containsKey(node.toString()) && explored.get(node.toString()) < node.getCost()) {
                continue;
            }
            explored.put(node.toString(),node.getCost());
            List<Node> children = expand(node);
            for (Node child : children) {
               if (!explored.containsKey(child.toString()) || explored.get(child.toString()) > child.getCost()) {
                    explored.put(child.toString(), child.getCost());
                    queue.add(child);
                }
            }
        }
        return null;
    }
}
