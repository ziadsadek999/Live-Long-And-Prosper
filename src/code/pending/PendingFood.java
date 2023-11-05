package code.pending;


import code.artifacts.Node;

public class PendingFood extends PendingResource {
    public PendingFood(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public void propagate(Node currNode) {
        PendingResource newPendingResource = decrementTime();
        if (newPendingResource.getRemainingTime() == 0) {
            currNode.setFood(currNode.getFood() + getAmount());
            currNode.setPendingResource(null);
        } else {
            currNode.setPendingResource(newPendingResource);
        }
    }

    @Override
    public PendingResource decrementTime() {
        return new PendingFood(getRemainingTime() - 1, getAmount());
    }

    public String toString() {
        return super.toString() + " food";
    }
}
