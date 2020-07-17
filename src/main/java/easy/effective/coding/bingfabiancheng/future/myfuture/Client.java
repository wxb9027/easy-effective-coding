package easy.effective.coding.bingfabiancheng.future.myfuture;

/**
 * Client主要实现了获取FutureData，开启构造RealData的线程，
 * 并在接受请求后，很快返回FutureData。
 * 注意，它不会等待数据真的构造完毕再返回，而是立即返回FutureData，
 * 即使这个时候FutureData内并没有真实数据
 */
public class Client {
    public Data request(final String queryStr){
        final FutureData future = new FutureData();

        new Thread(() -> {
            RealData realData = new RealData(queryStr);
            future.setReadData(realData);
        }).start();

        return future;
    }
}
