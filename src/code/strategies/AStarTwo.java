package code.strategies;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import code.LLAPSearch;
import code.actions.Build;
import code.actions.RequestEnergy;
import code.actions.RequestFood;
import code.actions.RequestMaterial;
import code.actions.RequestResource;
import code.artifacts.LLAPNode;
import code.artifacts.Node;

public class AStarTwo extends GenericSearch {
    public AStarTwo(Node root) {
        super(root);
    }

    public float HandleCosts(float cost, float numBuilds, float resourceFromBuild, float resourceInNode, RequestResource request) {
        float neededFood = resourceFromBuild * numBuilds;
        float foodIHave = resourceInNode;
        float foodToAsk = Math.max(neededFood - foodIHave, 0f);

        float requriedDeliveries = foodToAsk / request.getAmount();
        return requriedDeliveries * request.getCost();
    }

    public float GetCostForBuild(Build build, LLAPNode node) {
        RequestFood reqFood = (RequestFood) LLAPSearch.getActions().get(0);
        RequestMaterial reqMaterial = (RequestMaterial) LLAPSearch.getActions().get(1);
        RequestEnergy reqEnergy = (RequestEnergy) LLAPSearch.getActions().get(2);

        int remProsperity = (100 - (node).getProsperity());
        float numBuilds1 = remProsperity * 1f / build.getProsperity();
        float cost1 = numBuilds1 * build.getCost();
        cost1 = HandleCosts(cost1, numBuilds1, build.getFood(), node.getFood(), reqFood);
        cost1 = HandleCosts(cost1, numBuilds1, build.getMaterial(), node.getMaterial(), reqMaterial);
        cost1 = HandleCosts(cost1, numBuilds1, build.getEnergy(), node.getEnergy(), reqEnergy);

        return cost1;
    }

    public float GetHueristicValue(Node o) {
        if (o.isGoal()) return 0;
        LLAPNode lnode = (LLAPNode) o;

        Build Build1 = (Build) LLAPSearch.getActions().get(5);
        float cost1 = GetCostForBuild(Build1, lnode);

        Build Build2 = (Build) LLAPSearch.getActions().get(4);
        float cost2 = GetCostForBuild(Build2, lnode);

        return Math.min(cost1, cost2);
    }

    public int CompareBetween(Node node1, Node node2) {
        float totalCostOne = GetHueristicValue(node1) + node1.getCost();
        float totalCostTwo = GetHueristicValue(node2) + node2.getCost();

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
