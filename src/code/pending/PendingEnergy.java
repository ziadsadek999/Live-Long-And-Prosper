package code.pending;

import code.artifacts.Node;

public class PendingEnergy extends PendingResource {

    public PendingEnergy(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public Node tick(Node currNode) {
        if (getRemainingTime() == 0) {
            return new Node(currNode.getProsperity(), currNode.getFood(), currNode.getMaterial(), currNode.getEnergy() + getAmount(), null, null, null);
        } else {
            return new Node(currNode.getProsperity(), currNode.getFood(), currNode.getMaterial(), currNode.getEnergy(), decrementTime(), null, null);
        }
    }

    @Override
    public PendingResource decrementTime() {
        return new PendingEnergy(getRemainingTime() - 1, getAmount());
    }
}
