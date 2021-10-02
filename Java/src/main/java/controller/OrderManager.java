///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller;
//
//import common.DataInput;
//import model.Fruit;
//import model.Order;
//
//import java.util.ArrayList;
//import java.util.Hashtable;
//
///**
// *
// * @author VLT
// */
//public class OrderManager {
//
//    private Hashtable<String, ArrayList<Order>> customerList = new Hashtable<>();
//    ArrayList<Order> lo = new ArrayList<>();
//
//    public void shopping(ArrayList<Fruit> lf, Fruit fruit, int quantity) {
//        //check list empty user can't buy
//        if (lf.isEmpty()) {
//            System.err.println("No have item.");
//            return;
//        }
//        //loop until user don't want to buy continue
//
//        while (true) {
//
//            fruit.setQuantity(fruit.getQuantity() - quantity);
//            //check item exist or not
//            if (!DataInput.checkItemExist(lo, fruit.getFruitId())) {
//                updateOrder(lo, fruit.getFruitId(), quantity);
//            } else {
//                lo.add(new Order(fruit.getFruitId(), fruit.getFruitName(),
//                        quantity, fruit.getPrice()));
//            }
//
//            if (!DataInput.checkInputYN()) {
//                break;
//            }
//
//        }
//
//    }
//
//    public void putToHT(String name){//update ten khach hang voi list order cua khach hang
//        customerList.put(name, lo);
//    }
//
//    public Hashtable<String, ArrayList<Order>> getHT(){
//        return customerList;
//    }
//
//    public ArrayList<Order> getListOrder(){
//        return lo;
//    }
//
//    public void updateOrder(ArrayList<Order> lo, String id, int quantity) {
//        for (Order order : lo) {
//            if (order.getFruitId().equalsIgnoreCase(id)) {
//                order.setQuantity(order.getQuantity() + quantity);
//                return;
//            }
//        }
//    }
//}


package controller;

import common.DataInput;
import model.Fruit;
import model.Order;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author VLT
 */
public class OrderManager {

    private Hashtable<String, ArrayList<Order>> customerList = new Hashtable<>();
    ArrayList<Order> lo = new ArrayList<>();

    public void shopping(ArrayList<Fruit> lf, Fruit fruit, int quantity) {
        //check list empty user can't buy

        if (lf.isEmpty()) {
            System.err.println("No have item.");
            return;
        }
        //loop until user don't want to buy continue

        fruit.setQuantity(fruit.getQuantity() - quantity);
        //check item exist or not
        if (!DataInput.checkItemExist(lo, fruit.getFruitId())) {
            updateOrder(lo, fruit.getFruitId()+"", quantity);
        } else {
            lo.add(new Order(fruit.getFruitId()+"", fruit.getFruitName(),
                    quantity, fruit.getPrice()));
        }





    }

    public void putToHT(String name){//update ten khach hang voi list order cua khach hang
        customerList.put(name, lo);
    }

    public Hashtable<String, ArrayList<Order>> getHT(){
        return customerList;
    }

    public ArrayList<Order> getListOrder(){
        return lo;
    }

    public void updateOrder(ArrayList<Order> lo, String id, int quantity) {
        for (Order order : lo) {
            if (order.getFruitId().equalsIgnoreCase(id)) {
                order.setQuantity(order.getQuantity() + quantity);
                return;
            }
        }
    }



}
