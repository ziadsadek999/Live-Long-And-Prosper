package code.actions;

import code.artifacts.Node;
import code.pending.IntermediateNode;
import code.pending.PendingResource;

public class Await extends Action {
    public Await() {
        super(1, 1, 1, "WAIT");
    }

    @Override
    public Node perform(Node currNode) {
        if (currNode.getFood() <= 0 || currNode.getMaterial() <= 0 || currNode.getEnergy() <= 0) {
            return null;
        }
        if (currNode.getPendingResource() == null) {
            return new Node(currNode.getProsperity(), currNode.getFood() - 1, currNode.getMaterial() - 1, currNode.getEnergy() - 1, null, currNode, getName());
        }
        IntermediateNode intermediateNode = currNode.getPendingResource().tick(currNode);
        return new Node(currNode, intermediateNode, getName());
    }
}
