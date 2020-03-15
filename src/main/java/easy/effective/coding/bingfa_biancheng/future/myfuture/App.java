package easy.effective.coding.bingfa_biancheng.future.myfuture;

import java.util.concurrent.TimeUnit;

/**
 * 场景应用
 */
public class App {

    public static void main(String[] args) {
        Client client = new Client();
        // 发送请求立刻返回future
        Data future = client.request("请求数据");
        System.out.println("请求完毕");

        try {
            System.out.println("开始其他业务。。。");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("结束其他业务。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取真实。。。");
        System.out.println("数据 = " + future.getResult());
    }
}
