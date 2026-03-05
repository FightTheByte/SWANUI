package org.example.cob.swan;

import java.io.IOException;
import java.util.List;

public class SwanProcessBuilder {
    ProcessBuilder processBuilder;

    public String runModel(String[] commands){
        try{
            processBuilder = new ProcessBuilder(commands);
            Process process = processBuilder.start();
            process.waitFor();
            String results = new String(process.getInputStream().readAllBytes());
            System.out.println(results);
            return results;
        } catch(Exception e){
            return e.toString();
        }
    }
}
