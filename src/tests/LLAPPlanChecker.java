package tests;

import java.util.ArrayList;
import java.util.HashMap;

public class LLAPPlanChecker {
    int prosperity;
    int food;
    int material;
    int energy;
    HashMap<String, Integer> resourcePrice;
    
    /*
     * 0: cost to deduct
     * 1: food to deduct
     * 2: material to deduct
     * 3: eneregy to deduct
     * 4: prosperity to add
     */
    
    ArrayList<Integer> foodInfo;
    ArrayList<Integer> materialInfo;
    ArrayList<Integer> energyInfo;
    ArrayList<Integer> waitInfo;
    ArrayList<Integer> v9;
    ArrayList<Integer> v10;

    int maxMoney = 100000;
    int currentCost = 0;

    int timeRemForReqResource = 0;
    int maxResource = 50;

    int currentRequestedResource = -1;

    public LLAPPlanChecker(String str) {

        String[] splitState = str.split(";");

        this.prosperity = Integer.parseInt(splitState[0]);

        this.food = Integer.parseInt(splitState[1].split(",")[0]);
        this.material = Integer.parseInt(splitState[1].split(",")[1]);
        this.energy = Integer.parseInt(splitState[1].split(",")[2]);

        this.resourcePrice = new HashMap<String, Integer>();
        resourcePrice.put("A", Integer.parseInt(splitState[2].split(",")[0]));
        resourcePrice.put("B", Integer.parseInt(splitState[2].split(",")[1]));
        resourcePrice.put("C", Integer.parseInt(splitState[2].split(",")[2]));

        this.foodInfo = new ArrayList<Integer>();
        foodInfo.add(0);
        foodInfo.add(1);
        foodInfo.add(1);
        foodInfo.add(1);
        foodInfo.add(0);
        foodInfo.add(Integer.parseInt(splitState[3].split(",")[0]));
        foodInfo.add(Integer.parseInt(splitState[3].split(",")[1]));
        foodInfo.set(0, foodInfo.get(0) + foodInfo.get(1) * resourcePrice.get("A") + foodInfo.get(2) * resourcePrice.get("B") + foodInfo.get(3) * resourcePrice.get("C"));
        
        
        this.materialInfo = new ArrayList<Integer>();
        materialInfo.add(0);
        materialInfo.add(1);
        materialInfo.add(1);
        materialInfo.add(1);
        materialInfo.add(0);
        materialInfo.add(Integer.parseInt(splitState[4].split(",")[0]));
        materialInfo.add(Integer.parseInt(splitState[4].split(",")[1]));
        materialInfo.set(0, materialInfo.get(0) + materialInfo.get(1) * resourcePrice.get("A") + materialInfo.get(2) * resourcePrice.get("B") + materialInfo.get(3) * resourcePrice.get("C"));
        
        this.energyInfo = new ArrayList<Integer>();
        energyInfo.add(0);
        energyInfo.add(1);
        energyInfo.add(1);
        energyInfo.add(1);
        energyInfo.add(0);
        energyInfo.add(Integer.parseInt(splitState[5].split(",")[0]));
        energyInfo.add(Integer.parseInt(splitState[5].split(",")[1]));
        energyInfo.set(0, energyInfo.get(0) + energyInfo.get(1) * resourcePrice.get("A") + energyInfo.get(2) * resourcePrice.get("B") + energyInfo.get(3) * resourcePrice.get("C"));
       
        this.waitInfo = new ArrayList<Integer>();
        waitInfo.add(0);
        waitInfo.add(1);
        waitInfo.add(1);
        waitInfo.add(1);
        waitInfo.add(0);
        waitInfo.add(0);
        waitInfo.add(0);
        waitInfo.set(0, waitInfo.get(0) + waitInfo.get(1) * resourcePrice.get("A") + waitInfo.get(2) * resourcePrice.get("B") + waitInfo.get(3) * resourcePrice.get("C"));

        this.v9 = new ArrayList<Integer>();
        this.v10 = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            String par1 = splitState[6].split(",")[i];
            v9.add(Integer.parseInt(par1));
            String par2 = splitState[7].split(",")[i];
            v10.add(Integer.parseInt(par2));
        }
        v9.set(0, v9.get(0) + v9.get(1) * resourcePrice.get("A") + v9.get(2) * resourcePrice.get("B") + v9.get(3) * resourcePrice.get("C"));
        v10.set(0, v10.get(0) + v10.get(1) * resourcePrice.get("A") + v10.get(2) * resourcePrice.get("B") + v10.get(3) * resourcePrice.get("C"));

    }

    public boolean er(String y) {
        ArrayList<Integer> x = new ArrayList<>();
        switch (y) {
            case "A":
                x = foodInfo;
                break;
            case "B":
                x = materialInfo;
                break;
            case "C":
                x = energyInfo;
                break;
            case "D":
                x = waitInfo;
                break;
            case "E1":
                x = v9;
                break;
            case "E2":
                x = v10;
                break;
            default:
                x = new ArrayList<>();
                break;
        }
        return (this.food >= x.get(1) && this.material >= x.get(2) && this.energy >= x.get(3)
                && this.maxMoney - this.currentCost >= x.get(0));
    }

    public void ur(String y) {

        ArrayList<Integer> x = new ArrayList<>();

        switch (y) {
            case "A":
                x = foodInfo;
                break;
            case "B":
                x = materialInfo;
                break;
            case "C":
                x = energyInfo;
                break;
            case "D":
                x = waitInfo;
                break;
            case "E1":
                x = v9;
                break;
            case "E2":
                x = v10;
                break;
            default:
                x = new ArrayList<>();
                break;
        }

        this.food -= x.get(1);
        this.material -= x.get(2);
        this.energy -= x.get(3);
        this.currentCost += x.get(0);
        this.prosperity += x.get(4);
    }

    void processReqResource() {
        if (currentRequestedResource != -1 && timeRemForReqResource > 0) {
            timeRemForReqResource--;
        }
        if (this.timeRemForReqResource == 0 && this.currentRequestedResource != -1) {

            if (this.currentRequestedResource == 0) {
                this.food += this.foodInfo.get(5);
            }
            if (this.currentRequestedResource == 1) {
                this.material += this.materialInfo.get(5);
            }
            if (this.currentRequestedResource == 2) {
                this.energy += this.energyInfo.get(5);
            }
            this.currentRequestedResource = -1;
            this.timeRemForReqResource = 0;
        }
    }

    void capResources() {
        if (food > maxResource) {
            food = maxResource;
        }
        if (material > maxResource) {
            material = maxResource;
        }
        if (energy > maxResource) {
            energy = maxResource;
        }
    }

    boolean requestResource(String an) {
        processReqResource();
        int i = -1;
        if (!er(an)) {
            return false;
        }
        switch (an) {
            case "A":
                if (this.maxMoney - this.currentCost < this.foodInfo.get(0)) {
                    return false;
                }
                i = 0;
                timeRemForReqResource = foodInfo.get(6);
                break;
            case "B":
                if (this.maxMoney - this.currentCost < this.materialInfo.get(0)) {
                    return false;
                }
                i = 1;
                timeRemForReqResource = materialInfo.get(6);
                break;
            case "C":
                if (this.maxMoney - this.currentCost < this.energyInfo.get(0)) {
                    return false;
                }
                i = 2;
                timeRemForReqResource = energyInfo.get(6);
                break;
            default:
                return false;
        }
        this.currentRequestedResource = i;
        ur(an);
        capResources();
        return true;
    }

    boolean f3() {
        processReqResource();
        if (!er("D")) {
            return false;
        }
        ur("D");
        capResources();
        return true;
    }

    boolean f2(int i) {
        processReqResource();
        String an = "E" + i;
        if (!er(an)) {
            return false;
        }
        ur(an);
        capResources();
        return true;
    }

    public boolean tryPlan(String[] actions, LLAPPlanChecker s) {
        boolean linkin = false;
        for (int i = 0; i < actions.length; i++) {

            switch (actions[i]) {
                case "requestfood":
                    linkin = s.requestResource("A");
                    break;
                case "requestenergy":
                    linkin = s.requestResource("C");
                    break;
                case "requestmaterials":
                    linkin = s.requestResource("B");
                    break;
                case "build1":
                    linkin = s.f2(1);
                    break;
                case "build2":
                    linkin = s.f2(2);
                    break;
                case "wait":
                    linkin = s.f3();
                    break;
                default:
                    linkin = false;
                    break;

            }
            if (!linkin) {
                System.out.println("action that failed: " + actions[i] + ", order: " + i);
                return false;
            }
        }
        return true;
    }

    boolean goalTest() {
        return this.prosperity >= 100;
    }

    public boolean applyPlan(String grid, String solution) {
        boolean linkin = true;
        solution = solution.toLowerCase();
        if (solution.equals("nosolution")) {
            return false;
        }
        // System.out.println(solution);
        String[] solutionArray = solution.split(";");
        String plan = solutionArray[0];
        int blue = Integer.parseInt(solutionArray[1]);
        plan.replace(" ", "");
        plan.replace("\n", "");
        plan.replace("\r", "");
        plan.replace("\n\r", "");
        plan.replace("\t", "");

        String[] actions = plan.split(",");

        LLAPPlanChecker s = new LLAPPlanChecker(grid);
        linkin = tryPlan(actions, s);
        if (!linkin) {
            return false;
        }

        return s.goalTest() && s.currentCost == blue;
    }
}
