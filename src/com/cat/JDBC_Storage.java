package com.cat;

import com.cat.dao.MariaDBDao;
import com.cat.model.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBC_Storage {
    private List<Student> students = new ArrayList<>();
    private MariaDBDao mariaDBDao = new MariaDBDao();

    public JDBC_Storage() throws SQLException {
        students = mariaDBDao.getAllStudents();
        if (students.isEmpty()) {
            throw new IllegalArgumentException("Table is empty");
        }
    }

    public void removeAllStudents() {
        students.removeAll(students);
    }

    public List<Student> removeStudentById(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(students.get(i));
            }
        }
        return students;
    }

    public List<Student> removeStudentByName(String name) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name)) {
                students.remove(students.get(i));
            }
        }
        return students;
    }

    public void addStudent(Student student) throws SQLException {
        students.add(student);
    }

    public void updateStudent(int id, String name, int age) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.get(i).setName(name);
                students.get(i).setAge(age);
            }
        }
    }

    public Student getStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                return students.get(i);
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void pushAllStudentsIntoDB() {
        mariaDBDao.insertAllStudents(students);
    }

    public void crashAll() {
        mariaDBDao.dropTable();
    }

}
