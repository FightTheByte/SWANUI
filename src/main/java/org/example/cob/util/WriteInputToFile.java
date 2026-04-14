package org.example.cob.util;

import com.dlsc.formsfx.model.structure.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class WriteInputToFile {

    static String[] keywords = new String[]{
            "SET",
            "MODE",
            "COORDinates",
            "CGRID",
            "READGRID",
            "INPGRID",
            "READinp",
            "BOUNd",
            "BOUNdspec",
            "GEN2",
            "GEN1",
            "GEN3",
            "BREaking",
            "QUADrupl",
            "FRICtion",
            "WCAPping",
            "TRIad",
            "LIMiter",
            "OUTPut",
            "NUMeric",
            "QUANTity",
            "BLOCK",
            "POINTS",
            "TABLE",
            "SPECout",
            "COMPute",
            "HOTFILE",
            "STOP"
    };
    private WriteInputToFile() {

    }

    public static void writeToFile(Form form) throws IOException {
        try(FileWriter writer = new FileWriter("inputFile.swn")) {
            boolean foundWord = false;

            for (Field<?> field : form.getFields()) {
                if (field instanceof DataField<?, ?, ?> dataField) {
                    String value = dataField.getValue().toString();
                    for(String keyword : keywords){
                        if(keyword.equals(value)){
                            writer.append("\n").append(value);
                            foundWord = true;
                            break;
                        }
                    }
                    if(!foundWord) {
                        writer.append(" ").append(value);
                    }
                    foundWord = false;
                } else if (field instanceof MultiSelectionField<?> multiSelectionField) {
                    String value = multiSelectionField.getSelection().toString();
                    for(String keyword : keywords){
                        if(keyword.equals(value)){
                            writer.append("\n").append(value);
                            foundWord = true;
                            break;
                        }
                    }
                    if(!foundWord) {
                        writer.append(" ").append(value);
                    }
                    foundWord = false;
                } else if (field instanceof SingleSelectionField<?> singleSelectionField) {
                    String value = singleSelectionField.getSelection().toString();
                    for(String keyword : keywords){
                        if(keyword.equals(value)){
                            writer.append("\n").append(value);
                            foundWord = true;
                            break;
                        }
                    }
                    if(!foundWord) {
                        writer.append(" ").append(value);
                    }
                    foundWord = false;
                }
            }
        }
    }
}
