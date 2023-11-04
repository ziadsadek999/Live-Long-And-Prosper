package actions;

abstract public class RequestResource extends Action {
    private final int amount;
    private final int delay;

    public RequestResource(String amount, String delay) {
        super(1, 1, 1);
        this.amount = Integer.parseInt(amount);
        this.delay = Integer.parseInt(delay);
    }
}
