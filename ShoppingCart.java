
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    /*@ public invariant total >= 0;
      @ public invariant(\forall Item i; cart.containsKey(i) ==> cart.get(i) >=0);
    @*/
    public HashMap<Item, Integer> cart;
    public int total;
    
    /*@ ensures cart.isEmpty();
      @ ensures total == 0;
    @*/
    public ShoppingCart() {
        this.cart = new HashMap<>();
        this.total = 0;
    }

    /*@ requires itemName != null; 
      @ requires quantity >= 0;
      @ ensures cart.containsKey(itemName);
      @ ensures cart.get(itemName) >= \old(cart.containsKey(itemName) ? cart.get(itemName) : 0);
      @ ensures total >= \old(total);
    @*/
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

    /*@ requires itemName != null; 
      @ requires quantity >= 0;
      @ ensures !cart.containsKey(itemName) || cart.get(itemName) >= 0;
      @ ensures total <= \old(total);
    @*/
    public void removeItem(Item itemName, int quantity) {
        // Item must exist
        if (cart.containsKey(itemName)) {
            int newQuantity = cart.get(itemName) - quantity;

            if (newQuantity < 0) newQuantity = 0;

            cart.put(itemName, newQuantity);

            updateTotal();
        }
    }

    /*@ ensures total >= 0;
      @ ensures (\forall int j; 0 <= j && j < cart.size();
      @             (\exists Item i; cart.containsKey(i) && total >= i.price * cart.get(i)));             
    @*/
    private void updateTotal() {
        int newTotal = 0;

        for (Map.Entry<Item, Integer> entry : this.cart.entrySet()) {

            Item item = entry.getKey();
            Integer quantity = entry.getValue();

            newTotal += (item.price * quantity);
        }

        total = newTotal;
    }

    /*@ ensures \result >= 0;
      @ ensures \result == total;
    @*/
    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        String result = "";
        for (Item item : cart.keySet()) {
            result += item.name + ": " + cart.get(item) + "\n";
        }
        return result;
    }
}
