package leetCode;

public class CompressString {

    public String compressString(String S) {
        if(S.length() <= 1) return S;

        StringBuilder sb = new StringBuilder();

        char oldChar = S.charAt(0);
        int num = 0;
        for(int i = 0 ; i < S.length() ; i++){
            char newChar = S.charAt(i);
            if(oldChar == newChar){
                num++;
            }else{
                sb.append(oldChar).append(num);
                oldChar = newChar;
                num = 1;
            }
        }
        sb.append(oldChar).append(num);

        return sb.length() >= S.length() ? S : sb.toString();
    }
}
