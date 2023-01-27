package client;

import com.beust.jcommander.Parameter;
import java.util.List;

public class Args {

    @Parameter(names = {"-t"})
    private String request;
    @Parameter(names = {"-i"})
    private String index;
    @Parameter(names = "-m", variableArity = true)
    private List<String> valueToSave;

    public String getRequest() {
        return request;
    }

    public String getIndex() {
        return index;
    }

    public String getValueToSave() {
        if (valueToSave != null) {
            StringBuilder sb = new StringBuilder();
            for (String s : valueToSave) {
                sb.append(s).append(" ");
            }
            return sb.toString();
        } else {
            return null;
        }
    }
}
