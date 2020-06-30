/**
 * @ClassName: TestStringParse
 * @CopyRight: 百果科技
 * @Description:
 * @Author: wufangmin
 * @Date: 2020/5/12 下午 5:17
 * @Version:
 */
public class TestStringParse {


    public static void main(String[] args) {



        String testInput = "库房【shxgk】商品【119396】库存不足，出库失败!";

        int dis = testInput.indexOf("【",0);
        int die = testInput.indexOf("】",0);

        int gis = testInput.indexOf("【",die+1);
        int gie = testInput.indexOf("】",die+1);

        String depot = testInput.substring(dis+1,die);
        String goodsCode = testInput.substring(gis+1,gie);

        System.out.println(depot);
        System.out.println(goodsCode);

    }
}
