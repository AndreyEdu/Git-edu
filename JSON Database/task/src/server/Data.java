package server;

import java.util.Map;

public class Data {

    private static Map<Integer, String> data;

    private static final String ERROR = "ERROR";
    private static final String OK = "OK";

    public static String set(int index, String text) {
        data.put(index, text);
        return ok();
    }

    public static String get(int index) {
        if (haveSomeText(index)) {
            return data.get(index);
        } else {
            return error();
        }
    }

    public static String delete(int index) {
        if (haveSomeText(index)) {
            data.remove(index);
        }
        return ok();
    }


    private static boolean haveSomeText(int index) {
        return !(data.get(index) == null);
    }

    private static String error() {
        return ERROR;
    }

    private static String ok() {
        return OK;
    }
}
