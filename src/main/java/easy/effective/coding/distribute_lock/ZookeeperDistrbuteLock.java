package easy.effective.coding.distribute_lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock {
    CountDownLatch countDownLatch = null;
    @Override
    boolean tryLock() {
        try {
            zkClient.createEphemeral(lockPath);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    void waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null){
                    countDownLatch.countDown();
                }
            }
        };
        // 监听事件通知
        zkClient.subscribeDataChanges(lockPath,iZkDataListener);
        if (zkClient.exists(lockPath)){
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            }catch (Exception e){
                // todo
            }
        }
        //后面代码继续执行
        //为了不影响程序的执行 建议删除该事件监听 监听完了就删除掉
        zkClient.unsubscribeDataChanges(lockPath,iZkDataListener);
    }
}
