package io.hedwig.modules.jodd.json;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.util.Map;

public class JoddJsonClient {

    public static void main(String[] args) {
        ChildEntity ce = new ChildEntity();
        ce.setDetails("this is details");
        ce.setName("this is name");
        //serialize
        String childString = JsonSerializer.create().deep(true)
            .serialize(ce);
        System.out.println(childString);
        String childJson = "{\"details\":\"this is details\",\"name\":\"this is name\"}";
        //parser
        ChildEntity anotherCE = JsonParser.create().parse(childJson,ChildEntity.class);
        System.out.println(anotherCE);

    }
}
