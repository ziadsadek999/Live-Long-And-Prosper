package code.actions;

import code.artifacts.Node;
import code.pending.PendingFood;

public class RequestFood extends RequestResource {

    public RequestFood(String amount, String delay) {
        super(amount, delay, "RequestFood");
    }

    @Override
    public Node perform(Node currNode) {
        if (currNode.isDead()) {
            return null;
        }
        if (currNode.getPendingResource() == null) {
            return new Node(currNode.getProsperity(), currNode.getFood() - 1, currNode.getMaterial() - 1, currNode.getEnergy() - 1, new PendingFood(getAmount(), getDelay()), currNode, getName());
        }
        Node childNode = currNode.propagatePendingResource();
        if (childNode.getPendingResource() == null) {
            childNode.setPendingResource(new PendingFood(getAmount(), getDelay()));
            childNode.setParent(currNode);
            childNode.setOperation(getName());
            childNode.decrementAll();
            return childNode;
        }
        return null;
    }
}
