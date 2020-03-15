package easy.effective.coding.bingfa_biancheng.future.myfuture;

import java.util.concurrent.TimeUnit;

public class RealData implements Data {
    private String result ;

    public RealData(String para){
        // 构造RealData很慢
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i < 10; i++){
            sb.append(para);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
