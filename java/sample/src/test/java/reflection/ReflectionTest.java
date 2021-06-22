package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射
 *
 * @author duchao
 */
public class ReflectionTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Chinese chinese = new Chinese();
        Class cls = chinese.getClass();
        System.out.println(cls);
        System.out.println(cls.getName());
        System.out.println(Arrays.asList(cls.getDeclaredMethods()));
        System.out.println(Arrays.asList(cls.getDeclaredFields()));
        Chinese chi = (Chinese)Class.forName("reflection.ReflectionTest$Chinese").newInstance();
        chi.setName("语文书");
        chi.hello();
        Method setName = cls.getDeclaredMethod("setName", String.class);
        setName.invoke(chinese,"我是语文书");
        Method hello = cls.getDeclaredMethod("hello");
        hello.invoke(chinese);
    }

    static class Chinese implements Book {

        String name;

        @Override
        public void hello() {
            System.out.println(this.name);
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    interface Book{
        String desc = "这是书";

        void hello();
    }
}
