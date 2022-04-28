package code;

import org.apache.commons.lang3.StringUtils;

import java.util.Stack;

public class DeleteThreeTimesChar {

    /**
     * deleteThreeTimesChar（把连续出现3次以上的字符进行删除）
     *
     * @param inputString String
     * @return outputString String
     */
    public String deleteThreeTimesChar(String inputString) {
        inputString = inputString.trim();
        System.out.println("Input:" + inputString);
        System.out.println("OutPut:");
        if (StringUtils.isNotBlank(inputString) && inputString.length() > 2) {
            String[] strs = getStrs(inputString);
            // 删除超过连续出现3次以上的字符的集合
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
     * 删除连续出现3次以上的字符
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

    /**
     *  输出处理后的字符串
     *
     * @param str 原始字符串数组
     * @param i 当前循环下标
     * @param strStack 字符串栈
     * @param numStack 对应数量栈
     * @param lastStr 上一个从栈去除的字符
     * @return tempBuffer.toString() 处理后的字符串结果
     */
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

    /**
     * 获取栈中剩余字符的字符串结果
     *
     * @param tempBuffer StringBuffer
     * @param tempStrStack 临时字符串栈
     * @param tempNumStack 临时数量栈
     */
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

    /**
     * 输出最后结果
     *
     * @param strStack 字符串栈
     * @param numStack 数量栈
     * @return stringBuffer.reverse().toString() String
     */
    private String showTheLastResult(Stack<String> strStack, Stack<Integer> numStack) {
        StringBuffer stringBuffer = new StringBuffer();
        getBufferFromStack(stringBuffer, strStack, numStack);
        System.out.println(stringBuffer);
        return stringBuffer.reverse().toString();
    }

    /**
     *  处理是最后一个字符的场景
     *
     * @param strs 原始字符串数组
     * @param i 当前下标
     * @param lastOne 数组最后一个
     * @param strStack 字符串栈
     * @param numStack 数量栈
     * @return String
     */
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
}
