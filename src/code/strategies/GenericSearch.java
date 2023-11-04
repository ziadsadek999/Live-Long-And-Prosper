package code.strategies;

import code.LLAPSearch;
import code.actions.Action;
import code.artifacts.*;

import java.util.ArrayList;
import java.util.List;

abstract public class GenericSearch {
    Node root;
    private int nodesExpanded = 0;

    public GenericSearch(Node root) {
        this.root = root;
    }

    public Node solve() {
        return null;
    }

    public List<Node> expand(Node node) {
        List<Node> children = new ArrayList<>();
        for (Action action : LLAPSearch.getActions()) {
            Node child = action.perform(node);
            if (child != null)
                children.add(child);
        }
        nodesExpanded++;
        return children;
    }

    public int getNodesExpanded() {
        return nodesExpanded;
    }
}
