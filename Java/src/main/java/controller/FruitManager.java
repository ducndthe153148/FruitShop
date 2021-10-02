//package controller;
//
//import common.DataInput;
//import java.util.ArrayList;
//import model.Fruit;
//
//public class FruitManager {
//
//    private final ArrayList<Fruit> fruitList = new ArrayList<>();
//
//    public void addFruit() {
//        //loop until user don't want to create fruit
//        while (true) {
//            String fruitId = DataInput.checkInputString("Enter fruit id: ");
//            if (DataInput.fruitExisted(fruitList, fruitId)) {
//                System.err.println("Id exist");
//                return;
//            }
//            String fruitName = DataInput.checkInputString("Enter fruit name: ");
//            double price = DataInput.checkInputDouble("Enter price: ");
//            int quantity = DataInput.checkInputInt("Enter quantity: ");
//            String origin = DataInput.checkInputString("Enter origin: ");
//            fruitList.add(new Fruit(fruitId, fruitName, price, quantity, origin));
//            //check user want to continue or not
//            if (!DataInput.checkInputYN()) {
//                return;
//            }
//        }
//    }
//
//    public void updateFruit() {
//        //loop until user don't want to create fruit
//        while (true) {
//            String fruitId = DataInput.checkInputString("Enter fruit id: ");
//            if (!DataInput.fruitExisted(fruitList, fruitId)) {
//                System.err.println("Id does not exist!");
//                return;
//            }
//            String fruitName = DataInput.checkInputString("Enter fruit name: ");
//            double price = DataInput.checkInputDouble("Enter price: ");
//            int quantity = DataInput.checkInputInt("Enter quantity: ");
//            String origin = DataInput.checkInputString("Enter origin: ");
//            fruitList.add(new Fruit(fruitId, fruitName, price, quantity, origin));
//        }
//    }
//
//    public void deleteFruit() {
//        while (true) {
//            String fruitId = DataInput.checkInputString("Enter fruit id: ");
//            if (!DataInput.fruitExisted(fruitList, fruitId)) {
//                System.err.println("Id does not exist!");
//                return;
//            }
//        }
//    }
//
//    public ArrayList<Fruit> getFruitList() {
//        return fruitList;
//    }
//
//    //get fruint user want to by
//    public Fruit getFruitByItem(int item) {
//        int countItem = 1;
//        for (Fruit fruit : fruitList) {
//            //check shop have item or not
//            if (fruit.getQuantity() != 0) {
//                countItem++;
//            }
//            if (countItem - 1 == item) {
//                return fruit;
//            }
//        }
//        return null;
//    }
//}


package controller;

import common.DataInput;
import java.util.ArrayList;
import model.Fruit;

public class FruitManager {


    public void tempFruit(ArrayList<Fruit> fruitList){
        fruitList.add(new Fruit(1, "Coconut", 2, 100, "Vietnam"));
        fruitList.add(new Fruit(2, "Orange", 5, 100, "US"));
        fruitList.add(new Fruit(3, "Apple", 4, 100, "Thailand"));
        fruitList.add(new Fruit(4, "Grape", 9, 100, "France"));
    }

    public void addFruit(ArrayList<Fruit> fruitList) {
        //loop until user don't want to create fruit
        while (true) {
            int fruitId = DataInput.checkInputInt("Enter fruit id: ");
            if (DataInput.fruitExisted(fruitList, fruitId)) {
                System.err.println("Id exist");
                return;
            }
            String fruitName = DataInput.checkInputString("Enter fruit name: ");

            double price = DataInput.checkInputDouble("Enter price: ");
            int quantity = DataInput.checkInputInt("Enter quantity: ");

            String origin = DataInput.checkInputString("Enter origin: ");
//            Check giá trị âm
            if (quantity < 0 || fruitId < 0 || price < 0) {
                System.err.println("Quantity or Fruit or Price Id invalid");
                return;
            }
//            Check giá trị đặc biệt
            if (haveSpecialChar(origin) || haveSpecialChar(fruitName)) {
                System.err.println("Fruit name or Origin invalid");
                return;
            }
            fruitList.add(new Fruit(fruitId, fruitName, price, quantity, origin));
            //check user want to continue or not
            if (!DataInput.checkInputYN()) {
                return;
            }
        }
    }

    public boolean haveSpecialChar(String input) {
        String validChar = "1234567890qwertyuiopasdfghjklzxcvbnm ";
        for (int i = 0; i < input.length(); i++) {
            if (validChar.indexOf(input.toLowerCase().charAt(i)) < 0) {
                return true;
            }
        }
        return false;
    }

    public void updateFruit(ArrayList<Fruit> fruitList) {
        //loop until user don't want to create fruit
        while (true) {
            int fruitId = DataInput.checkInputInt("Enter fruit id: ");
            if (!DataInput.fruitExisted(fruitList, fruitId)) {
                System.err.println("Id does not exist!");
                return;
            }
            String fruitName = DataInput.checkInputString("Enter fruit name: ");
            double price = DataInput.checkInputDouble("Enter price: ");
            int quantity = DataInput.checkInputInt("Enter quantity: ");
            String origin = DataInput.checkInputString("Enter origin: ");
            //            Check giá trị âm
            if (quantity < 0 || fruitId < 0 || price < 0) {
                System.err.println("Quantity or Fruit Id or Price must be positive number");
                return;
            }
//            Check giá trị đặc biệt
            if (haveSpecialChar(origin) || haveSpecialChar(fruitName)) {
                System.err.println("Fruit name or Origin can not have special character");
                return;
            }
//          //Xóa dữ liệu cũ,ghi dữ liệu mới
            int selectedIndex = -1;
            for (int i = 0; i < fruitList.size(); i++) {
                if (fruitList.get(i).getFruitId() == fruitId) {
                    selectedIndex = i;
                }
                if (selectedIndex > -1) {
                    fruitList.remove(selectedIndex);
                    break;
                }
            }
            System.out.println("Selected Index = " + fruitId);
            fruitList.add(new Fruit(fruitId, fruitName, price, quantity, origin));
            return;
        }

    }

    public void deleteFruit(ArrayList<Fruit> fruitList) {
        while (true) {
            int fruitId = DataInput.checkInputInt("Enter fruit id: ");
            if (!DataInput.fruitExisted(fruitList, fruitId)) {
                System.err.println("Id does not exist!");
                return;
            }
            for (Fruit fruit : fruitList) {
                if (fruit.getFruitId() == fruitId) {
                    fruitList.remove(fruit);
                    System.out.println("Remove  success fruit: " + fruit.getFruitName());
                    return;
                }
            }
        }
    }



    //get fruint user want to by
    public Fruit getFruitByItem(int item,ArrayList<Fruit> fruitList) {
        int countItem = 1;
        for (Fruit fruit : fruitList) {
            //check shop have item or not
            if (fruit.getQuantity() != 0) {
                countItem++;
            }
            if (countItem - 1 == item) {
                return fruit;
            }
        }
        return null;
    }

}