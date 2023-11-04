package code.artifacts;

import code.pending.PendingResource;

public class Node {
    private int prosperity;
    private int food;

    private int material;

    private int energy;
    private PendingResource pendingResource;

    private Node parent;

    private int cost;
    private String operation;

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

    public void setProsperity(int prosperity) {
        this.prosperity = prosperity;
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

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void decrementAll() {
        this.food--;
        this.material--;
        this.energy--;
    }
}
