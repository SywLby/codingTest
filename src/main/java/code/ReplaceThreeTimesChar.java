package code;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ReplaceThreeTimesChar {

    private final static String UNDER_LINE = "_";

    private final static String MAP_KEY = "isExistsOverThreeTimesChar";

    private final static Integer MAP_VALUE_TRUE = 1;

    private final static Integer MAP_VALUE_FALSE = 0;


    private Map<String, Integer> isExistsOverThreeTimesCharMap = new HashMap<>();


    /**
     * replaceThreeTimesChar（把连续出现3次以上的字符，使用该字符在字母表中的上一个字符进行代替，若连续出现的是啊a，则删除）
     *
     * @param inputString String
     * @return outputString String
     */
    public String replaceThreeTimesChar(String inputString) {
        inputString = inputString.trim();
        System.out.println("Input:" + inputString);
        System.out.println("OutPut:");
        if (StringUtils.isNotBlank(inputString) && inputString.length() > 2) {
            String[] strs = getStrs(inputString);
            // 处理超过连续出现3次以上的字符的集合
            String outPutResult = replaceOverThreeTimeChar(strs, inputString);
            if (outPutResult.equals(inputString)) {
                System.out.println("no over three times char was replaced");
            }
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
     * @param strs 原始字符串数组
     * @param inputString 原始输入的字符串
     * @return String 最终输出结果
     */
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
                Integer addValue = existsNum + 1;
                strStack.pop();
                strStack.push(strs[i]+ UNDER_LINE + addValue);
                if (isLastOne && addValue >= 3) {
                    outputResult = strAfterReplace(strStack, addValue, isLastOne, strs, i);
                    break;
                }
                continue;
            }

            if (!getStrStackString(strStack).equals(strs[i]) && getStrStackNum(strStack) >= 3) {
                outputResult = strAfterReplace(strStack, getStrStackNum(strStack), isLastOne, strs, i);
                break;
            }else if (!getStrStackString(strStack).equals(strs[i]) && getStrStackNum(strStack) <= 3){
                strStack.push(strs[i] + UNDER_LINE + 1);
                continue;
            }
        }

        if (MAP_VALUE_TRUE.equals(isExistsOverThreeTimesCharMap.get(MAP_KEY))) {
            outputResult = replaceOverThreeTimeChar(getStrs(outputResult), outputResult);
        }
        return outputResult;
    }

    /**
     *  获取栈顶元素中的字符串
     *
     * @param strStack 栈
     * @return String 栈顶元素中的字符串
     */
    private String getStrStackString(Stack<String> strStack) {
        if (!strStack.empty()) {
            String strAndNum = strStack.peek();
            String str = strAndNum.split(UNDER_LINE)[0];
            return str;
        }
        return "";
    }

    /**
     * 获取栈顶元素中的数量
     *
     * @param strStack 栈
     * @return Integer 栈顶元素中的数量
     */
    private Integer getStrStackNum(Stack<String> strStack) {
        if (!strStack.empty()) {
            String strAndNum = strStack.peek();
            Integer num = Integer.valueOf(strAndNum.split(UNDER_LINE)[1]);
            return num;
        }
        return 0;
    }

    /**
     * 替换后的字符串
     *
     * @param strStack 栈
     * @param needReplaceNum 需要替换掉的字符串
     * @param isLastOne 是否最后一个字符
     * @param strs 原始字符串数组
     * @param index 当前下标
     * @return String 替换后的字符串
     */
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

    /**
     * 获取需要替换的字符的前一个字符
     *
     * @param str 当前字符
     * @return String 前一个字符
     */
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

    /**
     * 获取需要替换的字符串
     *
     * @param str 需要被替换的字符
     * @param needReplaceNum 需要被替换的字符的数量
     * @return String 替换的字符串
     */
    private String getReplaceString(String str, Integer needReplaceNum) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i<needReplaceNum; i++) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    /**
     *  获取替换后的字符串
     *
     * @param strStack 栈
     * @param isLastOne 是否最后一个字符
     * @param strs 原始数组
     * @param index 当前下标
     * @param beforeStrChar 需要被替换的字符前一个字符
     * @param lastStr 栈顶刚取出的元素中的字符串结果
     * @return String 替换后的结果
     */
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
