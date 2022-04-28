# codingTest
编程习题
Appreciate that the code will meet the requirements: 
1. Well-structure maven project 
2. Proper unit test with high test coverage 
3. Consider OO with proper design pattern 
4. Use Java 11 or above if possible (advantage option) 
4. Use some new feature from Java8 or above if possible (advantage option) 
** IMPORTANT ** 
Please do NOT put any wording onto github related to HSBC as cyber security issue, 
otherwise it would be considered as FAILED the code test. 
For a given string that only contains alphabet characters a-z, if 3 or more consecutive 
characters are identical, remove them from the string. Repeat this process until 
there is no more than 3 identical characters sitting besides each other. 
Example: 
Input: aabcccbbad 
Output: 
-> aabbbad 
-> aaad 
-> d 
#Stage 2 - advanced requirement 
Instead of removing the consecutively identical characters, replace them with a 
single character that comes before it alphabetically. 
Example: 
ccc -> b 
bbb -> a 
Input: abcccbad 
Output: 
-> abbbad, ccc is replaced by b 
-> aaad, bbb is replaced by a 
-> d
