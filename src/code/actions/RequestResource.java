package code.actions;

import code.artifacts.Node;

abstract public class RequestResource extends Action {
    private final int amount;
    private final int delay;

    public RequestResource(String amount, String delay, String name) {
        super(1, 1, 1, name);
        this.amount = Integer.parseInt(amount);
        this.delay = Integer.parseInt(delay);
    }

    public int getAmount() {
        return amount;
    }

    public int getDelay() {
        return delay;
    }

    public boolean canPerform(Node currNode) {
        return super.canPerform(currNode) && currNode.getPendingResource() == null;
    }
}
