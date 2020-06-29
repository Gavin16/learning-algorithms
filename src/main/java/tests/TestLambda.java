package tests;

import org.apache.commons.compress.utils.Lists;
import tools.GenerateJsonDataToWms;

import java.util.ArrayList;
import java.util.List;

public class TestLambda extends GenerateJsonDataToWms {

    public static void main(String[] args) {

        try {
            List<String> intList = new ArrayList<>();
            for(int i = 0 ; i < 10 ; i++){
                intList.add(String.valueOf(i));
            }
            intList.set(0,"");

            intList.stream().forEach(
                    e-> {System.out.println( Long.parseLong(e.substring(e.indexOf("_") + 1)));}
            );
            System.out.println("end line------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
