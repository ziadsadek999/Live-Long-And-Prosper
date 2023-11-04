package code;

import code.actions.*;
import code.artifacts.Strategy;

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

    public static String solve(String initialState, String strategy, boolean visualize) {
        parse(initialState);
        LLAPSearch.visualise = visualize;
        LLAPSearch.strategy = Strategy.valueOf(strategy);
        return "";
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
        actions.add(new code.actions.Build(splitState[6][0], splitState[6][1], splitState[6][2], splitState[6][3], splitState[6][4]));
        actions.add(new code.actions.Build(splitState[6][0], splitState[6][1], splitState[6][2], splitState[6][3], splitState[6][4]));
        actions.add(new Await());
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
