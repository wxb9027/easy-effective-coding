package function.programming.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: TODO
 * @Author TODO
 * @DATE 20/9/13 13:59
 * @Version 1.0
 **/
public class Lambda {
    public static void main(String[] args) {

    }
    /**
     * @Description
     * 传统方式实现排序功能
     **/
    @Test
    public void sortNoUseLambda(){
        String[] phnoeNum = {"18677777777","16622222222","18888888888"};
        Arrays.sort(phnoeNum, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (String s : phnoeNum) {
            System.out.println(s);
        }
        Arrays.stream(phnoeNum).forEach(x -> System.out.println(x));
        System.out.println(String.join(",",phnoeNum));
        System.out.println(phnoeNum);
    }

    @Test
    public void sortUseLambda(){
        String[] phnoeNum = {"18677777777","16622222222","18888888888"};
    }
    /**
     *lambda有一下作用：
     * 1. 作为对象引用（Method References），小巧且易读。
     *
     *
     *
     *
     *
     *
     *
     **/
}
