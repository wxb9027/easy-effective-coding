package easy.effective.coding.java_basic.jvm;

/**
 * 打印GC信息，并分析
 */
public class TestGCPrint {
    public static void main(String[] args) {
        for (int i = 0; i < 5 ;i++){
            String[] arr = new String[10];
            for (int j = 0 ; j < arr.length ; j++){
                arr[j] = "aaaaaaaaaaa";
            }
            System.out.println(Runtime.getRuntime().availableProcessors());
            System.out.println(Runtime.getRuntime().freeMemory());
            System.out.println(Runtime.getRuntime().maxMemory());
            System.out.println(Runtime.getRuntime().totalMemory());

            System.out.println("---------------------------");
        }

    }
}
