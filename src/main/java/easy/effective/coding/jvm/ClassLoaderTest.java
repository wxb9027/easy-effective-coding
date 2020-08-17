package easy.effective.coding.jvm;

import sun.misc.Launcher;

import java.security.SecureClassLoader;

/**
 * 当前ClassLoader是AppClassLoader
 * 其父ClassLoader是ExtClassLoader
 * 其祖父ClassLoader是根装载器（C语言编写），但因在java中无法获取它的句柄，所以返回null
 *
 * 默认情况下，使用AppClassLoader装载应用程序的类。
 * 全盘负责委托机制
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("current loader:"+loader);
        System.out.println("parent loader:" + loader.getParent());
        System.out.println("grandparent loader:"+loader.getParent().getParent());

    }
}
