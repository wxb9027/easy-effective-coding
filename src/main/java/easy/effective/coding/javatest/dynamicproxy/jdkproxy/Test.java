package easy.effective.coding.javatest.dynamicproxy.jdkproxy;

public class Test {
    public static void main(String[] args) {
        Student dog = new Student();
        AnimalInvocationHandler ani = new AnimalInvocationHandler();
        Person proxy = (Person) ani.bind(dog);
        proxy.study();
    }
}
