package org.example.cob.UI;

import com.dlsc.formsfx.model.structure.Element;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Section;
import com.dlsc.formsfx.model.validators.CustomValidator;
import com.dlsc.formsfx.model.validators.IntegerRangeValidator;
import com.dlsc.formsfx.model.validators.StringLengthValidator;
import com.dlsc.formsfx.view.controls.SimpleCheckBoxControl;
import javafx.beans.property.SimpleStringProperty;
import org.example.cob.parser.InputToken;
import org.example.cob.parser.SwanParameterField;

import java.util.*;

public class FormGenerator {

    List<Field<?>> field;
    List<Section> sections = new ArrayList<>();
    List<List<List<List<InputToken>>>> tokens;

    SwanParameterField swanParameterField;


    public FormGenerator(String csv){
        this.swanParameterField = new SwanParameterField(csv);
        this.swanParameterField.createInputTokens();
        this.tokens = this.swanParameterField.getInputTokens();
    }

    public List<Section> getSections(){
        for(List<List<List<InputToken>>> segment : tokens){
            field = new ArrayList<>();
            for(List<List<InputToken>> row: segment){
                for(List<InputToken> element : row){
                    if(element.toArray().length > 1){
                        Iterator<InputToken> token =  element.iterator();
                        ArrayList<String> inputTokens = new ArrayList<>();
                        while(token.hasNext()){
                            inputTokens.add(token.next().getName());
                        }
                        if(Objects.equals(element.getFirst().getType(), "MULTI")){

                            field.add(
                                    Field.ofMultiSelectionType(
                                                    inputTokens
                                            )
                                            .label(element.getFirst().getLabel())
                                            .render(new SimpleCheckBoxControl<>())
                                            .required(true)
                                            .styleClass("field")
                            );
                        }
                        else {
                            field.add(
                                    Field.ofSingleSelectionType(
                                                    inputTokens
                                            )
                                            .label(element.getFirst().getLabel())
                                            .required(true)
                                            .styleClass("field")
                            );
                        }
                    }
                    else{
                        //System.out.println("element " + element.get(0).getName() + " " + element.get(0).getType());
                        if(Objects.equals(element.getFirst().getType(), "STRING")){
                            field.add(
                                    Field.ofSingleSelectionType(
                                                    Arrays.asList(
                                                            element.getFirst().getName()
                                                    )
                                            )
                                            .label(element.getFirst().getLabel())
                                            .required(true)
                                            .styleClass("field")
                            );
                        }
                        if(Objects.equals(element.getFirst().getType(), "TEXT")){
                            field.add(
                                    Field.ofStringType(new SimpleStringProperty(""))
                                            .label(element.getFirst().getLabel())
                                            .required(true)
                                            .placeholder("test")
                                            .validate(StringLengthValidator.between(
                                                            0,
                                                            element.getFirst().getMax(),
                                                            element.getFirst().getMax() + " Characters only"
                                                    )
                                            )
                                            .styleClass("field")
                            );
                        }
                        if(Objects.equals(element.getFirst().getType(), "INT")){
                            field.add(
                                    Field.ofIntegerType(0)
                                            .label(element.getFirst().getName())
                                            .required(true)
                                            .validate(IntegerRangeValidator.between(
                                                            element.getFirst().getMin(),
                                                            element.getFirst().getMax(),
                                                            "Please enter only numbers between " + element.getFirst().getMin() + " and " + element.getFirst().getMax()
                                                    )
                                            )
                                            .styleClass("field")
                            );
                        }
                        if(Objects.equals(element.getFirst().getType(), "FLOAT")){
                            field.add(
                                    Field.ofStringType("")
                                            .label(element.getFirst().getName())
                                            .required(true)
                                            .validate(
                                                    CustomValidator.forPredicate(
                                                            s -> {
                                                                double inputValue;
                                                                try {
                                                                    inputValue = Double.parseDouble(s);
                                                                    return (inputValue >= element.getFirst().getMin() && inputValue <= element.getFirst().getMax());
                                                                } catch(Exception e){
                                                                    return false;
                                                                }

                                                            },
                                                            "Not a double"
                                                    )
                                            )
                                            .styleClass("field")
                            );
                        }

                    }

                }

            }
            sections.add(
                    Section.of(
                                    field.toArray(new Element[0])
                            )
                            .collapsible(true)
                            .collapse(true)


            );
        }

        final int[] i = {0};
        String[] titles = new String[]{
                "Start-up",
                "Computational Grid",
                "Input Grids and Data",
                "Boundary and Initial Conditions",
                "Physics",
                "Numerics",
                "Output Locations",
                "Computed Quantities",
                "Lock-up"
        };
        sections.forEach(section ->{
            section.title(titles[i[0]]);
            i[0]++;
        });
        return sections;
    }

}
