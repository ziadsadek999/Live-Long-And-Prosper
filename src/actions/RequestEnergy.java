package actions;

import artifacts.Node;

public class RequestEnergy extends RequestResource {

    public RequestEnergy(String amount, String delay) {
        super(amount, delay);
    }

    @Override
    public Node perform(Node currNode) {
        return null;
    }
}
