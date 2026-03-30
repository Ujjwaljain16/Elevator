import java.util.LinkedList;
import java.util.Queue;

public class Elevator {
    private final int id;
    private int currFloor;
    private Direction direction;
    private final Queue<Request> requests;
    private final Door door;
    private final WeightSensor weightSensor;
    private final ElevatorPanel panel;

    public Elevator(int id, int startFloor, int maxWeight, int totalFloors) {
        this.id = id;
        this.currFloor = startFloor;
        this.direction = Direction.IDLE;
        this.requests = new LinkedList<>();
        this.door = new Door();
        this.weightSensor = new WeightSensor(maxWeight);
        this.panel = new ElevatorPanel(this, totalFloors);
    }

    public void addRequest(Request request) {
        requests.offer(request);
        System.out.println("Elevator " + id + " queued request for floor " + request.getFloor());
    }

    public void step() {
        if (door.isOpen()) {
            closeDoor();
            if (door.isOpen()) {
                return;
            }
        }

        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        Request next = requests.peek();
        if (next == null) {
            direction = Direction.IDLE;
            return;
        }

        if (currFloor == next.getFloor()) {
            openDoor();
            requests.poll();
            return;
        }

        move(next.getFloor());
    }

    public void move(int targetFloor) {
        if (currFloor < targetFloor) {
            direction = Direction.UP;
            currFloor++;
            System.out.println("Elevator " + id + " moving UP to floor " + currFloor);
        } else if (currFloor > targetFloor) {
            direction = Direction.DOWN;
            currFloor--;
            System.out.println("Elevator " + id + " moving DOWN to floor " + currFloor);
        }
    }

    public void openDoor() {
        if (!door.isOpen()) {
            System.out.println("Elevator " + id + " opening door at floor " + currFloor);
            door.open();
        }
    }

    public void closeDoor() {
        if (weightSensor.isOverWeight()) {
            System.out.println("Elevator " + id + " is overweight. Door remains open.");
            door.open();
            return;
        }
        if (door.isOpen()) {
            System.out.println("Elevator " + id + " closing door at floor " + currFloor);
            door.close();
        }
    }

    public void setCurrWeight(int currWeight) {
        weightSensor.setCurrWeight(currWeight);
    }

    public int getId() {
        return id;
    }

    public int getCurrFloor() {
        return currFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorPanel getPanel() {
        return panel;
    }
}
