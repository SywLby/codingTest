package code;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class DeleteThreeTimesCharTest {

    @InjectMocks
    DeleteThreeTimesChar deleteThreeTimesChar;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deteleThreeTimesCharTest_inputNotOverThreeTimes() {
        String inputString = "aa";
        String outputString = deleteThreeTimesChar.deleteThreeTimesChar(inputString);

        assert outputString.equals("aa");
    }

    @Test
    public void deteleThreeTimesCharTest_inputHasOverThreeTimes() {
        String inputString = "aabbcccbad";
        String outputString = deleteThreeTimesChar.deleteThreeTimesChar(inputString);

        assert outputString.equals("d");
    }

    @Test
    public void deteleThreeTimesCharTest_doubleThree() {
        String inputString = "aaacccbbbddd";
        String outputString = deleteThreeTimesChar.deleteThreeTimesChar(inputString);

        assert outputString.equals("");
    }

    @Test
    public void deteleThreeTimesCharTest_theLastisTwoChar() {
        String inputString = "aabbcccbdd";
        String outputString = deleteThreeTimesChar.deleteThreeTimesChar(inputString);

        assert outputString.equals("aadd");
    }

    @Test
    public void deteleThreeTimesCharTest_overThreeCharButNotMatch() {
        String inputString = "aad";
        String outputString = deleteThreeTimesChar.deleteThreeTimesChar(inputString);

        assert outputString.equals("aad");
    }

}