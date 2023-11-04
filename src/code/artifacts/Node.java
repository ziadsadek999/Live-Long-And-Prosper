package code.artifacts;

import code.pending.PendingResource;

public class Node {
    private int prosperity;
    private int food;

    private int material;

    private int energy;
    private PendingResource pendingResource;

    private Node parent;

    private String operation;

    public Node(int prosperity, int food, int material, int energy, PendingResource pendingResource, Node parent, String operation) {
        this.prosperity = prosperity;
        this.food = food;
        this.material = material;
        this.energy = energy;
        this.pendingResource = pendingResource;
        this.parent = parent;
        this.operation = operation;
    }

    public Node propagatePendingResource() {
        if (pendingResource == null) {
            return new Node(prosperity, food, material, energy, null, null, null);
        }
        return pendingResource.propagate(this);
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
        this.food = food;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
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

    public void decrementAll() {
        this.food--;
        this.material--;
        this.energy--;
    }
}
