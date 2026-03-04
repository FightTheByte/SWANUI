package org.example.cob.handlers;

import com.google.common.eventbus.Subscribe;
import org.example.cob.customevents.WriteToFileEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    @Subscribe
    public void writeToFile(WriteToFileEvent writeToFileEvent){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("parameters.dat"))){
            writer.write(writeToFileEvent.getParameters());
        } catch(IOException e){
            System.out.println(e);
        }
    }
}
