package demo.concurrent.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author kain
 * @since 2020-04-02
 */
public class JdkProxyDemo {

    public static void main(String[] args) {
        IService service = new RealService();
        IService proxyService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[]{IService.class}, new RealServiceProxyHandle(service));
        proxyService.hello();
    }

    interface IService {
        void hello();
    }

    static class RealService implements IService {
        @Override
        public void hello() {
            System.out.println("hello");
        }
    }

    static class RealServiceProxyHandle implements InvocationHandler {

        private IService service;

        RealServiceProxyHandle(IService service) {
            this.service = service;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before hello");
            Object invoke = method.invoke(service, args);
            System.out.println("after hello");
            return invoke;
        }
    }

}
