import java.util.*;

class ShoppingException extends Exception {
    ShoppingException(String msg) {
        super(msg);
    }
}
class ProductNotFoundException extends ShoppingException {
    ProductNotFoundException(String msg) {
        super(msg);
    }
}
class EmptyCartException extends ShoppingException {
    EmptyCartException(String msg) {
        super(msg);
    }
}
class PaymentException extends ShoppingException {
    PaymentException(String msg) {
        super(msg);
    }
}
public class Q7 {
    static ArrayList<String> products =
            new ArrayList<>(Arrays.asList("Laptop", "Mouse", "Keyboard"));
    static ArrayList<String> cart = new ArrayList<>();

    static void search(String name)
            throws ProductNotFoundException {

        if (!products.contains(name))
            throw new ProductNotFoundException("Product not found");

        System.out.println("Product found: " + name);
    }

    static void add(String name)
            throws ProductNotFoundException {

        if (!products.contains(name))
            throw new ProductNotFoundException("Product not found");

        cart.add(name);
        System.out.println("Product added");
    }

    static void remove(String name)
            throws ProductNotFoundException {

        if (!cart.remove(name))
            throw new ProductNotFoundException("Product not in cart");

        System.out.println("Product removed");
    }

    static void pay(double amount)
            throws EmptyCartException, PaymentException {

        if (cart.isEmpty())
            throw new EmptyCartException("Cart is empty");

        if (amount < 1000)
            throw new PaymentException("Payment failed");

        System.out.println("Payment successful");
    }
    public static void main(String[] args) {
        try {
            search("Laptop");
            add("Laptop");
            add("Mouse");
            System.out.println("Cart: " + cart);
            remove("Mouse");
            pay(1000);
        }
        catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (EmptyCartException e) {
            System.out.println(e.getMessage());
        }
        catch (PaymentException e) {
            System.out.println(e.getMessage());
        }
    }
}