package io.qkits.corejava.corejava.guavasamples.base;


import java.util.Date;

import static io.allroundtester.walkthrough.corejava.guavasamples.SampleUtil.*;

import io.allroundtester.walkthrough.corejava.guavasamples.pojo.Student;

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