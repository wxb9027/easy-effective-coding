package easy.effective.coding.javatest.dynamicproxy.jdkproxy;

/**
 * @author fxm 20200523
 */
public class Student implements Person{
    @Override
    public void study() {
        System.out.println("我要学习");
    }
}
