package code.pending;

public class IntermediateNode {

    private final int food;
    private final int material;
    private final int energy;
    private PendingResource pendingResource;

    public IntermediateNode(int food, int material, int energy, PendingResource pendingResource) {
        this.food = food;
        this.material = material;
        this.energy = energy;
        this.pendingResource = pendingResource;
    }

    public int getFood() {
        return food;
    }

    public int getMaterial() {
        return material;
    }

    public int getEnergy() {
        return energy;
    }

    public PendingResource getPendingResource() {
        return pendingResource;
    }

    public void setPendingResource(PendingResource pendingResource) {
        this.pendingResource = pendingResource;
    }
}
