package server;

public class Data {

    private static String[] array = new String[1001];

    private static final String ERROR = "ERROR";
    private static final String OK = "OK";

    public static String set(int index, String text) {
        if (indexExist(index)) {
            array[index] = text;
            return ok();
        } else {
            return error();
        }
    }

    public static String get(int index) {
        if (indexExist(index) && haveSomeText(index)) {
            return array[index];
        } else {
            return error();
        }
    }

    public static String delete(int index) {
        if (indexExist(index)) {
            if (haveSomeText(index)) {
                array[index] = null;
            }
            return ok();
        } else {
            return error();
        }
    }

    private static boolean indexExist(int index) {
        return index >= 1 && index <= 1000;
    }

    private static boolean haveSomeText(int index) {
        return !(array[index] == null);
    }

    private static String error() {
        return ERROR;
    }

    private static String ok() {
        return OK;
    }
}
