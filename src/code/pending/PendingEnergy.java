package code.pending;

import code.artifacts.Node;

public class PendingEnergy extends PendingResource {

    public PendingEnergy(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public void propagate(Node currNode) {
        PendingResource newPendingResource = decrementTime();
        if (newPendingResource.getRemainingTime() == 0) {
            currNode.setEnergy(currNode.getEnergy() + getAmount());
            currNode.setPendingResource(null);
        } else {
            currNode.setPendingResource(newPendingResource);
        }
    }

    @Override
    public PendingResource decrementTime() {
        return new PendingEnergy(getRemainingTime() - 1, getAmount());
    }
}
