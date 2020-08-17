package easy.effective.coding.javatest.dynamicproxy.cglibproxy;

import org.springframework.beans.factory.support.SimpleInstantiationStrategy;

public class HelloServiceImpl {
    public void sayHello(){
        System.out.println("Hello fxm");
    }

    public void sayBye(){
        System.out.println("Bye fxm");
    }

}
