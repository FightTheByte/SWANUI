package org.example.cob.parser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class SwanParameterField {

    private final String csv;
    private final List<List<List<List<InputToken>>>> inputTokens = new ArrayList<>();

    SwanParameterField(String csv){
        this.csv = csv;
    }

    public void createInputTokens(){
        boolean dropdown = false;
        boolean label = false;
        boolean joinLabel = false;
        boolean maxFlag = false;
        boolean minFlag = false;

        List<InputToken> dropdownList = new ArrayList<>();
        String labelName = "";
        String name = "";
        String type = "";
        String dropdownName = "";
        String dropdownType = "";

        int maxConstraint = 0;
        int minConstraint = 0;

        try(
                FileReader fileReader = new FileReader(csv);
                CSVReader csvReader = new CSVReaderBuilder(fileReader).build();
        )
        {


            List<String[]> allData = csvReader.readAll();

            for(String[] row : allData){
                for(String cell : row){
                    if(Objects.equals(cell, "START")){
                        inputTokens.add(new ArrayList<>());
                        inputTokens.getFirst().add(new ArrayList<>());
                        label = true;
                        continue;
                    }
                    if(Objects.equals(cell, "EOF")){
                        continue;
                    }
                    if(Objects.equals(cell, "ROW")){
                        inputTokens.getLast().add(new ArrayList<>());
                        label = true;
                        continue;
                    }
                    if(Objects.equals(cell, "SEG")){
                        inputTokens.add(new ArrayList<>());
                        inputTokens.getLast().add(new ArrayList<>());
                        label = true;
                        continue;
                    }

                    if(Objects.equals(cell, "NEXT")){
                        if(dropdown){
                            dropdown = false;
                            label = true;
                            inputTokens.getLast().getLast().add(dropdownList);
                            dropdownList = new ArrayList<>();
                        }
                        else{
                            List<InputToken> singleToken = new ArrayList<>();
                            singleToken.add(new InputToken(type, name, labelName, minConstraint, maxConstraint));
                            inputTokens.getLast().getLast().add(singleToken);
                            label = true;
                        }
                        maxConstraint = 99999;
                        minConstraint = -99999;
                        continue;

                    }
                    if(Objects.equals(cell, "JOIN")){
                        dropdown = true;
                        joinLabel = true;
                        continue;
                    }
                    if(label || joinLabel){
                        labelName = cell;
                        label = false;
                        joinLabel = false;
                        continue;
                    }
                    if(Objects.equals(cell, "MAX")){
                        maxFlag = true;
                        continue;
                    }
                    if(Objects.equals(cell, "MIN")){
                        minFlag = true;
                        continue;
                    }
                    if(maxFlag){
                        maxConstraint = (Objects.equals(cell, "NULL"))
                                ?99999
                                :Integer.parseInt(cell);
                        maxFlag = false;
                        continue;
                    }
                    if(minFlag){
                        minConstraint = (Objects.equals(cell, "NULL"))
                                ?-99999
                                :Integer.parseInt(cell);
                        minFlag = false;
                        continue;
                    }
                    if(
                            Objects.equals(cell, "STRING")
                                    || Objects.equals(cell, "INT")
                                    || Objects.equals(cell, "FLOAT")
                                    || Objects.equals(cell, "LONG")
                                    || Objects.equals(cell, "TEXT")
                                    || Objects.equals(cell, "MULTI")
                    )
                    {
                        if(dropdown){
                            dropdownType = cell;

                            dropdownList.add(new InputToken(dropdownType, dropdownName, labelName, minConstraint, maxConstraint));
                            continue;
                        }
                        type = cell;
                        continue;
                    }
                    if(dropdown){
                        dropdownName = cell;
                        continue;
                    }
                    name = cell;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public List<List<List<List<InputToken>>>> getInputTokens(){
        return inputTokens;
    }
}
