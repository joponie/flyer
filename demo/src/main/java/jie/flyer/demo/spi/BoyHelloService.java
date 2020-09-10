package jie.flyer.demo.spi;

public class BoyHelloService implements HelloService{

    @Override
    public void sayHello() {
        System.out.println("boy hello");
    }
}
