public class Customer {
    private String name;
    private int pin;
    public Customer(String name, int pin) {
        this.pin = pin;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }

    public boolean check(int pin) {
        return this.pin == pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
