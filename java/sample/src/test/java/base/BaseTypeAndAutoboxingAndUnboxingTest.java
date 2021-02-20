package base;

import org.junit.Assert;
import org.junit.Test;

/**
 * 基本类型
 *
 * 八大基本类型：byte、short、int、long、float、double、char、boolean
 * 包装类型都声明final、不可继承
 * 包装类型自动拆箱
 *
 * @author 天台卧底
 * @date 2021/1/30 23:49
 */
public class BaseTypeAndAutoboxingAndUnboxingTest {

    /**
     * 测试基本类型自动装箱和自动拆箱
     * 基本类型装箱调用包装类型的valueOf()方法（boolean除外）
     * 包装类型拆箱调用包装类型的[类型Value()]方法（boolean除外）
     * 可通过反编译软件查看class文件
     */
    @Test
    public void testAutoboxingAndUnboxing(){
        Integer aab = 1; //调用Integer包装类型valueOf()方法装箱
        int aub = aab; //调用Integer包装类型intValue()方法拆箱
        Float fab=1F; // 调用Float包装类型valueOf()方法装箱
        float fub=fab; //调用Float包装类型floatValue()方法拆箱
    }

    /**
     * 基本类型与对应包装类
     * byte     Byte
     * short    Short
     * int      Integer
     * long     Long
     * float    Float
     * double   Double
     * char     Character
     * boolean  Boolean
     */
    @Test
    public void testBaseTypeAndWrapperClass(){
        byte b=1;
        Byte bw=1;
        System.out.println(b==bw);//true
        short s=1;
        Short sw=1;
        System.out.println(s==sw);//true
        int i=1;
        Integer iw=1;
        System.out.println(i==iw);//true
        long l=1;
        Long lw=1l;//加l或L
        System.out.println(l==lw);//true
        float f=1;
        Float fw=1f;//加f或F
        System.out.println(f==fw);//true
        double d=1.0;
        Double dw=1.0;
        System.out.println(d==dw);//true
        char c='a';
        Character cw='a';
        System.out.println(c==cw);//true
        boolean bb=true;
        Boolean bbw=true;
        System.out.println(bb==bbw);//true
    }

    /**
     * 测试自动装箱，包装类会自动调用valueOf()方法装箱
     */
    @Test
    public void testAutoBoxing(){
        Integer a1 = 123;
        Integer a2 = Integer.valueOf(123);
        Assert.assertTrue(a1==a2);//断言不会报错

        Boolean b1=true;
        Boolean b2=Boolean.valueOf(true);
        System.out.println(b1==b2);//断言不会报错

        Character c1 = 'a';
        Character c2 = Character.valueOf('a');
        System.out.println(c1==c2);//断言不会报错

    }

    /**
     * 测试自动装箱会使用缓存池中常量
     */
    @Test
    public void testAutoBoxingCache(){
        Integer m = 123;
        Integer n = 123;
        Integer x = Integer.valueOf(123);
        Integer y = Integer.valueOf(123);
        Assert.assertTrue(m==n);//断言不会报错
        Assert.assertTrue(x==y);//断言不会报错
    }

    /**
     * 测试包装类构造函数与装箱函数valueOf()区别
     * 构造函数：Integer包装类构造函数会新建一个对象
     * 装箱函数：Integer包装类装箱函数，-128~127从缓存池中取同个对象，反之会调用构造函数新建对象
     */
    @Test
    public void testConstructorAndBoxingMethod(){
        Integer a=new Integer(123);
        Integer b=new Integer(123);
        //Assert.assertTrue(a==b);//断言会报错，说明每次都会新建一个对象
        Integer c = Integer.valueOf(123);
        Integer d = Integer.valueOf(123);
        //Assert.assertTrue(c==d);//断言不会报错，说明装箱函数取得的是缓存池中同个对象
        Integer e = Integer.valueOf(180);
        Integer f = Integer.valueOf(180);
        //Assert.assertTrue(e==f);//断言会报错，说明装箱函数内部实现新创建一个对象
    }

}
