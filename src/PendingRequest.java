public class PendingRequest {
    ResourceType type;
    int remainingTime;

    public PendingRequest(ResourceType type, int remainingTime) {
        this.type = type;
        this.remainingTime = remainingTime;
    }
}
