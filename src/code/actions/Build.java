package code.actions;

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
        if (currNode.isDead()) {
            return null;
        }
        if (currNode.getPendingResource() == null) {
            if (canBuild(currNode)) {
                return new Node(currNode.getProsperity() + this.prosperity,
                        currNode.getFood() - getFood(),
                        currNode.getMaterial() - getMaterial(),
                        currNode.getEnergy() - getEnergy(),
                        null,
                        currNode,
                        getName(),
                        getCost() + currNode.getCost());
            }
            return null;
        }
        Node childNode = currNode.propagatePendingResource();
        if (canBuild(childNode)) {
            childNode.setParent(currNode);
            childNode.setOperation(getName());
            childNode.setFood(childNode.getFood() - getFood());
            childNode.setMaterial(childNode.getMaterial() - getMaterial());
            childNode.setEnergy(childNode.getEnergy() - getEnergy());
            childNode.setCost(getCost() + currNode.getCost());
            return childNode;
        }
        return null;
    }

    private boolean canBuild(Node currNode) {
        if (currNode.getFood() < this.getFood() || currNode.getEnergy() < this.getEnergy() || currNode.getMaterial() < this.getMaterial()) {
            return false;
        }
        return true;
    }

    @Override
    public int getCost() {
        return price + super.getCost();
    }
}
