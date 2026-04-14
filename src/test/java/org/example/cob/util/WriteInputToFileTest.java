package org.example.cob.util;

import com.dlsc.formsfx.model.structure.*;
import org.example.cob.customevents.WriteToFileEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class WriteInputToFileTest {
    List<Field<?>> fields;
    Form form;

    @BeforeEach
    public void init(){
        fields = new ArrayList<>();
        fields.add(
                Field.ofStringType("SET")
        );
        fields.add(
                Field.ofStringType("2.7")
        );
        fields.add(
                Field.ofStringType("MODE")
        );
        fields.add(
                Field.ofStringType("NONSTationary")
        );
        fields.add(
                Field.ofStringType("COORDinates")
        );
        fields.add(
                Field.ofStringType("SPHErical")
        );
        Section section = Section.of(
                fields.toArray(new Element[0])
        );
        form = Form.of(
              section
        );
    }

    @Test
    void fileContainsThreeRowsTest(){
        try{
            WriteToFileEvent.writeToFile(form);
            File inputFile = new File("inputFile.swn");
            try(
                    Scanner scanner = new Scanner(inputFile);
            ){
                int i = 0;
                while(scanner.hasNext()){
                    i = i++;
                    scanner.next();
                }
                assertEquals(3, i);
            }
        } catch(Exception e){
            fail();
        }
    }
}
