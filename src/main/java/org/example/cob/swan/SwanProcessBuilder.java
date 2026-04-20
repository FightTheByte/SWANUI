package org.example.cob.swan;

public class SwanProcessBuilder {
    ProcessBuilder processBuilder;

    public String runModel(String[] commands){

        try{
            processBuilder = new ProcessBuilder(commands);
            Process process = processBuilder.start();

            return new String(process.getInputStream().readAllBytes());
        } catch(Exception e){
            return e.toString();
        }
    }
}
