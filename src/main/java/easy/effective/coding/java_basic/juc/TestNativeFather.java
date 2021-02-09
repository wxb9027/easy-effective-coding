package easy.effective.coding.java_basic.juc;

/**
 * native关键字：
 * 用来修饰方法method；
 * 被native修改的方法，表示该方法是java代码调用非java代码的一个接口（Java Native Interface）；
 * 该方法的实现由非java代码实现，比如C/C++。
 *
 * 标识符native可以和所有其他java标志符连用，除了abstract。
 *
 * 注意：含有本地方法的类被继承，子类可以继承这个本地方法，并且可以用java代码重写这个方法；
 * 同样的当一个本地方法被final、static、private标识，该方法就不能被子类重写了。
 */
public abstract class TestNativeFather {

    /** 抽象方法，必须被子类实现*/
    public abstract void print(String str);

    /** 本地方法，可以被子类重写 */
    native public void native1(int x);
    native protected void native2(int[] arry) throws Exception;
    native synchronized float native3(Object o);

    /** private修饰的私有方法，不能被子类重写*/
    native private void native4();

    /** static修饰的静态方法，不能子类重写*/
    native public static long native5();

}
