package code;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class ReplaceThreeTimesCharTest {

    @InjectMocks
    ReplaceThreeTimesChar replaceThreeTimesChar;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void replaceThreeTimesChar_replaceTwoTimes() {
        String inputString = "aabbb";
        String outputString = replaceThreeTimesChar.replaceThreeTimesChar(inputString);
        assert outputString.equals("");
    }

    @Test
    public void requirementTwoTest_replaceFourTimes() {
        String inputString = "aabbccddde";
        String outputString = replaceThreeTimesChar.replaceThreeTimesChar(inputString);
        assert outputString.equals("e");
    }

    @Test
    public void requirementTwoTest_replaceInTheMiddle() {
        String inputString = "abbbacccdef";
        String outputString = replaceThreeTimesChar.replaceThreeTimesChar(inputString);
        assert outputString.equals("bdef");
    }

    @Test
    public void requirementTwoTest_notMatchThreeTimesChar() {
        String inputString = "aabbccddee";
        String outputString = replaceThreeTimesChar.replaceThreeTimesChar(inputString);
        assert outputString.equals("aabbccddee");
    }

    @Test
    public void requirementTwoTest_onlyTwoChar() {
        String inputString = "aa";
        String outputString = replaceThreeTimesChar.replaceThreeTimesChar(inputString);
        assert outputString.equals("aa");
    }
}