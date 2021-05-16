package problems;

/**
 *
 *    儿童俱乐部
 *  ×        儿
 *  -----------
 *  部部部部部部
 *
 *
 */
public class Exhaustion {


    public static void main(String[] args) {
        for(int i = 10000 ; i <= 99999 ; i++){
            int x = i % 10;
            int y = i / 10000;
            int sixX = x * 100000 +  x * 10000 + x * 1000 + x * 100 + x * 10 + x ;

            if(y * i == sixX){
                System.out.println("儿童俱乐部=" + i);
            }
        }
    }

}
