package strategies;

import artifacts.*;

abstract public class GenericSearch {

    protected final Resource food;
    protected final Resource materials;
    protected final Resource energy;
    protected final boolean visualize;
    protected final int initialProsperity;
    protected final Strategy strategy;

    protected final Build build1;

    protected final Build build2;

    public GenericSearch(String initialState, String strategy, boolean visualize) {
        String[] splitString = initialState.split(";");
        String[][] deepSplitString = new String[splitString.length][];
        for (int i = 0; i < splitString.length; i++) {
            deepSplitString[i] = splitString[i].split(",");
        }
        initialProsperity = Integer.parseInt(splitString[0]);
        food = new Resource(deepSplitString[1][0], deepSplitString[2][0], deepSplitString[3][0], deepSplitString[3][1], ResourceType.FOOD);
        materials = new Resource(deepSplitString[1][1], deepSplitString[2][1], deepSplitString[4][0], deepSplitString[4][1], ResourceType.MATERIALS);
        energy = new Resource(deepSplitString[1][2], deepSplitString[2][2], deepSplitString[5][0], deepSplitString[5][1], ResourceType.ENERGY);
        build1 = new Build(deepSplitString[6][0], deepSplitString[6][1], deepSplitString[6][2], deepSplitString[6][3], deepSplitString[6][4]);
        build2 = new Build(deepSplitString[7][0], deepSplitString[7][1], deepSplitString[7][2], deepSplitString[7][3], deepSplitString[7][4]);
        this.visualize = visualize;
        this.strategy = Strategy.valueOf(strategy);
    }

    abstract public String solve();


    public String toString() {
        String output = "";
        output += "Initial Prosperity: " + initialProsperity + "\n";
        output += "artifacts.Strategy: " + strategy + "\n";
        output += "Visualize: " + visualize + "\n";
        output += "Food:\n" + food + "\n";
        output += "Material:\n" + materials + "\n";
        output += "Energy:\n" + energy + "\n";
        output += "artifacts.Build 1:\n" + build1 + "\n";
        output += "artifacts.Build 2:\n" + build2 + "\n";
        return output;
    }
}
