package base;

import org.junit.Test;

/**
 * String字符串类，声明为final，不可继承，不可变
 * 在 Java 8 中，String 内部使用 char 数组存储数据。
 * 在 Java 9 之后，String 类的实现改用 byte 数组存储字符串，同时使用 coder 来标识使用了哪种编码。
 * 引用字面量常量都是从字符串常量池中取，两个字面量常量相同则引用相同。
 * <p>
 * String 不可变 线程安全，内部byte数组
 * StringBuilder 可变 线程不安全，内部byte数组，超过用量重新拷贝创建新数组
 * StringBuffer  可变 线程安全 内部加锁synchronized，内部byte数组，超过用量重新拷贝创建新数组
 *
 * @author 天台卧底
 * @date 2021/2/2 22:30
 */
public class StringTest {

    /**
     * String字符串
     * final类，不可继承
     * Java8，内部final char数组；java9开始，内部final byte数组
     * 类不可继承，内部final不可变，线城安全
     */
    @Test
    public void testString() {
        String str1 = "bbb";
        String str2 = "aaa";
        System.out.println(str1 == str2);//false
        str2 = "bbb";
        System.out.println(str1 == str2);//true，说明引用字面量常量都是从缓冲池中取
        String str3 = new String("bbb");
        System.out.println(str2 == str3);//false
        String str4 = new String("bbb");
        System.out.println(str3 == str4);//false，说明构造函数每次都是新创建一个对象
    }

    /**
     * StringBuilder字符串构造器
     * final类，不可继承
     * byte数组，可变，超过内部数组容量会调用系统拷贝函数重新创建内部新数组
     * 线城不安全
     */
    @Test
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append("a").append("b").append("c");
        System.out.println(sb.toString());
    }

    /**
     * StringBuffer字符串构造器
     * final类，不可继承
     * byte数组，可变，超过内部数组容量会调用系统拷贝函数重新创建内部新数组
     * append()方法加锁synchronized，线程安全
     */
    @Test
    public void testStringBuffer() {
        StringBuffer sb = new StringBuffer();
        sb.append("a").append("b").append("c");
        System.out.println(sb.toString());
    }

    /**
     * 字符串常量池
     *
     */
    @Test
    public void testStringPool(){

    }
}
