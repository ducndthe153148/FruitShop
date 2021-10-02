    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package view;

    import model.Fruit;
    import model.Order;
    import java.util.ArrayList;
    import java.util.Hashtable;
    public class OrderView {

        public static void displayListOrder(ArrayList<Order> lo) {
            double total = 0;
            System.out.printf("%15s%15s%15s%15s\n", "Product", "Quantity", "Price", "Amount");
            for (Order order : lo) {
                System.out.printf("%15s%15d%15.0f$%15.0f$\n", order.getFruitName(),
                        order.getQuantity(), order.getPrice(),
                        order.getPrice() * order.getQuantity());
                total += order.getPrice() * order.getQuantity();
            }
            System.out.println("Total: " + total + "$");
        }
        //allow user show view order
        public static void viewOrder(Hashtable<String, ArrayList<Order>> ht) {
            for (String name : ht.keySet()) {
                System.out.println("Customer: " + name);
                ArrayList<Order> lo = ht.get(name);
                displayListOrder(lo);
            }
        }
    }
