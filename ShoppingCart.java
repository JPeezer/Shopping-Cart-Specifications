
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    public HashMap<Item, Integer> cart;
    public int total;

    public ShoppingCart() {
        this.cart = new HashMap<>();
        this.total = 0;
    }

    public void addItem(Item itemName, int quantity) {

        // If item exists, update quantity
        if (cart.containsKey(itemName)) {
            int newQuantity = cart.get(itemName) + quantity;
            cart.put(itemName, newQuantity);
        }
        else {
            cart.put(itemName, quantity);
        }

        updateTotal();
    }

    public void removeItem(Item itemName, int quantity) {
        // Item must exist
        if (cart.containsKey(itemName)) {
            int newQuantity = cart.get(itemName) - quantity;

            if (newQuantity < 0) newQuantity = 0;

            cart.put(itemName, newQuantity);

            updateTotal();
        }
    }

    private void updateTotal() {
        int newTotal = 0;

        for (Map.Entry<Item, Integer> entry : this.cart.entrySet()) {

            Item item = entry.getKey();
            Integer quantity = entry.getValue();

            newTotal += (item.price * quantity);
        }

        total = newTotal;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : cart.keySet()) {
            sb.append(item.name).append(": ").append(cart.get(item)).append("\n");
        }
        return sb.toString();
    }
}
