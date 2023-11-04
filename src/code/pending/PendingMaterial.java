package code.pending;

import code.artifacts.Node;

public class PendingMaterial extends PendingResource {
    public PendingMaterial(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public Node propagate(Node currNode) {
        if (getRemainingTime() == 0) {
            return new Node(currNode.getProsperity(), currNode.getFood(), currNode.getMaterial() + getAmount(), currNode.getEnergy(), null, null, null);
        } else {
            return new Node(currNode.getProsperity(), currNode.getFood(), currNode.getMaterial(), currNode.getEnergy(), decrementTime(), null, null);
        }
    }

    @Override
    public PendingResource decrementTime() {
        return new PendingMaterial(getRemainingTime() - 1, getAmount());
    }
}
