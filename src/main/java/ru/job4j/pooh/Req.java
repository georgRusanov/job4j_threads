package ru.job4j.pooh;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Req {

    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        String[] splitted = content.split(" |/|" + System.lineSeparator());
        String param = splitted[splitted.length - 1];
        if ("GET".equals(splitted[0])) {
            param = splitted[2].equals("queue") ? "" : splitted[4];
        }
        return new Req(
                splitted[0],
                splitted[2],
                splitted[3],
                param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}
