package io.qkits.corejava.corejava.guavasamples.base;


import io.qkits.corejava.corejava.guavasamples.pojo.Student;

import java.util.Date;

import static io.qkits.corejava.corejava.guavasamples.SampleUtil.*;


public class GuavaMoreObjectToStringHelper {
    public static void main(String[] args) {

        Student student=findDummyStudent();

        printTitle("Google Guava : How To Use MoreObjects ");
        println("Print Object to String (Guava) "+student);


    }
    public static Student findDummyStudent(){
        Student student =new Student();
        student.setId(123L);
        student.setFirstName("Krisna");
        student.setLastName("Putra");
        student.setBirthday(new Date());
        student.setGrades(3.3f);
        return  student;
    }
}