package actions;

import artifacts.Node;

public class RequestFood extends RequestResource {

    public RequestFood(String amount, String delay) {
        super(amount, delay);
    }

    @Override
    public Node perform(Node currNode) {
        return null;
    }
}
