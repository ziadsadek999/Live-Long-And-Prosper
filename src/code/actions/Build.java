package code.actions;

import code.LLAPSearch;
import code.artifacts.Node;
import code.pending.PendingMaterial;

public class Build extends Action {
    private final int price;
    private final int prosperity;

    public Build(String price, String food, String material, String energy, String prosperity, int buildType) {
        super(Integer.parseInt(food), Integer.parseInt(material), Integer.parseInt(energy), "BUILD" + buildType);
        this.price = Integer.parseInt(price);
        this.prosperity = Integer.parseInt(prosperity);
    }

    @Override
    public Node perform(Node currNode) {
        if (!canPerform(currNode)) {
            return null;
        }
        Node childNode = new Node(currNode.getProsperity() + prosperity,
                currNode.getFood() - getFood(),
                currNode.getMaterial() - getMaterial(),
                currNode.getEnergy() - getEnergy(),
                currNode.getPendingResource(),
                currNode,
                getName(),
                currNode.getCost() + getCost());
        return childNode;
    }

    public String toString() {
        return super.toString() + " Prosperity: " + prosperity + " Price: " + price;
    }

    @Override
    public int getCost() {
        int cost = price + super.getCost();
        return cost;
    }
}
