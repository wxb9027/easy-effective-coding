package easy.effective.coding.data_structure.bloom;
/**
 * @Description
 * 判断一个元素k是否存在集合A之中：1.肯不存在；2.可能存在
 * 1.多k做多次hash，每次的hash值对A集合长度取余；
 * 2.余为位数组的下标，对应值为1；
 * 当某一元素做同样次数的hash，求余，位数组对应下标的值均为1则可能存在，否则肯定不存在
 *
 * 保证最佳误判率：
 * 位数组长度 ＝（哈西次数＊A集合元素个数）／ln2
 * @Author
 * @Date  20/7/6 20:34
 * @Param
 * @return
 **/
public class BloomFilter {
  /**哈西次数**/
  private int k;
  /**每个元素占用位数组的个数**/
  private int bitsPerKey;
  /**位数组长度**/
  private int bitLen;
  private byte[] result;

  public BloomFilter(int k, int bitsPerKey) {
    this.k = k;
    this.bitsPerKey = bitsPerKey;
  }

  public byte[] generate(byte[][] keys) {
    assert keys != null;
    bitLen = keys.length * bitsPerKey;
    // align the bitLen.
    bitLen = ((bitLen + 7) / 8) << 3;
    bitLen = bitLen < 64 ? 64 : bitLen;
    result = new byte[bitLen >> 3];
    for (int i = 0; i < keys.length; i++) {
      assert keys[i] != null;
      int h = Bytes.hash(keys[i]);
      for (int t = 0; t < k; t++) {
        int idx = (h % bitLen + bitLen) % bitLen;
        result[idx / 8] |= (1 << (idx % 8));
        int delta = (h >> 17) | (h << 15);
        h += delta;
      }
    }
    return result;
  }

  public boolean contains(byte[] key) {
    assert result != null;
    int h = Bytes.hash(key);
    for (int t = 0; t < k; t++) {
      int idx = (h % bitLen + bitLen) % bitLen;
      if ((result[idx / 8] & (1 << (idx % 8))) == 0) {
        return false;
      }
      int delta = (h >> 17) | (h << 15);
      h += delta;
    }
    return true;
  }

  

}
