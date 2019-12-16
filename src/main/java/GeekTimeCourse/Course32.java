package GeekTimeCourse;

import java.io.*;

/**
 * @ClassName: Course32
 * @CopyRight: wufangmin
 * @Description: 字符串匹配算法
 * @Author: wufangmin
 * @Date: 2019/12/16 10:19
 * @Version: 1.0
 */
public class Course32 {

    public static void main(String[] args) throws IOException {

        // TODO 改为相对路径
        // 比较结果如下
        //-1
        //BF算法耗时：74
        //-1
        //RK算法耗时:36
        File f = new File("D:\\documents\\临时资料\\strings.txt");
        FileInputStream fis = new FileInputStream(f);

        byte[] data = new byte[1024];
        byte[] bytes = new byte[10240000];
        int len,i = 0;
        while((len = fis.read(data))!= -1){
            for(int j = 0 ; j < len ; j++){
                bytes[i++] = data[j];
            }
        }

        String text = new String(bytes);
        String template = "atcegtrgtrhthtehethngfngngndhnfgnfgnhetntymfnfyh";

        // 两种算法执行耗时比较
        long startTm1 = System.currentTimeMillis();
        System.out.println(bfStringMatch(text,template));
        System.out.println("BF算法耗时：" + (System.currentTimeMillis() - startTm1));

        long startTm2 = System.currentTimeMillis();
        System.out.println(rkStringMatch(text,template));
        System.out.println("RK算法耗时:" + (System.currentTimeMillis() - startTm2));
    }


    /**
     *
     * 假定字符串中只包含
     * RK 字符串匹配算法：
     * (1) 设计Hash算法，求字符串的hashCode
     * (2) 视情况对数据做预处理提高执行效率
     * (3) 对text 逐字符的匹配字符串
     * (4) 若遇到 hashCode 相同且字符串也相同则返回当前id
     *
     * @param text
     * @param temp
     * @return
     */
    public static int rkStringMatch(String text,String temp){
        if(text == null || temp == null) return -1;
        int textLen = text.length(),tLen = temp.length();
        if(textLen == 0 || tLen == 0 || textLen < tLen) return -1;

        int hTemp = hashCode(temp),lastHashCode = 0;
        for (int i = 0; i <= (textLen - tLen) ; i++){
            int hMatch = hashCode(text,i,tLen,lastHashCode);
            if(hMatch == hTemp && isTwoStringMatch(text,i,temp,tLen)){
                return i;
            }
        }
        return -1;
    }

    /**
     *
     */
    static int hashCode(String str){
        int len = str.length();
        return hashCode(str,0,len,0);
    }

    /**
     * 分情况计算hashCode, 可以减少大字符串匹配的效率
     */
    static int hashCode(String str,int s,int len,int lastCode){
        if(len > 0){
            int hashCode;
            if(s > 0){
                hashCode = lastCode - str.charAt(s - 1) + str.charAt(s + len -1);
            }else{
                hashCode = 0;
                for(int i = s ; i < s + len ; i++){
                    hashCode += str.charAt(i);
                }
            }
            return hashCode;
        }
        return 0;
    }
    /**
     * 字符串匹配 BF算法
     * 返回text中首次出现 template 的字符id
     */
    public static int bfStringMatch(String text, String temp){
        if(text == null || temp == null) return -1;
        int textLen = text.length(),tLen = temp.length();
        if(textLen == 0 || tLen == 0 || textLen < tLen) return -1;

        for (int i = 0; i <= (textLen - tLen) ; i++){
            if(isTwoStringMatch(text,i,temp,tLen)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 比较两个字符串是否匹配
     * @param text 需要用来匹配的字符串
     * @param i
     * @param temp 字符串模板
     * @param tLen 模板长度
     * @return
     */
    private static boolean isTwoStringMatch(String text, int i, String temp, int tLen) {
        for(int j = 0; j < tLen ; j++){
            if(text.charAt(j+i) != temp.charAt(j)){
                return false;
            }
        }
        return true;
    }
}
