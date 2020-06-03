package demo.concurrent.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author kain
 * @since 2020-04-02
 */
public class CglibProxyDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealService.class);
        enhancer.setCallback(new ServiceInterceptor());
        RealService o = (RealService) enhancer.create();
        o.hello();
        o.hello2();
    }

    static class RealService {
        public void hello() {
            System.out.println("hello");
        }

        public final void hello2() {
            System.out.println("hello2");
        }
//        private RealService(){}
    }

    static class ServiceInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before hello");
            Object invoke = methodProxy.invokeSuper(o, objects);
            System.out.println("after hello");
            return invoke;
        }
    }
}
