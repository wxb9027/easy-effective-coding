package easy.effective.coding.javatest.dynamicproxy.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;

public class Client {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //继承被代理类
        enhancer.setSuperclass(HelloServiceImpl.class);
        //设置回调
        enhancer.setCallback(new HelloMethodInterceptor());
        //设置代理类对象
        HelloServiceImpl helloService = (HelloServiceImpl)enhancer.create();
        //在调用代理类中方法时 会被我们实现的方法拦截器拦截
        helloService.sayHello();
    }
}
