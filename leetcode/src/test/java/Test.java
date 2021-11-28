
import antlr.StringUtils;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName Test.scala
 * @author: lulong
 * @Date: 2021/10/31
 * @Time: 23:02
 */
public class Test {
    public static boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) return false;

        HashMap<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            if (charMap.containsKey(c) && charMap.get(c) != 0) {
                charMap.put(c, charMap.get(c) - 1);
            }
        }

        return charMap.values().stream().allMatch(x -> x == 0);

    }

    public static char findTheDifference(String s, String t) {
        if ("".equals(s)) return t.charAt(0);
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);

            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            charMap.put(c, charMap.getOrDefault(c, 0) - 1);
        }

        return charMap.keySet().stream().filter(x -> charMap.get(x) == 1).findFirst().orElse('c');

    }

    public static int longestPalindrome(String s) {
        int[] count = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c]++;
        }

        int result = 0;
        for (int num : count) {
            result += num / 2 * 2;
            if (num % 2 == 1 && result % 2 == 0) {
                result++;
            }
        }

        return result;
    }


    public static int thirdMax(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int n : nums) {
            treeSet.add(n);

            if (treeSet.size() > 3) {
                treeSet.remove(treeSet.first());
            }
        }

        return treeSet.size() == 3 ? treeSet.first() : treeSet.last();
    }

    public static String addStrings(String num1, String num2) {
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        int flag = 0;
        StringBuilder sb = new StringBuilder();
        while (len1 >= 0 || len2 >= 0 || flag != 0) {
            int x = len1 >= 0 ? num1.charAt(len1) - '0' : 0;
            int y = len2 >= 0 ? num2.charAt(len2) - '0' : 0;

            int res = x + y + flag;
            sb.append(res % 10);
            flag = res / 10;
            len1--;
            len2--;
        }

        sb.reverse();
        return sb.toString();
    }

    public static int countSegments(String s) {
        String[] s1 = s.split(" ");
        long res = Arrays.stream(s1).filter(x -> !x.trim().equals("")).count();
        return (int) res;
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }


    public static int findContentChildren(int[] g, int[] s) {
        int gLen = g.length;
        int sLen = s.length;
        int res = 0;
        int minSize = 0;

        Arrays.sort(g);
        Arrays.sort(s);

        for (int i = 0; i < gLen; i++) {
            for (int j = minSize; j < sLen; j++) {
                if (s[j] >= g[i]) {
                    minSize = j;
                    res += 1;
                    i++;
                }
            }
        }
        return res;
    }

    public static String licenseKeyFormatting(String s, int k) {
        String newStr = s.replaceAll("-", "");
        int len = newStr.length();
        int index = len - 1;
        StringBuilder sb = new StringBuilder();


        while (index >= 0) {
            sb.append(Character.toUpperCase(newStr.charAt(index)));
            if ((len - index) % k == 0 && index > 0) {
//               sb.append(Character.toUpperCase(newStr.charAt(index)));
                sb.append("-");
            }
            index--;
        }

        sb.reverse();
        return sb.toString();
    }


    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        int result = 0;
        int preMax = 0;
        for (int i = 0; i < timeSeries.length; i++) {
            if (timeSeries[i] >= preMax) {
                result += duration;
            } else {
//                result += duration - (preMax - timeSeries[i]);
                result += timeSeries[i] + duration - preMax;
            }
            preMax = timeSeries[i] + duration;
        }

        return result;
    }

    public static void setZeroes(int[][] matrix) {
        int len = matrix.length;
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if ((matrix[i][j]) == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                } else {
                    if (rowSet.contains(i) || colSet.contains(j)) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                if (rowSet.contains(i) || colSet.contains(j)) {
//                    matrix[i][j] = 0;
//                }
//            }
//        }
    }


    public static int multiply(int A, int B) {
        B = B - 1;
        if (B == 0) return A;
        return multiply(A, B) + A;
    }


    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        generate(sb, n, n - 1, n, res);

        return res;
    }

    public static void generate(StringBuilder sb, int n, int left, int right, List<String> res) {
        if (sb.length() == n * 2) {
            res.add(sb.toString());
            return;
        }

        if (left > 0) {
            sb.append("(");
            generate(sb, n, left - 1, right, res);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (right > 0 && right > left) {
            sb.append(")");
            generate(sb, n, left, right - 1, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        int[][] martixs = {{0, 0, 0, 5},
                {4, 3, 1, 4},
                {0, 1, 1, 4},
                {1, 2, 1, 3},
                {0, 0, 1, 1}};
        setZeroes(martixs);
//        System.out.println(Arrays.deepToString(martixs));
//        System.out.println(multiply(3, 4));
        System.out.println(generateParenthesis(3));
    }
}
