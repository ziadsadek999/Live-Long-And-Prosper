package code.actions;

import code.LLAPSearch;
import code.artifacts.Node;

import static code.LLAPSearch.*;

abstract public class Action {
    private final int food;
    private final int material;
    private final int energy;

    public Action(int food, int material, int energy) {
        this.food = food;
        this.material = material;
        this.energy = energy;
    }

    abstract public Node perform(Node currNode);

    public int getCost() {
        return food * getFoodPrice() + material * getMaterialPrice() + energy + getEnergyPrice();
    }
}
