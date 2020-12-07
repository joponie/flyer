package jie.flyer.demo.conf;

import jie.flyer.demo.conf.handle.UserHandler;

/**
 * @Author kain
 * @Date 2020/12/7
 **/
public class Demo {

    public static void main(String[] args) {
        HandlerProxyFactory<UserHandler> proxyFactory = new HandlerProxyFactory<>(UserHandler.class);
        UserHandler userHandler = proxyFactory.newInstance();
        userHandler.sayHello();
    }

}
