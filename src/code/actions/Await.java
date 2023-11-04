package code.actions;

import code.artifacts.Node;

public class Await extends Action {
    public Await() {
        super(1, 1, 1, "WAIT");
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
                currNode.getPendingResource(),
                currNode,
                getName(),
                currNode.getCost() + getCost());

        return childNode;
    }
}
