package first;

import core.BaseTestDriver;
import org.junit.jupiter.api.Test;
import pages.firstExercise.FirstExercise;
import pages.secondExercise.SecondExercise;

public class Tests extends BaseTestDriver {

    @Test
    public void selectingMultiLanguageTest(){
        FirstExercise firstExercise = new FirstExercise();
        firstExercise.openTheLanguageDropDown();
        firstExercise.checkLanguages();
    }

    @Test
    public void checkTables() throws Exception {
        SecondExercise secondExercise = new SecondExercise();
        String cellText = secondExercise.getTableCellText(secondExercise.getTable(), 1, "Ernst Handel", 3);
        secondExercise.verifyTableCellText(secondExercise.getTable(), 0, "Austria", 0, cellText);
        String cellTextFromFile = secondExercise.fromFile(1, "Ernst Handel", 3);
        secondExercise.verifyTableCellText(secondExercise.getTable(), 0, "Austria", 0, cellTextFromFile);
    }
}
