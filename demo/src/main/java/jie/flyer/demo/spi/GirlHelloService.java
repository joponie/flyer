package jie.flyer.demo.spi;

public class GirlHelloService implements HelloService{

    @Override
    public void sayHello() {
        System.out.println("girl hello");
    }
}
