package artifacts;

public class Node {
    private final int prosperity;
    private final int food;

    private final int material;

    private final int energy;

    private final PendingRequest pendingRequest;

    
    public Node(int prosperity, int food, int material, int energy, PendingRequest pendingRequest) {
        this.prosperity = prosperity;
        this.food = food;
        this.material = material;
        this.energy = energy;
        this.pendingRequest = pendingRequest;
    }

    public Node requestResource(Resource resource) {
        Node intermediate = acquireResource();
        if (intermediate == null || intermediate.pendingRequest == null) {
            if (food == 0 || material == 0 || energy == 0) {
                return null;
            } else {
                return new Node(prosperity, food - 1, material - 1, energy - 1, new PendingRequest(resource));
            }
        }
        return null;
    }

    public Node await() {
        if (food == 0 || material == 0 || energy == 0) {
            return null;
        } else {
            Node intermediate = acquireResource();
            if (intermediate == null) {
                return new Node(prosperity, food - 1, material - 1, energy - 1, pendingRequest);
            }
            return intermediate;
        }
    }

    public Node build(Build buildType) {
        Node intermediate = acquireResource();
        if (intermediate == null) {
            intermediate = this;
        }
        int newMaterial = intermediate.material - buildType.getMaterialUse();
        int newEnergy = intermediate.energy - buildType.getEnergyUse();
        int newFood = intermediate.food - buildType.getFoodUse();
        if (newMaterial >= 0 && newEnergy >= 0 && newFood >= 0) {
            return new Node(prosperity + buildType.getProsperity(), newFood, newMaterial, newEnergy, intermediate.pendingRequest);
        }
        return null;
    }

    private Node acquireResource() {
        if (pendingRequest != null) {
            if (pendingRequest.getRemainingTime() == 0) {
                ResourceType type = pendingRequest.getResource().getType();
                int newFood = food;
                int newMaterial = material;
                int newEnergy = energy;
                if (type == ResourceType.FOOD) {
                    newFood += pendingRequest.getResource().getAmountRequest();
                } else if (type == ResourceType.MATERIALS) {
                    newMaterial += pendingRequest.getResource().getAmountRequest();
                } else if (type == ResourceType.ENERGY) {
                    newEnergy += pendingRequest.getResource().getAmountRequest();
                }
                return new Node(prosperity, newFood, newMaterial, newEnergy, null);
            } else {
                PendingRequest newPending = new PendingRequest(pendingRequest.getResource());
                newPending.decrementTime();
                return new Node(prosperity, food, material, energy, newPending);
            }
        }
        return null;
    }

    public boolean isGoal() {
        return prosperity == 100;
    }
}
