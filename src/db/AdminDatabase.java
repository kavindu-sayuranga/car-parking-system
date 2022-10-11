package db;

import model.Admin;

import java.util.ArrayList;

public class AdminDatabase {

    public static ArrayList<Admin> adminTable = new ArrayList<Admin>();

    static {
        adminTable.add(new Admin("Kavindu","Admin","2000"));
    }
}
