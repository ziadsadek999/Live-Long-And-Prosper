package code.actions;

import code.artifacts.Node;
import code.pending.IntermediateNode;
import code.pending.PendingEnergy;
import code.pending.PendingFood;

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
            return new Node(currNode.getProsperity(), currNode.getFood() - 1, currNode.getMaterial() - 1, currNode.getEnergy() - 1, new PendingEnergy(getAmount(), getDelay()), currNode, getName());
        }
        IntermediateNode intermediateNode = currNode.getPendingResource().tick(currNode);
        if (intermediateNode.getPendingResource() == null) {
            intermediateNode.setPendingResource(new PendingEnergy(getAmount(), getDelay()));
            return new Node(currNode, intermediateNode, getName());
        }
        return null;
    }
}
