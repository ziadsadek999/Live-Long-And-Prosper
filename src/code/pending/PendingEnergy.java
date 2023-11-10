package code.pending;

import code.artifacts.LLAPNode;

public class PendingEnergy extends PendingResource {

    public PendingEnergy(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public void propagate(LLAPNode currNode) {
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

    public String toString() {
        return "(Energy " + super.toString() + ")";
    }
}
