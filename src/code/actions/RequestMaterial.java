package code.actions;

import code.artifacts.Node;

public class RequestMaterial extends RequestResource {

    public RequestMaterial(String amount, String delay) {
        super(amount, delay);
    }

    @Override
    public Node perform(Node currNode) {
        return null;
    }
}
