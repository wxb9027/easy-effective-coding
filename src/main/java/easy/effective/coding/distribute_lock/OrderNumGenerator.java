package easy.effective.coding.distribute_lock;

import java.text.SimpleDateFormat;

//生成订单号 时间戳
public class OrderNumGenerator {
    //区分不同的订单号
    private static int count = 0;
    //单台服务器，多个线程 同时生成订单号
    public String getNumber(){
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simpt.format(new Date()) + "-" + ++count;  //时间戳后面加了 count

    }
}
