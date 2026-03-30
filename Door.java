public class Door {
    private boolean open;

    public void open() {
        if (!open) {
            open = true;
        }
    }

    public void close() {
        if (open) {
            open = false;
        }
    }

    public boolean isOpen() {
        return open;
    }
}
