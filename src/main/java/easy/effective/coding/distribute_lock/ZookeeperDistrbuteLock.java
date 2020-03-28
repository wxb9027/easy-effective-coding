package easy.effective.coding.distribute_lock;

public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock {

    @Override
    boolean tryLock() {
        zkClient.createEphemeral(lockPath);
        return true;
    }

    @Override
    void waitLock() {

    }
}
