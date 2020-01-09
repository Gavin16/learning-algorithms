package tests;

/**
 * @ClassName: Book
 * @Description: 类初始化顺序及实例初始化顺序
 * @Author: wufangmin
 * @Date: 2019/12/9 11:12
 * @Version: 1.0
 */
public class Book {
    public static void main(String[] args)
    {
        staticFunction();
    }

    static Book book = new Book();

    static
    {
        System.out.println("书的静态代码块");
    }

    {
        System.out.println("书的普通代码块");
    }

    Book()
    {
        System.out.println("书的构造方法");
        System.out.println("price=" + price +",amount=" + amount);
    }

    public static void staticFunction(){
        System.out.println("书的静态方法");
    }

    int price = 110;
    static int amount = 112;
}
