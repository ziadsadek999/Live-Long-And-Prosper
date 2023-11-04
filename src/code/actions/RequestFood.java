package code.actions;

import code.artifacts.Node;
import code.pending.IntermediateNode;
import code.pending.PendingFood;
import code.pending.PendingMaterial;

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
        IntermediateNode intermediateNode = currNode.getPendingResource().tick(currNode);
        if (intermediateNode.getPendingResource() == null) {
            intermediateNode.setPendingResource(new PendingFood(getAmount(), getDelay()));
            return new Node(currNode, intermediateNode, getName());
        }
        return null;
    }
}
