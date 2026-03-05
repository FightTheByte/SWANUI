package org.example.cob.swan;
import org.example.cob.swan.SwanProcessBuilder;
public class SwanAdapter {

    SwanProcessBuilder swanProcessBuilder = new SwanProcessBuilder();
    String[] arguments;
    String results;
    boolean testFlag = false;

    public SwanAdapter(boolean testFlag){
        this.testFlag = testFlag;
    }

    public String runSwanWithArgumentsWindows() {
        arguments = (testFlag)
                ?new String[]{"cmd", "/C", "echo success"}
                :new String[]{"cmd", "/C", "swanrun parameters.swn"};

        results = swanProcessBuilder.runModel(arguments);
        return results;
    }

    public String runSwanWithArgumentsMac() {
        arguments = (testFlag)
                ?new String[]{"/bin/bash", "-c", "echo success"}
                :new String[]{"/bin/bash", "-c", "swanrun parameters.swn"};
        results = swanProcessBuilder.runModel(arguments);
        return results;
    }

    public String runSwanWithArgumentsLinux() {
        arguments = (testFlag)
                ?new String[]{"/usr/bin/bash", "-c", "echo success"}
                :new String[]{"/usr/bin/bash", "-c", "swanrun parameters.swn"};
        results = swanProcessBuilder.runModel(arguments);
        return results;
    }
}
