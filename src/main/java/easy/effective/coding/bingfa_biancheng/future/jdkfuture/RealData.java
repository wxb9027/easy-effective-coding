package easy.effective.coding.bingfa_biancheng.future.jdkfuture;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Callable接口只有一个方法call()，它会返回需要构造的实际数据。
 * 这个Callable接口也是Future框架和应用程序之间的重要接口。
 * 要实现自己的业务系统，通常需要实现自己的Callable对象。
 */
public class RealData implements Callable {
    private String para;

    public RealData(String para){
        this.para = para;
    }
    @Override
    public String call() throws Exception {
         StringBuffer sb = new StringBuffer();
         for (int i = 0;i < 5;i++){
             sb.append(para);
             TimeUnit.SECONDS.sleep(1);
         }
        return sb.toString();
    }
}
