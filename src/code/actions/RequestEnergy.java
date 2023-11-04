package code.actions;

import code.artifacts.Node;
import code.pending.PendingEnergy;


public class RequestEnergy extends RequestResource {

    public RequestEnergy(String amount, String delay) {
        super(amount, delay, "RequestEnergy");
    }

    @Override
    public Node perform(Node currNode) {
        if (currNode.isDead()) {
            return null;
        }
        if (currNode.getPendingResource() == null) {
            return new Node(currNode.getProsperity(),
                    currNode.getFood() - 1,
                    currNode.getMaterial() - 1,
                    currNode.getEnergy() - 1,
                    new PendingEnergy(getAmount(),
                            getDelay()),
                    currNode,
                    getName(),
                    getCost() + currNode.getCost());
        }
        Node childNode = currNode.propagatePendingResource();
        if (childNode.getPendingResource() == null) {
            childNode.setPendingResource(new PendingEnergy(getAmount(), getDelay()));
            childNode.setParent(currNode);
            childNode.setOperation(getName());
            childNode.decrementAll();
            childNode.setCost(getCost() + currNode.getCost());
            return childNode;
        }
        return null;
    }
}
