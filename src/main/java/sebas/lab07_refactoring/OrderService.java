/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sebas.lab07_refactoring;

/**
 *
 * @author jacks
 */
public class OrderService {

    private static final double TAX_RATE = 0.18;

    public void processOrder(Order order) {
        validateOrder(order);
        double total = calculateOrderTotal(order);
        saveOrder(order, total);
        sendConfirmation(order, total);
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must have items");
        }
        if (order.getCustomer() == null) {
            throw new IllegalArgumentException("Order must have a customer");
        }
    }

    private double calculateOrderTotal(Order order) {
        double subtotal = 0;
        for (OrderItem item : order.getItems()) {
            subtotal += item.getPrice() * item.getQuantity();
        }
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;
        order.setTotal(total);
        return total;
    }

    private void saveOrder(Order order, double total) {
        System.out.println("Saving order to database...");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Total: " + total);
    }

    private void sendConfirmation(Order order, double total) {
        System.out.println("Sending confirmation message to: "
                + order.getCustomer().getFormattedPhone());
        System.out.println("Dear " + order.getCustomer().getName());
        System.out.println("Your order #" + order.getId()
                + " has been confirmed.");
        System.out.println("Total: $" + total);
    }
}
