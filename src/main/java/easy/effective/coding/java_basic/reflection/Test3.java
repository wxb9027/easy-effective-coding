package easy.effective.coding.java_basic.reflection;

public class Test3 {

    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.m);
    }
}

class A {

    static {
        System.out.println("静态代码块初始化");
        m = 300;
    }

    static int m = 100;

    public A() {
        System.out.println("无参构造方法初始化");
    }

}
