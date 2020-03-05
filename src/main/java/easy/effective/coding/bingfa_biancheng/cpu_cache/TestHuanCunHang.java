package easy.effective.coding.bingfa_biancheng.cpu_cache;
import org.apache.log4j.Logger;

/**
 * @author 韦海燕
 * 验证cpu缓存行对性能的影响
 * 1.cpu缓存行为64Bytes，一次最多可以缓存8个long；
 * 2.在java中，数据连续保持的，访问一个long型数组时，如果一个long被加载到缓存，另外的7个long也同时被加载；
 * 3.根据第二条，按行遍历数组，可以利用cpu缓存，按列遍历的话，下一列的数据不一定在缓存中，
 *   如此按行遍历的时间少于按列遍历的时间；
 */
public class TestHuanCunHang {
    private static final Logger LOG = Logger.getLogger(TestHuanCunHang.class);
    static  long sum = 0L;
    public static void main(String[] args) {
        long[][] arr = new long[1024 * 1024][8];
        TestHuanCunHang.performanceHX(arr);
    }

    private static void  performanceHX(long[][] arr){
        long start = System.currentTimeMillis();
        // 按行遍历
        for (int i = 0; i < 1024*1024; i++){
            for (int j = 0; j < 8; j++){
                sum += arr[i][j];
            }
        }
        LOG.info("按行 loop time:" + (System.currentTimeMillis() - start) );
        start = System.currentTimeMillis();
        // 按列遍历
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 1024*1024; j++){
                sum += arr[j][i];
            }
        }
        LOG.info("按列 loop time:" + (System.currentTimeMillis() - start) );
    }
}
