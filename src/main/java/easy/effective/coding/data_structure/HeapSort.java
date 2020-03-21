package easy.effective.coding.data_structure;

import java.util.Arrays;

/**
 * 堆排序算法思路：
 * 1、把无序数组构建成二叉堆；
 * 2、循环移除堆顶元素，堆自调整；
 * ＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
 * ＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
 * 堆是一个完全二叉树
 * 堆的每一个父节点数值都大于（或小于）其子子节点，堆的每个左子树和右子树也是一个堆。
 * 堆的存储一般用数组实现
 * 假设父节点的数组下标为i，那么其左右节点的下标分别为：（2*i+1）和（2*i＋2）
 * 如果孩子节点的下标是j，则其父节点的下标为（j－1）／2
 * 完全二叉树中，假设有n个元素，那么在堆中最后一个父节点的位置为（n／2-1）
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = new int[]{1,3,2,6,5,7,8,9,10,0};
        //1、构建一个最大堆 O(n)
        buildHeap(array);
        System.out.println(Arrays.toString(array));

        //2、循环移除堆顶元素，移到集合尾部，调整堆产生新的堆顶 O(nlogn)
        heapSort(array);
        System.out.println(Arrays.toString(array));

    }

    /**
     * 构建堆 - 时间复杂度：O(n) 注意不是 O(nlogn)
     */
    public static void buildHeap(int[] array){
        //从最后一个非叶子节点开始，依次做"下沉"调整
        for (int i = array.length/2-1;i>=0;i--){
            downAdjust(array,i,array.length);
        }
    }

    /**
     * "下沉"调整 (构建最大堆) - 时间复杂度：O(logn)
     */
    public static void downAdjust(int[] array, int parentIndex, int length){
        //temp保存父节点值，用于最后赋值
        int temp = array[parentIndex];
        int childIndex = 2*parentIndex+1;
        while (childIndex<length){
            //如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if(childIndex+1<length&&array[childIndex+1]>array[childIndex]){
                childIndex++;
            }
            //如果父节点大于任何一个孩子节点的值，则直接跳出
            if(temp>=array[childIndex])
                break;
            //无需真正交换，单向赋值即可
            array[parentIndex]=array[childIndex];
            parentIndex=childIndex;
            childIndex = 2*childIndex+1;
        }
        array[parentIndex]=temp;
    }

    /**
     * 堆排序（升序）
     */
    public static void heapSort(int[] array){
        for(int i = array.length-1;i>0;i--){
            //最后一个元素和第一个元素交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            //下沉调整最大堆
            downAdjust(array,0,i);
        }
    }

}
