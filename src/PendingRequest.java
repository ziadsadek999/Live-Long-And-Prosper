public class PendingRequest {
    private final Resource resource;
    private int remainingTime;

    public PendingRequest(Resource resource) {
        this.resource = resource;
        this.remainingTime = resource.getDelayRequest();
    }

    public Resource getResource() {
        return resource;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void decrementTime() {
        this.remainingTime--;
    }
}
