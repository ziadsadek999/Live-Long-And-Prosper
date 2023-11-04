package code.pending;

import code.artifacts.Node;

public class PendingMaterial extends PendingResource {
    public PendingMaterial(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public Node propagate(Node currNode) {
        if (getRemainingTime() == 0) {
            return new Node(currNode.getProsperity(),
                    currNode.getFood(),
                    currNode.getMaterial() + getAmount(),
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
        return new PendingMaterial(getRemainingTime() - 1, getAmount());
    }
}
