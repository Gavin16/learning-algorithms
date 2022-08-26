package leetcode.byteDance;




import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Day13 {


    public static void main(String[] args) {
        Day13 day13 = new Day13();


        char c1 = '3';
        char c2 = 'd';

        System.out.println(Character.getNumericValue(c1));
        System.out.println(Character.getNumericValue(c2));
    }

    void sortBDSubjects() throws IOException {

        String path = this.getClass().getResource("/").getPath();
        FileInputStream fis1 = new FileInputStream(path + "/repeated50");
        FileInputStream fis2 = new FileInputStream(path + "/repeated250");

        BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));


        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        String line = null;
        while((line = br1.readLine()) != null){
            if(!"".equals(line)){
                list1.add(line + "\r\n");
            }
        }
        line = null;
        while((line = br2.readLine()) != null){
            if(!"".equals(line)){
                list2.add(line + "\r\n");
            }
        }

        Collections.sort(list2, Comparator.comparing(o -> {
            String[] split = o.split("\\.");
            if(isNumber(split[0])){
                return Integer.parseInt(split[0]);
            }
            return Integer.MAX_VALUE;
        }));
        File sorted_repeated_50 = new File("sorted_repeated_50");
        File sorted_repeated_250 = new File("sorted_repeated_250");
        FileOutputStream fos1 = new FileOutputStream(sorted_repeated_50);
        FileOutputStream fos2 = new FileOutputStream(sorted_repeated_250);


        for(int i = 0; i < list1.size(); i++){
            String s = list1.get(i);
            if(null != s && !"".equals(s)){
                fos1.write(s.getBytes());
            }
        }


        for(int i = 0; i < list2.size(); i++){
            String s = list2.get(i);
            if(null != s && !"".equals(s)){
                fos2.write(s.getBytes());
            }
        }

        fos1.close();
        fos2.close();
        fis1.close();
        fis2.close();
    }

    private boolean isNumber(String s) {
        for(int i = 0; i < s.length(); i++){
            if(!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }


}
