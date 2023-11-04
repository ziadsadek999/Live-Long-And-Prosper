package code.actions;

import code.LLAPSearch;
import code.artifacts.Node;

import static code.LLAPSearch.*;

abstract public class Action {
    private final int food;
    private final int material;
    private final int energy;
    private final String name;

    public Action(int food, int material, int energy, String name) {
        this.food = food;
        this.material = material;
        this.energy = energy;
        this.name = name;
    }

    abstract public Node perform(Node currNode);

    public int getCost() {
        return food * getFoodPrice() + material * getMaterialPrice() + energy + getEnergyPrice();
    }

    protected String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public int getFood() {
        return food;
    }

    public int getMaterial() {
        return material;
    }

    public boolean canPerform(Node currNode) {
        if (currNode.getFood() < this.getFood() || currNode.getEnergy() < this.getEnergy() || currNode.getMaterial() < this.getMaterial()) {
            return false;
        }
        if (currNode.getCost() + getCost() >= LLAPSearch.MAX_COST) {
            return false;
        }
        return true;
    }
}
