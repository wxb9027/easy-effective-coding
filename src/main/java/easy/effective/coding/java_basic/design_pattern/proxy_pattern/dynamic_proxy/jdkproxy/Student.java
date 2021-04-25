package easy.effective.coding.java_basic.design_pattern.proxy_pattern.dynamic_proxy.jdkproxy;

/**
 * @author fxm 20200523
 */
public class Student implements Person{
    @Override
    public void study() {
        System.out.println("我要学习");
    }
}
