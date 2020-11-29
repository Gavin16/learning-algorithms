package leetcode;

public class Main {


    public static void main(String[] args) {
        String test  = "Let's take LeetCode contest";
        String s = new Main().reverseWords(test);
        System.out.println(s);
    }


    public String reverseWords(String s) {
        if(null == s || s.length() == 0) return s;

        String[] sa = s.split(" ");
        for(int i = 0 ; i <sa.length; i++){
            String ele = sa[i];
            char[] cs = ele.toCharArray();
            for(int j = 0 , k = ele.length()-1 ; j < k ; j++,k--){
                char temp = cs[j];
                cs[j] = cs[k];
                cs[k] = temp;
            }
            sa[i] = String.valueOf(cs);
        }
        return String.join(" ",sa);
    }
}
