package easy.effective.coding.jvm;

/**
 * 打印GC信息，并分析
 */
public class TestGCPrint {
    public static void main(String[] args) {
        for (int i = 0; i < 100000000 ;i++){
            String[] arr = new String[100];
            for (int j = 0 ; j < arr.length ; j++){
                arr[j] = "aaaaaaaaaaa";
            }
        }

    }
}
