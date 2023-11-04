package code.pending;


import code.artifacts.Node;

public class PendingFood extends PendingResource {
    public PendingFood(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public Node propagate(Node currNode) {
        if (getRemainingTime() == 0) {
            return new Node(currNode.getProsperity(), currNode.getFood() + getAmount(), currNode.getMaterial(), currNode.getEnergy(), null, null, null);
        } else {
            return new Node(currNode.getProsperity(), currNode.getFood(), currNode.getMaterial(), currNode.getEnergy(), decrementTime(), null, null);
        }
    }

    @Override
    public PendingResource decrementTime() {
        return new PendingFood(getRemainingTime() - 1, getAmount());
    }
}
