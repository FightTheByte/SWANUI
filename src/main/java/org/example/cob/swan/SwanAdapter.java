package org.example.cob.swan;
import org.example.cob.swan.SwanProcessBuilder;
public class SwanAdapter {

    SwanProcessBuilder swanProcessBuilder = new SwanProcessBuilder();
    String[] arguments;
    String results;

    public String runSwanWithArgumentsWindows() {
        arguments = new String[]{"cmd", "/C", "echo success"};
        results = swanProcessBuilder.runModel(arguments);
        return results;
    }

    public String runSwanWithArgumentsMac() {
        arguments = new String[]{"/bin/bash", "-c", "echo success"};
        results = swanProcessBuilder.runModel(arguments);
        return results;
    }

    public String runSwanWithArgumentsLinux() {
        arguments = new String[]{"/usr/bin/bash", "-c", "echo success"};
        results = swanProcessBuilder.runModel(arguments);
        return results;
    }
}
