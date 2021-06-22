import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式：静态代理、动态代理
 * 静态代理：每一个类都有一个委托代理类
 * 动态代理：JDK动态代理、cglib动态代理
 *
 * @author duchao
 */
public class ProxyTest {

    /**
     * 静态代理：需要为每个具体实现类实现一个代理类
     */
    @Test
    public void testStaticProxy(){
        AudiProxy audiProxy = new AudiProxy(new Audi());
        audiProxy.run();
    }

    /**
     * JDK动态代理
     */
    @Test
    public void testJdkDynamicProxy(){
        Car audiCar = (Car) Proxy.newProxyInstance(Audi.class.getClassLoader(), Audi.class.getInterfaces(), new CarJdkDynamicHandler(new Audi()));
        audiCar.run();

        Car bmwCar = (Car) Proxy.newProxyInstance(BMW.class.getClassLoader(), BMW.class.getInterfaces(), new CarJdkDynamicHandler(new BMW()));
        bmwCar.run();
    }

    /**
     * Cglib动态代理
     */
    @Test
    public void testCglibDynamicProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Animal.class);
        enhancer.setCallback(new AnimalCglibDynamicHandler());
        Animal animal = (Animal) enhancer.create();
        animal.hello();
    }

    public interface Car{
        void run();
    }

    public class Audi implements Car{

        public Audi(){

        }

        @Override
        public void run() {
            System.out.println("奥迪在飞奔");
        }

    }

    public class BMW implements Car{

        @Override
        public void run() {
            System.out.println("宝马在飞奔");
        }

    }

    public class AudiProxy implements Car{

        private Audi audi;

        public AudiProxy(Audi audi){
            this.audi = audi;
        }

        @Override
        public void run() {
            System.out.println("车启动...");
            audi.run();
            System.out.println("车停了...");
        }
    }

    public class CarJdkDynamicHandler implements InvocationHandler{

        private Object object;

        public CarJdkDynamicHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("车启动...");
            method.invoke(object,args);
            System.out.println("车停了...");
            return null;
        }
    }

    static class Animal{
        void hello(){
            System.out.println("我是动物");
        }
    }

    public class AnimalCglibDynamicHandler implements MethodInterceptor{
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("before...");
            Object res = methodProxy.invokeSuper(obj, args);
            System.out.println("after...");
            return res;
        }
    }

}
