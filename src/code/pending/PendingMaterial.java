package code.pending;

import code.artifacts.Node;

public class PendingMaterial extends PendingResource {
    public PendingMaterial(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public IntermediateNode tick(Node currNode) {
        int newFood = currNode.getFood() - 1;
        int newMaterial = currNode.getMaterial() - 1;
        int newEnergy = currNode.getEnergy() - 1;
        if (getRemainingTime() == 0) {
            return new IntermediateNode(newFood, newMaterial + getAmount(), newEnergy, null);
        } else {
            return new IntermediateNode(newFood, newMaterial, newEnergy, decrementTime());
        }
    }

    @Override
    public PendingResource decrementTime() {
        return new PendingMaterial(getRemainingTime() - 1, getAmount());
    }
}
