package code.pending;

public class PendingEnergy extends PendingResource {

    public PendingEnergy(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public PendingResource tick() {
        if (this.getRemainingTime() == 0) {
            return null;
        }
        return new PendingEnergy(this.getRemainingTime() - 1, getAmount());
    }
}
