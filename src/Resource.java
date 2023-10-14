public class Resource {

    private final int initialValue;
    private final int unitPrice;

    private final int amountRequest;

    private final int delayRequest;

    private final ResourceType type;

    public Resource(String initialValue, String unitPrice, String amountRequest, String delayRequest, ResourceType type) {
        this.initialValue = Integer.parseInt(initialValue);
        this.unitPrice = Integer.parseInt(unitPrice);
        this.amountRequest = Integer.parseInt(amountRequest);
        this.delayRequest = Integer.parseInt(delayRequest);
        this.type = type;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getAmountRequest() {
        return amountRequest;
    }

    public int getDelayRequest() {
        return delayRequest;
    }

    public ResourceType getType() {
        return this.type;
    }

    public String toString() {
        return "Initial Value: " + initialValue + "\nUnit Price: " + unitPrice + "\nAmount Request: " + amountRequest + "\nDelay Request: " + delayRequest;
    }
}
