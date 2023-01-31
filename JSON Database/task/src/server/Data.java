package server;

import java.util.HashMap;
import java.util.Map;

public class Data {

    private static Map<String, String> data = new HashMap<>();

    static final String ERROR = "ERROR";
    static final String OK = "OK";

    static final String NO_SUCH_KEY = "No such key";

    public static JSONResponse set(String index, String text, JSONResponse jsonResponse) {
        data.put(index, text);
        jsonResponse.setResponse(OK);
        return jsonResponse;
    }

    public static JSONResponse get(String index, JSONResponse jsonResponse) {
        if (haveSomeText(index)) {
            jsonResponse.setResponse(OK);
            jsonResponse.setValue(data.get(index));
        } else {
            jsonResponse.setResponse(ERROR);
            jsonResponse.setReason(NO_SUCH_KEY);
        }
        return jsonResponse;
    }

    public static JSONResponse delete(String index, JSONResponse jsonResponse) {
        if (haveSomeText(index)) {
            data.remove(index);
            jsonResponse.setResponse(OK);
        } else {
            jsonResponse.setResponse(ERROR);
            jsonResponse.setReason(NO_SUCH_KEY);
        }
        return jsonResponse;
    }


    private static boolean haveSomeText(String index) {
        return !(data.get(index) == null);
    }
}
