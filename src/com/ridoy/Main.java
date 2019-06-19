package com.ridoy;


import java.util.List;
import java.util.function.Predicate;

public class Main {

    // DAO - Data Access Object Pattern is used to separate low level data accessing API

    public static void main(String[] args) {
        // write your code here


        StudentDAO studentDAO = new StudentDAOMySQLImpl();
        studentDAO.create(new Student("20130000001", "Tom Hardy"));
        studentDAO.create(new Student("20130000005", "Leonardo Decaprio"));
        studentDAO.create(new Student("20130000006", "Tom Holland"));
        studentDAO.create(new Student("20130000007", "Seith Rollins"));
        studentDAO.create(new Student("20130000008", "Bradly Cooper"));
        studentDAO.create(new Student("20130000009", "Tom Cruise"));

        /*
         * retrieving using studentId
         */
        studentDAO.retrieve("20130000001");

        /*
         * retrieving all students
         */

        List<Student> allStudents = studentDAO.retrieve();
        allStudents.forEach(System.out::println);

        /*
         * retrieving filtered students
         *
         * */
        List<Student> filteredStudent = studentDAO.retrieve(student -> student.getName().startsWith("T"));
        filteredStudent.forEach(System.out::println);


        /*
         * updating a student
         */
        studentDAO.update("20130000001",new Student("20130000001","Venom"));

        /*
         * deleting a students
         */
        studentDAO.delete("20130000007");


        /*
         * deleting all students
         *
         */
        studentDAO.deleteAll();




        StudentDAO filesStudent = new StudentDAOFileImpl();
        //filesStudent.create(student);
        //filesStudent.retrieve("201100000004");
        //filesStudent.retrieve();
        //filesStudent.delete("201100000003");
        //filesStudent.update("2016000000044",student);


    }
}
