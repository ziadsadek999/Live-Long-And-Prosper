package code.artifacts;

import code.pending.PendingResource;

public class LLAPNode extends Node {
    private final int prosperity;

    private int food;
    private int material;
    private int energy;

    private PendingResource pendingResource;


    public LLAPNode(int prosperity, int food, int material, int energy, PendingResource pendingResource, Node parent, String operation, int cost) {
        super(parent, cost, operation);
        this.prosperity = prosperity;
        setEnergy(energy);
        setFood(food);
        setMaterial(material);
        this.pendingResource = pendingResource;
        propagatePendingResource();
    }

    public void propagatePendingResource() {
        if (pendingResource != null) {
            pendingResource.propagate(this);
        }
    }

    public String toString() {
        return "\nProsperity: " + prosperity + "\nFood: " + food + "\nMaterial: " + material + "\nEnergy: " + energy + "\nPendingResource: " + pendingResource + "\nCost: " + getCost() + "\n-------------------------";
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
