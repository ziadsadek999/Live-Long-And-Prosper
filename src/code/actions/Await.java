package code.actions;

import code.artifacts.Node;

public class Await extends Action {
    public Await() {
        super(1, 1, 1, "WAIT");
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
                    null,
                    currNode,
                    getName(),
                    getCost() + currNode.getCost());
        }
        Node childNode = currNode.propagatePendingResource();
        childNode.setParent(currNode);
        childNode.setOperation(getName());
        childNode.decrementAll();
        childNode.setCost(getCost() + currNode.getCost());
        return childNode;
    }
}
