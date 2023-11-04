package code.actions;

import code.artifacts.Node;

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
        return null;
    }

    @Override
    public int getCost() {
        return price + super.getCost();
    }
}
