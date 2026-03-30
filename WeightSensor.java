public class WeightSensor {
    private final int maxWeight;
    private int currWeight;

    public WeightSensor(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean isOverWeight() {
        return currWeight > maxWeight;
    }

    public void setCurrWeight(int currWeight) {
        this.currWeight = currWeight;
    }
}
