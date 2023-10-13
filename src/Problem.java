public class Problem {

    private Resource food;
    private Resource material;
    private Resource energy;
    private boolean visualize;
    private int prosperity;
    private Strategy strategy;

    private Build build1;

    private Build build2;

    public Problem(String initialState, String strategy, boolean visualize) {
        parseInitialState(initialState);
        this.visualize = visualize;
        this.strategy = Strategy.valueOf(strategy);
    }

    private void parseInitialState(String initialState) {
        String[] splitString = initialState.split(";");
        String[][] deepSplitString = new String[splitString.length][];
        for (int i = 0; i < splitString.length; i++) {
            deepSplitString[i] = splitString[i].split(",");
        }
        prosperity = Integer.parseInt(splitString[0]);
        food = new Resource(deepSplitString[1][0], deepSplitString[2][0], deepSplitString[3][0], deepSplitString[3][1]);
        material = new Resource(deepSplitString[1][1], deepSplitString[2][1], deepSplitString[4][0], deepSplitString[4][1]);
        energy = new Resource(deepSplitString[1][2], deepSplitString[2][2], deepSplitString[5][0], deepSplitString[5][1]);
        build1 = new Build(deepSplitString[6][0], deepSplitString[6][1], deepSplitString[6][2], deepSplitString[6][3], deepSplitString[6][4]);
        build2 = new Build(deepSplitString[7][0], deepSplitString[7][1], deepSplitString[7][2], deepSplitString[7][3], deepSplitString[7][4]);
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Initial Prosperity: " + prosperity + "\n");
        output.append("Strategy: " + strategy + "\n");
        output.append("Visualize: " + visualize + "\n");
        output.append("Food:\n" + food + "\n");
        output.append("Material:\n" + material + "\n");
        output.append("Energy:\n" + energy + "\n");
        output.append("Build 1:\n" + build1 + "\n");
        output.append("Build 2:\n" + build2 + "\n");
        return output.toString();
    }
}
