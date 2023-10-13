public class Node {
    private int prosperity;
    private int food;

    private int material;

    private int energy;

    private PendingRequest pendingRequest;

    public Node(int prosperity, int food, int material, int energy, PendingRequest pendingRequest) {
        this.prosperity = prosperity;
        this.food = food;
        this.material = material;
        this.energy = energy;
        this.pendingRequest = pendingRequest;
    }

    public Node requestResource(Resource resource) {
        if (pendingRequest != null) {
            return null;
        }
        return new Node(prosperity, food, material, energy, new PendingRequest(resource.getType(), resource.getDelayRequest()));
    }

    public void Await() {

    }

    public Node build(Build buildType) {
        int newMaterial = material - buildType.getMaterialUse();
        int newEnergy = energy - buildType.getEnergyUse();
        int newFood = food - buildType.getFoodUse();
        int newProsperity = prosperity - buildType.getProsperity();
        if (newMaterial >= 0 && newEnergy >= 0 && newFood >= 0 && newProsperity >= 0) {
            return new Node(newProsperity + buildType.getProsperity(), newFood, newMaterial, newEnergy, pendingRequest);
        }
        return null;
    }

    public Node acquireResource() {

        return null;
    }
}
