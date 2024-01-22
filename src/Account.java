public class Account {
    private double money = 0;
    private String name = "";

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }
}
