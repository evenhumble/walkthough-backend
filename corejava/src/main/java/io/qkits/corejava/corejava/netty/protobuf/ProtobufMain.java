package io.qkits.corejava.corejava.netty.protobuf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author mazhiqiang
 */
public class ProtobufMain {

    public static void main(String[] args) throws IOException {
        PersonProtos.Person clamaa =
                PersonProtos.Person.newBuilder().setId(1).setName("clamaa").setEmail("clark2mazhiqiang@163.com")
                        .setPhone(PersonProtos.Person.PhoneNumber.newBuilder().setNumber("13811102102")).build();
        FileOutputStream fos = new FileOutputStream("test.txt");
        clamaa.writeTo(fos);
        FileInputStream fis = new FileInputStream("test.txt");
        PersonProtos.Person person = PersonProtos.Person.parseFrom(fis);
        System.out.println(person);

    }

}
