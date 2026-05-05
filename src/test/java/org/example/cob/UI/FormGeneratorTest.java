package org.example.cob.UI;
import java.util.List;
import com.dlsc.formsfx.model.structure.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormGeneratorTest {

    FormGenerator formGenerator;

    @BeforeEach
    public void instantiate(){
        this.formGenerator = new FormGenerator("/swan-test.csv");
    }

    @Test
    void isInstantiatedTest(){
        assertTrue(formGenerator != null);
    }

    @Test
    void returnsArrayOfSectionsTest(){
        List<Section> sections = formGenerator.getSections();
        assertEquals(2, sections.size());
    }

    @Test
    void assignsTitlesToSections(){
        List<Section> sections = formGenerator.getSections();
        String title = sections.get(0).getTitle();
        assertEquals("Start-up", title);
    }
}
