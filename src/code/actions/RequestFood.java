package code.actions;

import code.artifacts.Node;
import code.pending.PendingEnergy;
import code.pending.PendingFood;

public class RequestFood extends RequestResource {

    public RequestFood(String amount, String delay) {
        super(amount, delay, "RequestFood");
    }

    @Override
    public Node perform(Node currNode) {
        if (!canPerform(currNode)) {
            return null;
        }
        Node childNode = new Node(currNode.getProsperity(),
                currNode.getFood() - getFood(),
                currNode.getMaterial() - getMaterial(),
                currNode.getEnergy() - getEnergy(),
                new PendingFood(getAmount(), getDelay() + 1),
                currNode,
                getName(),
                currNode.getCost() + getCost());
        return childNode;
    }
}
