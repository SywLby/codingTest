package RequirementBusinessImpl;

import IRequirementBusiness.IRequirementBusiness;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RequirementBusiness implements IRequirementBusiness {

    private final static String UNDER_LINE = "_";

    private final static String MAP_KEY = "isExistsOverThreeTimesChar";

    private final static Integer MAP_VALUE_TRUE = 1;

    private final static Integer MAP_VALUE_FALSE = 0;


    private Map<String, Integer> isExistsOverThreeTimesCharMap = new HashMap<>();

    /**
     * requirementone（把连续出现3次以上的字符进行删除）
     *
     * @param inputString String
     * @return outputString String
     */
    @Override
    public String requirementOne(String inputString) {
        inputString = inputString.trim();
        System.out.println("Input:" + inputString);
        System.out.println("OutPut:");
        if (StringUtils.isNotBlank(inputString) && inputString.length() > 2) {
            String[] strs = getStrs(inputString);
            // 处理超过连续出现3次以上的字符的集合
            String outPutResult = dealWithThreeTimeChar(strs, inputString);
            return outPutResult;
        }else {
            System.out.println(inputString);
            return inputString;
        }
    }

    /**
     * 将入参中的字符串转成数组
     *
     * @param  inputString String
     * @return strs String[]
     */
    private static String[] getStrs(String inputString) {
        String[] strs = inputString.split("");
        return strs;
    }

    /**
     *
     * @param strs String[]输入的字段转化成的数组
     * @param inputString String
     * @return
     */
    private String dealWithThreeTimeChar(String[] strs, String inputString) {
        Stack<String> strStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        String result = new String();
        Integer lastOne = strs.length - 1;
        for (int i = 0;i<strs.length;i++) {
            if (strStack.empty()) {
                strStack.push(strs[i]);
                if(numStack.empty()) {
                    numStack.push(1);
                }
                continue;
            }
            if (strStack.peek().equals(strs[i])) {
                if (numStack.peek() != null) {
                    int nowNum = numStack.pop();
                    numStack.push(nowNum+1);
                }
                // 处理lastOne的场景
                result = dealLastOne(strs, i, lastOne, strStack, numStack);
                continue;
            }

            if (!strStack.peek().equals(strs[i]) && numStack.peek() >= 3) {
                String lastStr = strStack.pop();
                numStack.pop();
                outPutAfterPop(strs, i, strStack, numStack, lastStr);
                if (!strStack.empty() && strStack.peek().equals(strs[i])) {
                    if (numStack.peek() != null) {
                        int nowNum = numStack.pop();
                        numStack.push(nowNum+1);
                    }
                    // 如果此时已经是最后一个，则需要判断栈中顶元素是否超过3个
                    result = dealLastOne(strs ,i , lastOne, strStack, numStack);
                    continue;
                } else {
                    strStack.push(strs[i]);
                    numStack.push(1);
                    // 如果此时已经是最后一个，则需要判断栈中顶元素是否超过3个
                    result = dealLastOne(strs ,i , lastOne, strStack, numStack);
                    continue;
                }
            }else if (!strStack.peek().equals(strs[i]) && numStack.peek() < 3) {
                strStack.push(strs[i]);
                numStack.push(1);
                if (lastOne.equals(i)) {
                    result = showTheLastResult(strStack, numStack);
                    return result;
                }
            }
        }

        return result;
    }

    private String outPutAfterPop(String[] str, int i, Stack<String> strStack, Stack<Integer> numStack, String lastStr) {
        StringBuffer tempBuffer = new StringBuffer();
        Stack<String> tempStrStack = (Stack)strStack.clone();
        Stack<Integer> tempNumStack = (Stack)numStack.clone();
        getBufferFromStack(tempBuffer, tempStrStack, tempNumStack);
        // 栈顶先出所以需要反转
        tempBuffer.reverse();
        if (i != str.length - 1 && !lastStr.equals(str[i])) {
            for (int j = i; j < str.length; j++) {
                tempBuffer.append(str[j]);
            }
        }
        System.out.println(tempBuffer);
        return tempBuffer.toString();
    }

    private void getBufferFromStack(StringBuffer tempBuffer, Stack<String> tempStrStack, Stack<Integer> tempNumStack) {
        if (!tempStrStack.empty()) {
            int stackSize = tempStrStack.size();
            for (int m = 0; m < stackSize; m++) {
                String tempStrString = tempStrStack.pop();
                if (!tempNumStack.empty()) {
                    int loopNum = tempNumStack.pop();
                    for (int n = 0; n < loopNum; n++) {
                        tempBuffer.append(tempStrString);
                    }
                }
            }
        }
    }

    private String showTheLastResult(Stack<String> strStack, Stack<Integer> numStack) {
       StringBuffer stringBuffer = new StringBuffer();
       getBufferFromStack(stringBuffer, strStack, numStack);
        System.out.println(stringBuffer);
        return stringBuffer.reverse().toString();
    }

    private String dealLastOne(String[] strs, int i, Integer lastOne, Stack<String> strStack, Stack<Integer> numStack) {
        if (lastOne.equals(i)) {
           if(!strStack.empty() && !numStack.empty() && numStack.peek() >= 3) {
               String lastStr = strStack.pop();
               numStack.pop();
               return outPutAfterPop(strs, i, strStack, numStack, lastStr);
           }else {
               return showTheLastResult(strStack, numStack);
           }
        }
        return "";
    }

    /**
     * requirementTwo（把连续出现3次以上的字符，使用该字符在字母表中的上一个字符进行代替，若连续出现的是啊a，则删除）
     *
     * @param inputString String
     * @return outputString String
     */
    @Override
    public String requirementTwo(String inputString) {
        inputString = inputString.trim();
        System.out.println("Input:" + inputString);
        System.out.println("OutPut:");
        if (StringUtils.isNotBlank(inputString) && inputString.length() > 2) {
            String[] strs = getStrs(inputString);
            // 处理超过连续出现3次以上的字符的集合
            String outPutResult = replaceOverThreeTimeChar(strs, inputString);
            return outPutResult;
        }else {
            System.out.println(inputString);
            return inputString;
        }
    }

    private String replaceOverThreeTimeChar(String[] strs, String inputString) {
        Stack<String> strStack = new Stack<>();
        String outputResult = new String(inputString);
        Integer lastOne = strs.length - 1;
        isExistsOverThreeTimesCharMap.put(MAP_KEY, MAP_VALUE_FALSE);
        for (int i = 0;i<strs.length;i++) {
            if (strStack.isEmpty()) {
                strStack.push(strs[i] + UNDER_LINE + 1);
                continue;
            }

            boolean isLastOne = lastOne.equals(i);
            if (getStrStackString(strStack).equals(strs[i])) {
                Integer existsNum = getStrStackNum(strStack);
                if (isLastOne && existsNum+1 >= 3) {
                    outputResult = strAfterReplace(strStack, existsNum+1, isLastOne, strs, i);
                    break;
                }else {
                    dealWithAddNum(strStack,existsNum);
                    continue;
                }
            }

            if (!getStrStackString(strStack).equals(strs[i]) && getStrStackNum(strStack) >= 3) {
                if (isLastOne) {
                    outputResult = strAfterReplace(strStack, getStrStackNum(strStack), isLastOne, strs, i);
                }else {
                    outputResult = strAfterReplace(strStack, getStrStackNum(strStack), isLastOne, strs, i);
                }
                break;
            }else if (!getStrStackString(strStack).equals(strs[i]) && getStrStackNum(strStack) <= 3){
                strStack.push(strs[i] + UNDER_LINE + 1);
                continue;
            }
        }

        if (MAP_VALUE_TRUE.equals(isExistsOverThreeTimesCharMap.get(MAP_KEY))) {
            outputResult = replaceOverThreeTimeChar(getStrs(outputResult), outputResult);
        }
        if (outputResult.equals(inputString)) {
            System.out.println("no over three times char was replaced");
        }
        return outputResult;
    }

    private String getStrStackString(Stack<String> strStack) {
        if (!strStack.empty()) {
            String strAndNum = strStack.peek();
            String str = strAndNum.split(UNDER_LINE)[0];
            return str;
        }
        return "";
    }

    private Integer getStrStackNum(Stack<String> strStack) {
        if (!strStack.empty()) {
            String strAndNum = strStack.peek();
            Integer num = Integer.valueOf(strAndNum.split(UNDER_LINE)[1]);
            return num;
        }
        return 0;
    }

    private void dealWithAddNum(Stack<String> strStack, Integer existsNum) {
        if (!strStack.empty()) {
            String strAndNum = strStack.pop();
            String str = strAndNum.split(UNDER_LINE)[0];
            Integer tempNum = existsNum + 1;
            strStack.push(str + UNDER_LINE + tempNum);
        }
    }

    private String strAfterReplace(Stack<String> strStack, Integer needReplaceNum, boolean isLastOne, String[] strs, int index) {
        String str = strStack.pop().split(UNDER_LINE)[0];
        String beforeStrChar = getBeforeStrChar(str);
        String repaceString = getReplaceString(str, needReplaceNum);
        String strAfterReplace = getStrAfterReplace(strStack, isLastOne, strs, index, beforeStrChar, str);
        if (StringUtils.isNotBlank(beforeStrChar)) {
            System.out.println(strAfterReplace + "," + repaceString + " is replaced by " + beforeStrChar);
        }else {
            System.out.println(strAfterReplace + ", " + repaceString + " is deleted");
        }
        if (getStrStackString(strStack).equals(beforeStrChar)) {
            isExistsOverThreeTimesCharMap.put(MAP_KEY, MAP_VALUE_TRUE);
        }
        return strAfterReplace;
    }

    private String getBeforeStrChar(String str) {
        if (str.equals("a")) {
            return "";
        }else {
            int asciiNum = Integer.valueOf(str.charAt(0));
            asciiNum--;
            char beforeChar = (char) asciiNum;
            return String.valueOf(beforeChar);
        }
    }

    private String getReplaceString(String str, Integer needReplaceNum) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i<needReplaceNum; i++) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    private String getStrAfterReplace(Stack<String> strStack, boolean isLastOne, String[] strs, int index, String beforeStrChar, String lastStr) {
        StringBuffer stringBuffer = new StringBuffer();
        Stack<String> tempStrStack = (Stack)strStack.clone();
        if (!tempStrStack.empty()) {
            int stackSize = tempStrStack.size();
            for (int m = 0; m < stackSize; m++) {
                String tempStrString = tempStrStack.pop();
                String strValue = tempStrString.split(UNDER_LINE)[0];
                int loopNum = Integer.valueOf(tempStrString.split(UNDER_LINE)[1]);
                for (int n = 0; n < loopNum; n++) {
                    stringBuffer.append(strValue);
                }
            }
        }
        stringBuffer.reverse();
        stringBuffer.append(beforeStrChar);
        if (isLastOne && !lastStr.equals(strs[index])) {
            stringBuffer.append(strs[index]);
        }else if (!isLastOne) {
            for (int i = index;i<strs.length;i++){
                stringBuffer.append(strs[i]);
            }
        }
        return StringUtils.isBlank(stringBuffer.toString()) ? "" : stringBuffer.toString();
    }
}
