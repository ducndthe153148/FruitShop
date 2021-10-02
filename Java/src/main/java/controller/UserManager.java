/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.AppConstant;
import common.DataInput;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author kiennt
 */
public class UserManager {
    public static ArrayList<User> userList = new ArrayList<>(); // sửa final thành static
    public static int count = 0;
//    public static void main(String[] args) {
//        getListAccounts();
//    }
    // Lấy các object từ file txt và truyền vào ArrayList<User>
    public static List<User> getListAccounts() {
        File f = new File(AppConstant.USER_DATA); // chưa có file dat của user. sửa thành file account.txt
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line = null;
            while((line = reader.readLine())!=null){
                count++;
                User user = new User();
                String[] userInfo = line.split("\\|");
                user.setUserId(Integer.parseInt(userInfo[0].trim()));
                user.setUserName(userInfo[1].trim());
                user.setPassword(userInfo[2].trim());
                if(userInfo[3].trim().equals("Admin")){ // sửa
                    user.setType(1);
                } else {
                    user.setType(2);
                }
                userList.add(user); // đã add được các object từ file sang array list
            }
            count+=1;
        } catch (Exception ignored) {
        }
        return userList;
    }

    public static boolean checkLogin(User user) { // Lỗi 1, truyền filename nhưng chưa sử dụng, xoá file name
        File f = new File(AppConstant.USER_DATA); // chưa có file dat của user. sửa thành file account.txt
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                    String[] userInfo = line.split("\\|");
                if (user.getUserName().equals(userInfo[1].trim()) && user.getPassword().equals((userInfo[2].trim()))) {
                    if(userInfo[3].trim().equals("Admin")){
                        user.setType(1);
                    } else {
                        user.setType(2);
                    }
                    //user.setType(Integer.parseInt(userInfo[3].trim()));// sai ở đây
                    user.setUserId(Integer.parseInt(userInfo[0].trim())); // sai ở đây
                    return true;
                }
            }
        } catch (IOException ex) {
            System.out.println("Loi o buffer reader");
            System.out.println(ex);
        }
        return false;
    }

//    public static void changePassword(User user, String newPassword) {
//        List<String> listUsers = getListAccounts();
//        if (listUsers != null && !listUsers.isEmpty()) {
//            for (int i = 0; i < listUsers.size(); i++) {
//                String[] userInfo = listUsers.get(i).split("\\|");
//                if (user.getUserId() == Integer.parseInt(userInfo[0])) {
//                    listUsers.set(i, userInfo[0] + "|" + userInfo[1] + "|" + newPassword + "|" + userInfo[3]);
//                    user.setPassword(newPassword);
//                }
//            }
//            saveAccount(listUsers);
//        }
//    }

    public static void saveAccount(List<String> listUsers) {
        try (FileOutputStream fos = new FileOutputStream(new File(AppConstant.USER_DATA))) {
            for (String user : listUsers) {
                fos.write(user.getBytes());
                fos.write("\n".getBytes());
            }
        } catch (IOException ex) {
        }
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public static void addToFile(User user){
        try{
            String changeType = DataInput.checkType(user.getType());
            // thêm 1 hàm checkType trong DataInput
            int userCode = user.getUserId();
            String userName = user.getUserName();
            String password = user.getPassword();

            File file = new File(AppConstant.USER_DATA);
            FileWriter fw = new FileWriter(file,true);
            //BufferedWriter writer give better performance
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n"+userCode+"|" +userName +"|" +password +"|" +changeType +"|");
            //Closing BufferedWriter Stream

            bw.close();
        } catch(Exception e){
            System.out.println("Lỗi ở addUser ghi file");
            System.out.println(e);
        }
    }

    public void addUser() {
        //getListAccounts();
        //loop until user don't want to create user
        while (true) {
            int userCode = count++;
//            int userCode = DataInput.checkInputInt("Enter user code: ");
//            if (DataInput.userExisted(userList, userCode)) {
//                System.err.println("User code existed!");
//                return;
//            }
            String userName = DataInput.checkInputString("Enter user name: "); // thêm hàm check username
            if(DataInput.checkName(userList,userName)){
                System.err.println("Username existed!");
                return;
            }
            String password = DataInput.checkPassword("Enter password: "); // thêm hàm check password
            int userType = DataInput.checkInputInt("Enter user type: ");
            User user = new User(userCode, userName, password, userType);
            userList.add(user);

            // add user mới vào file txt
            addToFile(user);
            System.out.println("Add successfully");
            //check user want to continue or not
            if (!DataInput.checkInputYN()) {
                return;
            }
        }
    }

//    public static void main(String[] args) {
//        UserManager us = new UserManager();
//        getListAccounts();
//        us.updateUser();
//        //us.addUser();
//        //testDelete();
//    }
    public void updateUser() {
        //loop until user don't want to create fruit
        while (true) {
            int userCode = DataInput.checkInputInt("Enter user code: ");
            if (!DataInput.userExisted(userList, userCode)) {
                System.err.println("User code does not exist!");
                return;
            }
            String userName = DataInput.checkInputString("Enter user name: "); // thêm hàm check username
            if(DataInput.checkName(userList,userName)){
                System.err.println("Username existed!");
                return;
            }
            String password = DataInput.checkPassword("Enter password: ");
            int userType = DataInput.checkInputInt("Enter user type: ");
            User user = new User(userCode, userName, password, userType);
            deleteUserById(userCode);
            userList.add(user);
            addToFile(user);

            //check user want to continue or not
            if (!DataInput.checkInputYN()) {
                return;
            }
        }
    }
    public void deleteUserById(int userCode) {
            File inputFile = new File(AppConstant.USER_DATA);
            File tempFile = new File("src/view/Data/myTempFile.txt");
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line = null;
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
                // lấy ra line với usercode
                while ((line = reader.readLine()) != null) {
                    String[] userInfo = line.split("\\|");
                    if (userCode == Integer.parseInt(userInfo[0].trim())) {
                        break;
                    }
                }
                // xoá line đó
                deleteLine(line);

            } catch (IOException ex) {
                System.out.println("Loi o buffer reader");
                System.out.println(ex);
            }
    }
    public void deleteLine(String lineToRemove){
        String currentLine;
        try{
            File inputFile = new File(AppConstant.USER_DATA);
            File tempFile = new File("src/view/Data/myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
            while((currentLine = reader.readLine()) != null) {
                if(!currentLine.trim().equals(lineToRemove)){
                    writer.println(currentLine);
                    writer.flush();
                }
            }
            writer.close();
            reader.close();
            boolean successful = tempFile.renameTo(inputFile);
            System.out.println("Information about user code: " +lineToRemove);
        } catch (Exception e){
            System.out.println("sai ở delete line");
        }
    }
    public void deleteUser() {
        while (true) {
            int userCode = DataInput.checkInputInt("Enter user code: ");
            if (!DataInput.userExisted(userList, userCode)) {
                System.err.println("Id does not exist!");
                return;
            }
            File inputFile = new File(AppConstant.USER_DATA);
            File tempFile = new File("src/view/Data/myTempFile.txt");
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line = null;
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
                // lấy ra line với usercode
                while ((line = reader.readLine()) != null) {
                    String[] userInfo = line.split("\\|");
                    if (userCode == Integer.parseInt(userInfo[0].trim())) {
                        break;
                    }
                }
                System.out.println("Line is " +line);
                if (DataInput.checkDelete()) {
                    // xoá line đó
                    deleteLine(line);
                }
            } catch (IOException ex) {
                System.out.println("Loi o buffer reader");
                System.out.println(ex);
            }
            //check user want to continue or not
            if (!DataInput.checkInputYN()) {
                return;
            }
        }
    }
}
