/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.AppConstant;
import common.DataInput;
import controller.UserManager;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 *
 * @author kiennt
 */
public class UserView {

    public static void manageUsers() {
        UserManager userManager = new UserManager();
        UserView userView = new UserView();
        List<User> listUser = userManager.getListAccounts();
        System.out.println("USERS MANAGEMENT");
        System.out.println("List of current users: ");
        userView.displayUserList(listUser);
        while (true) {
            System.out.println("  1.Add new user");
            System.out.println("  2.Edit/update a user");
            System.out.println("  3.Delete a user");
            System.out.println("  4.Main screen");

            switch (DataInput.checkInputIntLimit(AppConstant.ENTER_CHOICE, 1, 4)) {
                case 1:
                    userManager.addUser();
                    break;
                case 2:
                    //userManager.getListAccounts();
                    userManager.updateUser();
                    break;
                case 3:
                    //userManager.getListAccounts();
                    userManager.deleteUser();
                    break;
                default:
                    return;
            }
        }
    }

    public User login() {
        while (true) {
            User user = getUserInfo(); // user ở đây là tài khoản mình nhập vào
            try {
                if (UserManager.checkLogin(user)) { //bỏ truyền vào file
                    return user;
                } else {
                    System.err.println("Username or password is incorrect! Please enter again!");
                }
            } catch (Exception ex) {
                System.exit(0);
            }
        }
    }

    public User getUserInfo() {
        System.out.println("\n===== Login =====");
        User user = new User();
        user.setUserName(DataInput.checkInputString("Username: "));
        user.setPassword(DataInput.checkInputString("Password: "));
        return user;
    }

    public String changePassword(User user) {
        String password;
        while (true) {
            String oldPassord = DataInput.checkInputString("Enter old password: ");
            if (!oldPassord.equals(user.getPassword())) {
                System.out.println("Old passwords do not match! Please enter again!");
            } else {
                password = DataInput.checkPassword("Enter new password: ");
                String confirmPassword = DataInput.checkInputString("Confirm new password: ");
                if (password.equals(confirmPassword)) {
                    break;
                } else {
                    System.out.println("Passwords do not match! Please enter again!");
                }
            }
        }
        return password;
    }

    public void displayUserList(List<User> userList) {
        int countItem = 1;
        System.out.printf("%-12s%-20s%-20s%12s\n", "++ Code ++", "++ User Name ++", "++ Password ++", "++ Role ++");
        for (User user : userList) {
            System.out.printf("%-10d%-20s%-20s%10d\n", user.getUserId(), user.getUserName(),
                    user.getPassword(), user.getType());
        }
    }
}
