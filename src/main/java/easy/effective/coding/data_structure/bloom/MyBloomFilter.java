/*
package easy.effective.coding.data_structure.bloom;

import com.google.common.hash.HashFunction;
import sun.jvm.hotspot.utilities.BitMap;

import java.util.BitSet;

*/
/**
 * @Description: TODO
 * @Author TODO
 * @DATE 20/7/6 23:05
 * @Version 1.0
 **//*

public class MyBloomFilter {
    */
/**
     * 初始化过滤器  位数值
     * 定义hash函数
     * 基本功能：
     *  1.添加数据
     *  2.判断数据是否存在
     *
     **//*

    */
/***
     * 构建Hash函数
     *//*

    private static final int[] seed = new int[]{3,5,7,11};
    private static HashFunction[] hashFunctions = new HashFunction[seed.length];
    */
/**
     * 初始化bitMap
     *//*

    private static final BitSet bitSet = new BitSet(1024);
    
    */
/**
     * @Description 判断元素是否存在
     * @Date  20/7/6 23:26
     * @Param 
     * @return 
     **//*

    public static boolean contains(String ele){
        return true;
    }
    */
/**
     * @Description 添加元素
     * @Date  20/7/6 23:27
     * @Param 
     * @return 
     **//*

    public static void add(String ele){
        if (null == ele){
            System.out.println("ele is null");
            return;
        }
        for (HashFunction function : hashFunctions){
            //bitSet.set(function,true);
        }
    }
    class HashFunction {

        private int size;
        private int seed;

        public HashFunction(int size, int seed) {
            this.size = size;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            int r = (size - 1) & result;
            return (size - 1) & result;
        }
    }

}
*/
