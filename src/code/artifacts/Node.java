package code.artifacts;

public abstract class Node {
    private final Node parent;

    private final int cost;
    private final String operation;

    //constructor 
    public Node(Node parent, int cost , String operation){
        this.parent = parent;
        this .cost = cost;
        this.operation = operation;
    }
    public abstract boolean isGoal();

    public int getCost(){
        return cost;
    }

    public Node getParent() {
        return parent;
    }
    public String getOperation() {
        return operation;
    }
   
    
}
