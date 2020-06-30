import com.alibaba.fastjson.JSON;
import tools.dto.DetailDto;

/**
 * @ClassName: TestJsonCharCase
 * @CopyRight:
 * @Description: 测试JSON是否区分大小写
 * @Author: wufangmin
 * @Date: 2020/5/27 上午 10:03
 * @Version:
 */
public class TestJsonCharCase {


    public static void main(String[] args) {

        DetailDto detailDto = new DetailDto() {
            {
                this.setGoodsCode("123455");
                this.setArrivedNumber(123);
            }
        };

        System.out.println(JSON.toJSONString(detailDto));

    }
}
