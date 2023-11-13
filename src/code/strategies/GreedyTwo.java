package code.strategies;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import code.LLAPSearch;
import code.actions.Build;
import code.artifacts.LLAPNode;
import code.artifacts.Node;

public class GreedyTwo extends GenericSearch {
    public GreedyTwo(Node root) {
        super(root);
    }

    public float GetHueristicValue(Node o) {
        if (o.isGoal()) return 0;
        int remProsperity = (100 - ((LLAPNode) o).getProsperity());

        Build Build1 = (Build) LLAPSearch.getActions().get(5);
        float cost1 = (remProsperity * 1f / Build1.getProsperity()) * Build1.getCost();

        Build Build2 = (Build) LLAPSearch.getActions().get(4);
        float cost2 = (remProsperity * 1f / Build2.getProsperity()) * Build2.getCost();

        return Math.min(cost1, cost2);
    }

    public int CompareBetween(Node node1, Node node2) {
        float totalCostOne = GetHueristicValue(node1);
        float totalCostTwo = GetHueristicValue(node2);

        float comp = totalCostOne - totalCostTwo;
        if (comp < 0) {
            return -1;
        } else if (comp > 0) {
            return 1;
        }
        return 0;
    }

    public Node solve() {
        //priority queue that sorts on node cost
        PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> CompareBetween(o1, o2));

        queue.add(root);
        return handleQueue(queue);
    }
}
