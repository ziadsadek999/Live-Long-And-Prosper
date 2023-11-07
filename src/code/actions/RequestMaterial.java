package code.actions;

import code.artifacts.LLAPNode;
import code.artifacts.Node;
import code.pending.PendingEnergy;
import code.pending.PendingFood;
import code.pending.PendingMaterial;

public class RequestMaterial extends RequestResource {

    public RequestMaterial(String amount, String delay) {
        super(amount, delay, "RequestMaterials");
    }

    @Override
    public Node perform(Node node) {
        LLAPNode currNode = (LLAPNode) node;
        if (!canPerform(currNode)) {
            return null;
        }
        Node childNode = new LLAPNode(currNode.getProsperity(),
                currNode.getFood() - getFood(),
                currNode.getMaterial() - getMaterial(),
                currNode.getEnergy() - getEnergy(),
                new PendingMaterial(getDelay() + 1, getAmount()),
                currNode,
                getName(),
                currNode.getCost() + getCost());
        return childNode;
    }
}
