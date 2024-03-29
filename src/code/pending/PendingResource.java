package code.pending;

import code.artifacts.LLAPNode;

abstract public class PendingResource {
    private final int remainingTime;
    private final int amount;

    public PendingResource(int remainingTime, int amount) {
        this.remainingTime = remainingTime;
        this.amount = amount;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getAmount() {
        return amount;
    }

    abstract public void propagate(LLAPNode currNode);

    abstract public PendingResource decrementTime();

    public String toString() {
        return " RemainingTime: " + remainingTime + " Amount: " + amount;
    }
}
