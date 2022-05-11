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
        String result = new String(inputString);
        boolean havenDealWithDelete = false;
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
                    if (i == strs.length-1 && numStack.peek() >= 3) {
                        numStack.pop();
                        result = dealDeleteChar(strs, i, strStack, numStack, strStack.pop());
                        havenDealWithDelete = true;
                    }
                }
                continue;
            }

            if (!strStack.peek().equals(strs[i]) && numStack.peek() >= 3) {
                numStack.pop();
                result = dealDeleteChar(strs, i, strStack, numStack, strStack.pop());
                havenDealWithDelete = true;
                break;
            }else if (!strStack.peek().equals(strs[i]) && numStack.peek() < 3) {
                strStack.push(strs[i]);
                numStack.push(1);
            }
        }
        if (StringUtils.isNotBlank(result) && (havenDealWithDelete || !numStack.empty() && numStack.peek() >= 3)) {
            result = dealWithThreeTimeChar(getStrs(result), result);
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
     * @param lastStr 上次在栈顶取出的元素
     * @return tempBuffer.toString() 处理后的字符串结果
     */
    private String dealDeleteChar(String[] str, int i, Stack<String> strStack, Stack<Integer> numStack, String lastStr) {
        StringBuffer tempBuffer = new StringBuffer();
        Stack<String> tempStrStack = (Stack)strStack.clone();
        Stack<Integer> tempNumStack = (Stack)numStack.clone();
        getBufferFromStack(tempBuffer, tempStrStack, tempNumStack);
        // 栈顶先出所以需要反转
        tempBuffer.reverse();
        if (i != str.length -1 || (i == str.length - 1 && !lastStr.equals(str[i]))) {
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
}
