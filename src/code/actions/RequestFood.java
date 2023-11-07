package code.actions;

import code.artifacts.LLAPNode;
import code.artifacts.Node;
import code.pending.PendingEnergy;
import code.pending.PendingFood;

public class RequestFood extends RequestResource {

    public RequestFood(String amount, String delay) {
        super(amount, delay, "RequestFood");
    }

    @Override
    public Node perform(Node node) {
        LLAPNode currNode = (LLAPNode) node;
        if (!canPerform(currNode)) {
            return null;
        }
        Node childNode = new LLAPNode(currNode.getProsperity(),
                currNode.getFood() - getFood(),
                currNode.getMaterial() - getMaterial(),
                currNode.getEnergy() - getEnergy(),
                new PendingFood(getDelay() + 1, getAmount()),
                currNode,
                getName(),
                currNode.getCost() + getCost());
        return childNode;
    }
}
