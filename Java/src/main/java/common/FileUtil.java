/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author kiennt
 */
public class FileUtil {

    private String filePath;

    public FileUtil(String filePath) {
        this.filePath = filePath;
    }

    public void writeObject(Object serObj) {

        try {

            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void writeObjects(ArrayList<Object> objectList) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath, true);
            try {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Object anObject : objectList) {
                    try {
                        oos.writeObject(anObject);
                        System.out.println("saved");

                    } catch (NotSerializableException e) {
                        System.out.println("An object was not serializable, it has not been saved.");
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Object> readObjects() {
        ArrayList<Object> objectList = new ArrayList<Object>();
        boolean cont = true;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("outputFile"));
            while (cont) {
                Object obj = null;
                try {
                    obj = ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (obj != null) {
                    objectList.add(obj);
                } else {
                    cont = false;
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return objectList;
    }

}
