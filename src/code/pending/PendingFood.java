package code.pending;


import code.artifacts.Node;

public class PendingFood extends PendingResource {
    public PendingFood(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public Node propagate(Node currNode) {
        if (getRemainingTime() == 0) {
            return new Node(currNode.getProsperity(),
                    currNode.getFood() + getAmount(),
                    currNode.getMaterial(),
                    currNode.getEnergy(),
                    null,
                    null,
                    null,
                    0);
        } else {
            return new Node(currNode.getProsperity(),
                    currNode.getFood(),
                    currNode.getMaterial(),
                    currNode.getEnergy(),
                    decrementTime(),
                    null,
                    null,
                    0);
        }
    }

    @Override
    public PendingResource decrementTime() {
        return new PendingFood(getRemainingTime() - 1, getAmount());
    }
}
