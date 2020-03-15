package easy.effective.coding.bingfa_biancheng.future.myfuture;

/**
 * FutureData是真实数据RealData的代理，封装了获取RealData的等待过程。
 * 当client请求数据时，先返回FutureData
 * FutureData是Future模式的关键。
 */
public class FutureData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;

    // 以代理的方式返回真实的数据
    @Override
    public synchronized String getResult() {
        while (!isReady){
            try {
                wait();//等待结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }

    public synchronized void setReadData(RealData realData){
        if (isReady){
            return ;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();//通知wait的线程
    }
}
