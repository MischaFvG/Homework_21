package com.cat.dao;

import com.cat.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MariaDBDao {
    private Connection connection;

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public MariaDBDao() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3307/students", "root", "1234");
    }

    public void insertAllStudents(List<Student> studentList) {
        try (Statement statement = connection.createStatement()) {
            for (int i = 0; i < studentList.size(); i++) {
                String request = String.format("INSERT INTO students VALUES('%d','%s','%d')",
                        studentList.get(i).getId(),
                        studentList.get(i).getName(),
                        studentList.get(i).getAge());
                statement.execute(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM students");
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                studentList.add(new Student(id, name, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void dropTable() {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("DROP TABLE students");
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
