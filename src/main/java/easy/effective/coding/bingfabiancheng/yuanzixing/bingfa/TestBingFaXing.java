package easy.effective.coding.bingfabiancheng.yuanzixing.bingfa;

/**
 * @author 韦海燕 2020年1月27日20:49:41
 * 验证多线程中的共享变量可见性
 * 1.testKJX存在于th1和th2两个线程的run方法中，作为共享对象，testKJX.count也是共享的；
 * 2.th1和th2读取count后放入各自cpu的缓存，之后在各自缓存中进行count++操作，
 *   导致最终结果一直小于20k
 */
public class TestBingFaXing {
    private long count = 0;
    private void add10K(){
        int idx = 0;
        while (idx++ < 10000){
            count++;
        }
    }
    public long getCount(){
        return count;
    }
    public static void main(String[] args) throws InterruptedException {
        final TestBingFaXing testKJX = new TestBingFaXing();
        Thread th1 = new Thread(() -> {
            testKJX.add10K();
        });
        Thread th2 = new Thread(() -> {
            testKJX.add10K();
        });
        th1.start();
        th2.start();

        th1.join();
        th2.join();
        System.out.printf("count: " + testKJX.getCount());
    }
}
