package easy.effective.coding.java_basic.design_pattern.proxy_pattern.dynamic_proxy.jdkproxy;

public class Test {
    public static void main(String[] args) {
        Student dog = new Student();
        AnimalInvocationHandler ani = new AnimalInvocationHandler();
        Person proxy = (Person) ani.bind(dog);
        proxy.study();
    }
}
