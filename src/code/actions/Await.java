package code.actions;

import code.artifacts.LLAPNode;
import code.artifacts.Node;

public class Await extends Action {
    public Await() {
        super(1, 1, 1, "WAIT");
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
                currNode.getPendingResource(),
                currNode,
                getName(),
                currNode.getCost() + getCost());

        return childNode;
    }

    @Override
    public boolean canPerform(LLAPNode currNode) {
        return super.canPerform(currNode) && currNode.getPendingResource() != null;
    }
}
