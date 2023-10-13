public class Build {
    private int price;
    private int foodUse;
    private int materialUse;
    private int energyUse;
    private int prosperity;

    public Build(String price, String foodUse, String materialUse, String energyUse, String prosperity) {
        this.price = Integer.parseInt(price);
        this.foodUse = Integer.parseInt(foodUse);
        this.materialUse = Integer.parseInt(materialUse);
        this.energyUse = Integer.parseInt(energyUse);
        this.prosperity = Integer.parseInt(prosperity);
    }

    public int getPrice() {
        return price;
    }

    public int getFoodUse() {
        return foodUse;
    }

    public int getMaterialUse() {
        return materialUse;
    }

    public int getEnergyUse() {
        return energyUse;
    }

    public int getProsperity() {
        return prosperity;
    }

    public String toString() {
        return "Price: " + price + "\nFood Use: " + foodUse + "\nMaterial Use: " + materialUse + "\nEnergy Use: " + energyUse + "\nProsperity: " + prosperity;
    }
}
