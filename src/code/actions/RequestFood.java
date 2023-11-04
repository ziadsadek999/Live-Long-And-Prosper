package code.actions;

import code.artifacts.Node;

public class RequestFood extends RequestResource {

    public RequestFood(String amount, String delay) {
        super(amount, delay, "RequestFood");
    }

    @Override
    public Node perform(Node currNode) {
        return null;
    }
}
