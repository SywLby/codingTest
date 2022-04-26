package RequirementBusinessImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class RequirementBusinessTest {

    @InjectMocks
    RequirementBusiness requirementBusiness;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void requirementOneTest_inputNotOverThreeTimes() {
        String inputString = "aa";
        String outputString = requirementBusiness.requirementOne(inputString);

        assert outputString.equals("aa");
    }

    @Test
    public void requirementOneTest_inputHasOverThreeTimes() {
        String inputString = "aabbcccbad";
        String outputString = requirementBusiness.requirementOne(inputString);

        assert outputString.equals("d");
    }

    @Test
    public void requirementOneTest_doubleThree() {
        String inputString = "aaacccbbbddd";
        String outputString = requirementBusiness.requirementOne(inputString);

        assert outputString.equals("");
    }

    @Test
    public void requirementOneTest_theLastisTwoChar() {
        String inputString = "aabbcccbdd";
        String outputString = requirementBusiness.requirementOne(inputString);

        assert outputString.equals("aadd");
    }

    @Test
    public void requirementOneTest_overThreeCharButNotMatch() {
        String inputString = "aad";
        String outputString = requirementBusiness.requirementOne(inputString);

        assert outputString.equals("aad");
    }

    @Test
    public void requirementTwoTest_First() {
        String inputString = "aabbb";
        String outputString = requirementBusiness.requirementTwo(inputString);
        assert outputString.equals("");
    }

    @Test
    public void requirementTwoTest_second() {
        String inputString = "aabbccddde";
        String outputString = requirementBusiness.requirementTwo(inputString);
        assert outputString.equals("e");
    }

    @Test
    public void requirementTwoTest_Third() {
        String inputString = "abbbacccdef";
        String outputString = requirementBusiness.requirementTwo(inputString);
        assert outputString.equals("bdef");
    }

    @Test
    public void requirementTwoTest_Fourth() {
        String inputString = "aabbccddee";
        String outputString = requirementBusiness.requirementTwo(inputString);
        assert outputString.equals("aabbccddee");
    }

    @Test
    public void requirementTwoTest_Fifth() {
        String inputString = "aa";
        String outputString = requirementBusiness.requirementTwo(inputString);
        assert outputString.equals("aa");
    }
}