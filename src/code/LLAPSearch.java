package code;

import code.actions.*;
import code.artifacts.LLAPNode;
import code.artifacts.Node;
import code.artifacts.Strategy;
import code.strategies.BreadthFirstSearch;
import code.strategies.*;
import code.strategies.IterativeDeepeningSearch;

import java.io.PrintWriter;
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
    public static PrintWriter writer;

    public static String solve(String initialState, String strategy, boolean visualize) {
        parse(initialState);
        writer = new PrintWriter(System.out);
        LLAPSearch.visualise = visualize;
        LLAPSearch.strategy = Strategy.valueOf(strategy);
        Node root = new LLAPNode(initialProsperity, initialFood, initialMaterial, initialEnergy, null, null, null, 0);
        Strategy strategyEnum = Strategy.valueOf(strategy);
        GenericSearch genericSearchAlgorithm = getGenericSearchAlgorithm(strategyEnum, root);

        Node goal = genericSearchAlgorithm.solve();

        if (goal == null) {
            print("NOSOLUTION");
            return "NOSOLUTION";
        }

        String result = getPlan(goal) + ";" + goal.getCost() + ";" + genericSearchAlgorithm.getNodesExpanded();
        visualize(goal);
        print(result);
        writer.flush();
        return result;
    }

    private static GenericSearch getGenericSearchAlgorithm(Strategy strategyEnum, Node root) {
        GenericSearch genericSearch = null;
        switch (strategyEnum) {
            case BF -> {
                genericSearch = new BreadthFirstSearch(root);
            }
            case DF -> {
                genericSearch = new DepthFirstSearch(root);
            }
            case ID -> {
                genericSearch = new IterativeDeepeningSearch(root);
            }
            case UC -> {
                genericSearch = new UniformCostSearch(root);
            }
            case GR1 -> {
                genericSearch = new GreedyOne(root);
            }
            case GR2 -> {
                genericSearch = new GreedyTwo(root);
            }
            case AS1 -> {
                genericSearch = new AStarOne(root);
            }
            case AS2 -> {
                genericSearch = new AStarTwo(root);
            }
        }

        return genericSearch;
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

    public static List<Node> getPath(Node goal){
        LinkedList<Node> path = new LinkedList<>();
        Node currNode = goal;
        while(currNode!=null){
            path.addFirst(currNode);
            currNode = currNode.getParent();
        }
        return path;
    }

    public static void visualize (Node goal){
        if(visualise){
            List<Node> path = getPath(goal);
            for(Node node: path){
                if(node.getOperation()!=null){
                    print("   |");
                    print(node.getOperation());
                    print("   |");
                }
                print(node.toString());
            }
        }
    }

    public static void print(String string) {
        if (visualise) {
            writer.println(string);
        }
    }

    private static void parse(String initialState) {
        System.out.println(initialState);
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
        actions.add(new Await());
        actions.add(new Build(splitState[7][0], splitState[7][1], splitState[7][2], splitState[7][3], splitState[7][4], 2));
        actions.add(new Build(splitState[6][0], splitState[6][1], splitState[6][2], splitState[6][3], splitState[6][4], 1));
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


    public static int getFoodPrice() {
        return foodPrice;
    }

    public static int getMaterialPrice() {
        return materialPrice;
    }

    public static int getEnergyPrice() {
        return energyPrice;
    }

    public static List<Action> getActions() {
        return actions;
    }
}


