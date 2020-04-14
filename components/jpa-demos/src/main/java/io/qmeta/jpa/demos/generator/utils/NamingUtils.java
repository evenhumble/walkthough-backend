package io.qmeta.jpa.demos.generator.utils;

import org.springframework.util.StringUtils;

public class NamingUtils {

    public static String convertToJavaName(String dbNaming){
        String[] parsedName = dbNaming.split("_");
        if (parsedName.length>1) {
            StringBuilder javaName = new StringBuilder(parsedName[0]);
            for (int i = 1; i < parsedName.length ; i++) {
                javaName.append(StringUtils.capitalize(parsedName[i]));
            }
            return javaName.toString();
        }
        return StringUtils.capitalize(dbNaming);
    }
}
