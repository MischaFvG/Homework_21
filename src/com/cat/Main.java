package com.cat;

import com.cat.model.Student;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        JDBC_Storage jdbc_storage = new JDBC_Storage();
        System.out.println(jdbc_storage.getAllStudents());
        System.out.println(jdbc_storage.removeStudentById(1));
        System.out.println(jdbc_storage.removeStudentByName("Alberto"));
        System.out.println(jdbc_storage.getStudent(2));
        jdbc_storage.removeAllStudents();
        System.out.println(jdbc_storage.getAllStudents());
        jdbc_storage.addStudent(new Student(7,"Alex", 23));
        System.out.println(jdbc_storage.getAllStudents());
        jdbc_storage.updateStudent(7, "Max", 60);
        System.out.println(jdbc_storage.getAllStudents());
        System.out.println("If you would like to save changes into database click Q, please");
        String stringSave = scanner.nextLine();
        if (stringSave.equals("q") || stringSave.equals("Q")) {
            jdbc_storage.pushAllStudentsIntoDB();
        } else {
            System.out.println("Changes have not been saved");
        }
        if (stringSave.equals("d") || stringSave.equals("D")) {
            jdbc_storage.crashAll();
        }
    }
}
