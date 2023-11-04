package code.pending;


public class PendingFood extends PendingResource {
    public PendingFood(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public PendingResource tick() {
        if (this.getRemainingTime() == 0) {
            return null;
        }
        return new PendingFood(this.getRemainingTime() - 1, getAmount());
    }
}
