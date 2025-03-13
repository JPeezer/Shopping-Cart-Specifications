public class Item {
    public int price;
    public String name;

    @SuppressWarnings("unused")
    public Item(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
