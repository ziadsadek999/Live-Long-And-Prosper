public class Problem {

    private final Resource food;
    private final Resource materials;
    private final Resource energy;
    private final boolean visualize;
    private final int initialProsperity;
    private final Strategy strategy;

    private final Build build1;

    private final Build build2;

    public Problem(String initialState, String strategy, boolean visualize) {
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

    public String solve() {
        String plan = "";
        String cost = "";
        String nodesExpanded = "";
        return plan + ";" + cost + ";" + nodesExpanded;
    }

    public String toString() {
        String output = "";
        output += "Initial Prosperity: " + initialProsperity + "\n";
        output += "Strategy: " + strategy + "\n";
        output += "Visualize: " + visualize + "\n";
        output += "Food:\n" + food + "\n";
        output += "Material:\n" + materials + "\n";
        output += "Energy:\n" + energy + "\n";
        output += "Build 1:\n" + build1 + "\n";
        output += "Build 2:\n" + build2 + "\n";
        return output;
    }
}
