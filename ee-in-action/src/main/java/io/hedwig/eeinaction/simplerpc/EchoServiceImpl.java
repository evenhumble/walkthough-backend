package io.hedwig.eeinaction.simplerpc;

public class EchoServiceImpl implements EchoService {

    public String echo(String input) {
        return input!=null?"I am a echo man":"Nothing,Nothing";
    }
}
