package client;

import com.beust.jcommander.Parameter;
import java.util.List;

public class Args {

    @Parameter(names = {"-t"})
    private String request;
    @Parameter(names = {"-k"})
    private String key;
    @Parameter(names = "-v", variableArity = true)
    private List<String> valueToSave;

    public String getRequest() {
        return request;
    }

    public String getKey() {
        return key;
    }

    public String getValueToSave() {
        if (valueToSave != null) {
            StringBuilder sb = new StringBuilder();
            for (String s : valueToSave) {
                sb.append(s).append(" ");
            }
            return sb.toString().trim();
        } else {
            return null;
        }
    }
}
