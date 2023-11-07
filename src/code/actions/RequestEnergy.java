package code.actions;

import code.LLAPSearch;
import code.artifacts.LLAPNode;
import code.artifacts.Node;
import code.pending.PendingEnergy;


public class RequestEnergy extends RequestResource {

    public RequestEnergy(String amount, String delay) {
        super(amount, delay, "RequestEnergy");
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
                new PendingEnergy(getDelay() + 1, getAmount()),
                currNode,
                getName(),
                currNode.getCost() + getCost());
        return childNode;
    }
}
