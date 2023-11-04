package code.pending;

public class PendingMaterial extends PendingResource {
    public PendingMaterial(int remainingTime, int amount) {
        super(remainingTime, amount);
    }

    @Override
    public PendingResource tick() {
        if (this.getRemainingTime() == 0) {
            return null;
        }
        return new PendingMaterial(this.getRemainingTime() - 1, getAmount());
    }
}
