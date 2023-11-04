package code.actions;

abstract public class RequestResource extends Action {
    private final int amount;
    private final int delay;

    public RequestResource(String amount, String delay, String name) {
        super(1, 1, 1, name);
        this.amount = Integer.parseInt(amount);
        this.delay = Integer.parseInt(delay);
    }
}
