package code.pending;

import code.artifacts.Node;

public class PendingMaterial extends PendingResource {
    public PendingMaterial(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public void propagate(Node currNode) {
        PendingResource newPendingResource = decrementTime();
        if (newPendingResource.getRemainingTime() == 0) {
            currNode.setMaterial(currNode.getMaterial() + getAmount());
            currNode.setPendingResource(null);
        } else {
            currNode.setPendingResource(newPendingResource);
        }
    }

    @Override
    public PendingResource decrementTime() {
        return new PendingMaterial(getRemainingTime() - 1, getAmount());
    }
}
