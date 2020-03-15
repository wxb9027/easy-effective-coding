package easy.effective.coding.bingfa_biancheng.future.myfuture;

/**
 * Data是一个核心接口，这就是客户端希望获取的数据。
 * 在Future模式中，这个Data接口有两个重要的实现：FutureData(临时代理)和RealData(最终结果)。
 *
 * FutureData，可立即返回，作为RealData的临时代理。
 *             FutureData是Future模式的关键。它实际上是真实数据RealData的代理，封装了获取RealData的等待过程
 * RealData，需要一定时间才能构建成功。
 *
 */
public interface Data {
    String getResult();
}
