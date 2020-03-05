package easy.effective.coding.bingfa_biancheng.youxuxing.zhiling_chongpai;

/**
 *  正常顺序：a = 1;x = b;b = 1;y = a;
 *  我们想要验证的顺序：x = b；y = a；a = 1;b = 1;
 *
 *  加入volatile可以防止指令重排
 *
 */
public class TestZhiLingChongPai {
    /*volatile*/ int a = 0, b = 0;
    /*volatile*/ int x = 0, y = 0;

    int index = 0;
    public  TestZhiLingChongPai(int index){
        this.index = index;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<100_00_00;i++){
            new TestZhiLingChongPai(i).runTest();
        }
    }

    public void runTest() throws InterruptedException {
        Thread tA = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 1;
                x = b;
            }
        });
        Thread tB = new Thread(new Runnable() {
            @Override
            public void run() {
                b = 1;
                y = a;
            }
        });
        tA.start();
        tB.start();
        tA.join();
        tB.join();
        if(x==0 && y==0){
            System.out.println(index+" = "+x+" "+y);
        }
    }
}
