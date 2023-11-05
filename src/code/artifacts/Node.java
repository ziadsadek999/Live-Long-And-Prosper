package code.artifacts;

import code.pending.PendingResource;

public class Node {
    private final int prosperity;
    private int food;

    private int material;

    private int energy;
    private PendingResource pendingResource;

    private final Node parent;

    private final int cost;
    private final String operation;

    public Node(int prosperity, int food, int material, int energy, PendingResource pendingResource, Node parent, String operation, int cost) {
        this.prosperity = prosperity;
        setEnergy(energy);
        setFood(food);
        setMaterial(material);
        this.pendingResource = pendingResource;
        this.parent = parent;
        this.operation = operation;
        this.cost = cost;
        propagatePendingResource();
    }

    public void propagatePendingResource() {
        if (pendingResource != null) {
            pendingResource.propagate(this);
        }
    }

    public String toString() {
        return "Prosperity: " + prosperity + " Food: " + food + " Material: " + material + " Energy: " + energy + " PendingResource: " + pendingResource + " Cost: " + cost;
    }

    public String getOperation() {
        return operation;
    }

    public boolean isGoal() {
        return prosperity >= 100;
    }

    public boolean isDead() {
        return food <= 0 || material <= 0 || energy <= 0;
    }

    public int getProsperity() {
        return prosperity;
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

    public int getCost() {
        return cost;
    }

    public PendingResource getPendingResource() {
        return pendingResource;
    }

    public Node getParent() {
        return parent;
    }

    public void setFood(int food) {
        this.food = Math.min(food, 50);
    }

    public void setMaterial(int material) {
        this.material = Math.min(material, 50);
    }

    public void setEnergy(int energy) {
        this.energy = Math.min(energy, 50);
    }

    public void setPendingResource(PendingResource pendingResource) {
        this.pendingResource = pendingResource;
    }
}
