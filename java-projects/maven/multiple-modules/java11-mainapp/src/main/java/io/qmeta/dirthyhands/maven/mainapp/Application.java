package io.qmeta.dirthyhands.maven.mainapp;

import io.qmeta.dirtyhands.maven.entity.User;

public class Application {
    public static void main(String[] args) {
        User user = new User("test");
        System.out.printf(user.toString());
    }
}
