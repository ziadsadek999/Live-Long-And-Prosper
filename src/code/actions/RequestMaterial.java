package code.actions;

import code.artifacts.Node;
import code.pending.PendingMaterial;

public class RequestMaterial extends RequestResource {

    public RequestMaterial(String amount, String delay) {
        super(amount, delay, "RequestMaterials");
    }

    @Override
    public Node perform(Node currNode) {
        if (!canPerform(currNode)) {
            return null;
        }
        if (currNode.getPendingResource() == null) {
            return new Node(currNode.getProsperity(),
                    currNode.getFood() - 1,
                    currNode.getMaterial() - 1,
                    currNode.getEnergy() - 1,
                    new PendingMaterial(getAmount(),
                            getDelay()),
                    currNode,
                    getName(),
                    getCost() + currNode.getCost());
        }
        Node childNode = currNode.propagatePendingResource();
        if (childNode.getPendingResource() == null) {
            childNode.setPendingResource(new PendingMaterial(getAmount(), getDelay()));
            childNode.setParent(currNode);
            childNode.setOperation(getName());
            childNode.decrementAll();
            childNode.setCost(getCost() + currNode.getCost());
            return childNode;
        }
        return null;
    }
}
