package tests.reviewJDK.math;

public class IndexTest {


    public static void main(String[] args) {


        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 3 ; i++){
            sb.append("abc").append(",");
        }

        sb.replace(sb.length()-1,sb.length(),";");
        System.out.println(sb.toString());

    }
}
