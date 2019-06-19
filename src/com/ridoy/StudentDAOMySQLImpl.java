package com.ridoy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentDAOMySQLImpl implements StudentDAO {
    private List<Student> studentsList = new ArrayList<>();


    private Connection connection = DBConnection.getConnection();
    private Statement statement = null;
    ResultSet resultSet;


    @Override
    public Student create(Student student) {
        try {
            statement = connection.createStatement();
            String query = "INSERT INTO student values('"+student.getId()+"','"+student.getName()+"')";
            statement.executeUpdate(query);
            System.out.println("Data inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrieve(student.getName());
    }

    @Override
    public Student retrieve(String studentId) {
        Student student = null;
        String query = "SELECT std_name FROM student WHERE std_id = ('"+studentId+"')";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                String name = resultSet.getString("std_name");
                student = new Student(studentId,name);
                System.out.println(name + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public List<Student> retrieve() {
        String query = "SELECT * FROM student";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String id = resultSet.getString("std_id");
                String name = resultSet.getString("std_name");

                Student student = new Student(id,name);
                studentsList.add(student);

            }
            /*for (Student student : studentsList)
                System.out.println(student);*/
            //studentsList.forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    @Override
    public List<Student> retrieve(Predicate<Student> filter) {
        List<Student> filteredStudents = retrieve();
        return filteredStudents.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public Student update(String studentId, Student student) {
        String query = "UPDATE student SET std_name = ('"+student.getName()+"') WHERE std_id = ('"+studentId+"')";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return retrieve(studentId);
    }

    @Override
    public boolean delete(String studentId) {
        String query = "DELETE FROM student WHERE std_id = ('"+studentId+"')";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Deleted the student successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        String query = "DELETE FROM student";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Deletion of all students successful!");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
