package code;

import code.actions.*;
import code.artifacts.Node;
import code.artifacts.Strategy;
import code.strategies.BreadthFirstSearch;
import code.strategies.*;
import code.strategies.IterativeDeepeningSearch;

import java.util.*;

public class LLAPSearch {
    private static int initialProsperity;
    private static int initialFood;

    private static int initialMaterial;
    private static int initialEnergy;

    private static int foodPrice;
    private static int materialPrice;
    private static int energyPrice;

    private static boolean visualise;

    private static Strategy strategy;
    private static List<Action> actions;
    public static final int MAX_COST = 100000;


    public static String solve(String initialState, String strategy, boolean visualize) {
        parse(initialState);
        LLAPSearch.visualise = visualize;
        LLAPSearch.strategy = Strategy.valueOf(strategy);
        Node root = new Node(initialProsperity, initialFood, initialMaterial, initialEnergy, null, null, null, 0);
        Strategy strategyEnum = Strategy.valueOf(strategy);
        GenericSearch genericSearch = null;
        switch (strategyEnum) {
            case BF -> {
                genericSearch = new BreadthFirstSearch(root);
            }
            case DF -> {
                genericSearch = new code.strategies.DepthFirstSearch(root);
            }
            case ID -> {
                genericSearch = new IterativeDeepeningSearch(root);
            }
            case UC -> {
                genericSearch = new code.strategies.UniformCostSearch(root);
            }
            case GR1 -> {
                genericSearch = new code.strategies.GreedyOne(root);
            }
            case GR2 -> {
                genericSearch = new code.strategies.GreedyTwo(root);
            }
            case AS1 -> {
                genericSearch = new code.strategies.AStarOne(root);
            }
            case AS2 -> {
                genericSearch = new code.strategies.AStarTwo(root);
            }
        }
        Node goal = genericSearch.solve();
        String result = getPlan(goal) + ";" + goal.getCost() + ";" + genericSearch.getNodesExpanded();
        System.out.println(result);
        System.out.println(LLAPSearch.string());
        return result;
    }

    public static String getPlan(Node goal) {
        StringBuilder path = new StringBuilder();
        Node currNode = goal;
        while (currNode.getParent() != null) {
            path.insert(0, currNode.getOperation() + ",");
            currNode = currNode.getParent();
        }
        return path.toString().substring(0, path.length() - 1);
    }


    private static void parse(String initialState) {
        String[][] splitState = splitState(initialState);
        setInitialValues(splitState);
        setActions(splitState);
    }

    private static String[][] splitState(String initialState) {
        String[] splitString = initialState.split(";");
        String[][] deepSplitString = new String[splitString.length][];
        for (int i = 0; i < splitString.length; i++) {
            deepSplitString[i] = splitString[i].split(",");
        }
        return deepSplitString;
    }

    private static void setInitialValues(String[][] splitState) {
        initialProsperity = Integer.parseInt(splitState[0][0]);
        initialFood = Integer.parseInt(splitState[1][0]);
        initialMaterial = Integer.parseInt(splitState[1][1]);
        initialEnergy = Integer.parseInt(splitState[1][2]);
        foodPrice = Integer.parseInt(splitState[2][0]);
        materialPrice = Integer.parseInt(splitState[2][1]);
        energyPrice = Integer.parseInt(splitState[2][2]);
    }

    private static void setActions(String[][] splitState) {
        actions = new ArrayList<>();
        actions.add(new RequestFood(splitState[3][0], splitState[3][1]));
        actions.add(new RequestMaterial(splitState[4][0], splitState[4][1]));
        actions.add(new RequestEnergy(splitState[5][0], splitState[5][1]));
        actions.add(new Build(splitState[6][0], splitState[6][1], splitState[6][2], splitState[6][3], splitState[6][4], 1));
        actions.add(new Build(splitState[7][0], splitState[7][1], splitState[7][2], splitState[7][3], splitState[7][4], 2));
        actions.add(new Await());
    }

    public static String string() {
        String output = "";
        output += "Initial Prosperity: " + initialProsperity + "\n";
        output += "Strategy: " + strategy + "\n";
        output += "Visualize: " + visualise + "\n";
        output += "Food:\n" + initialFood + "\n";
        output += "Material:\n" + initialMaterial + "\n";
        output += "Energy:\n" + initialEnergy + "\n";
        output += "Food Price:\n" + foodPrice + "\n";
        output += "Material Price:\n" + materialPrice + "\n";
        output += "Energy Price:\n" + energyPrice + "\n";
        for (Action action : actions) {
            output += action.toString() + "\n";
        }
        return output;
    }

    public static int getInitialProsperity() {
        return initialProsperity;
    }

    public static int getInitialFood() {
        return initialFood;
    }

    public static int getInitialMaterial() {
        return initialMaterial;
    }

    public static int getInitialEnergy() {
        return initialEnergy;
    }

    public static int getFoodPrice() {
        return foodPrice;
    }

    public static int getMaterialPrice() {
        return materialPrice;
    }

    public static int getEnergyPrice() {
        return energyPrice;
    }

    public static boolean getVisualise() {
        return visualise;
    }

    public static Strategy getStrategy() {
        return strategy;
    }

    public static List<Action> getActions() {
        return actions;
    }
}


