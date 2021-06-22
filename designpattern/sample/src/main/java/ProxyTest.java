import org.junit.Test;

/**
 * 代理模式：静态代理、动态代理
 * 静态代理：
 *
 * @author duchao
 */
public class ProxyTest {

    /**
     * 静态代理：需要为每个具体实现类实现一个代理类
     */
    @Test
    public void testStaticProxy(){
        CarProxy carProxy = new CarProxy(new Audi());
        carProxy.run();
    }

    public interface Car{
        void run();
    }

    public class Audi implements Car{

        @Override
        public void run() {
            System.out.println("奥迪在飞奔");
        }
    }

    public class CarProxy implements Car{

        private Car car;

        public CarProxy(Car car){
            this.car = car;
        }

        @Override
        public void run() {
            System.out.println("车代理执行前...");
            car.run();
            System.out.println("车代理执行后...");
        }
    }

}
