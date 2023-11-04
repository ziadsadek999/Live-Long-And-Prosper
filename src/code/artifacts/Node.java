package code.artifacts;

import code.pending.IntermediateNode;
import code.pending.PendingResource;

public class Node {
    private final int prosperity;
    private final int food;

    private final int material;

    private final int energy;
    private final PendingResource pendingResource;

    private final Node parent;

    private final String operation;

    public Node(int prosperity, int food, int material, int energy, PendingResource pendingResource, Node parent, String operation) {
        this.prosperity = prosperity;
        this.food = food;
        this.material = material;
        this.energy = energy;
        this.pendingResource = pendingResource;
        this.parent = parent;
        this.operation = operation;
    }

    public Node(Node parent, IntermediateNode intermediateNode, String operation) {
        this.prosperity = parent.prosperity;
        this.food = intermediateNode.getFood();
        this.material = intermediateNode.getMaterial();
        this.energy = intermediateNode.getEnergy();
        this.pendingResource = intermediateNode.getPendingResource();
        this.parent = parent;
        this.operation = operation;
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
}
