package com.ridoy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StudentDAOFileImpl implements StudentDAO {

    @Override
    public Student create(Student student) {
        RandomAccessFile studentFile = null;
        try {
            studentFile = new RandomAccessFile("student.txt", "rw");
            studentFile.seek(studentFile.length());

            studentFile.writeBytes("student_id : " + student.getId() + "\t");
            studentFile.writeBytes("student_name : " + student.getName() + "\t");


            System.out.println("Data inserted successfully");


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Student retrieve(String studentId) {
        try {
            BufferedReader buf = new BufferedReader(new FileReader("student.txt"));
            String line = buf.readLine();
            String[] tokens = line.split("\t");

            String id = tokens[0];
            String name = tokens[1];

            System.out.println(id + "\t" + name +"\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> retrieve() {
        try {
            Stream<String> lines = Files.lines(Paths.get("student.txt"));

            Optional<String> hasStudent = lines.filter(s -> s.startsWith("student_id")).findFirst();

            hasStudent.ifPresent(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> retrieve(Predicate<Student> filter) {
        return null;
    }

    @Override
    public Student update(String studentId, Student student) {
        return null;
    }

    @Override
    public boolean delete(String studentId) {

        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
