package easy.effective.coding.java_basic.annotation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 简单的测试框架
 * 当主方法执行时，回自动执行被检测的所有方法（加了Check注解的方法），判单是否有异常，记录到文件中
 *
 * 小结：
 * 注解不是程序的一部分，可理解为注解就是一个标签.
 * 注解给谁用？
 *  1、编译器
 *  2、解析程序
 *
 */
public class TestCheck {

    public static void main(String[] args) throws IOException {

        //1.创建计算器对象
        Calculator c = new Calculator();
        //2.获取字节码文件对象
        Class<? extends Calculator> cls = c.getClass();
        //3.获取所有方法
        Method[] methods = cls.getMethods();

        //被检测的方法个数
        int total = 0;
        //出现异常的次数
        int num = 0;

        BufferedWriter bw = new BufferedWriter(new FileWriter("bug.txt"));

        for (Method method : methods) {
            //4.判断方法上有Check方法
            if (method.isAnnotationPresent(Check.class)) {
                //5.有，执行
                total++;
                try {
                    method.invoke(c);
                } catch (Exception e) {
                    //6.捕获异常,记录到文件中
                    num++;
                    bw.write(method.getName() + "方法出错了");
                    bw.newLine();
                    bw.write("异常名称：" + e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常原因：" + e.getCause().getMessage());
                    bw.newLine();
                    bw.write("------------------");
                    bw.newLine();
                }
            }
        }
        bw.write("本次测试一共检测" + total + "个方法");
        bw.newLine();
        bw.write("本次测试一共出现" + num + "次异常");
        bw.flush();
        bw.close();
    }
}
