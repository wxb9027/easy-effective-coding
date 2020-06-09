package easy.effective.coding.collection.map;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestHashMap {
    @Test
    public void testKeySetOrder(){
        Map<Long,String> map = new HashMap<>();
        for (int i = 0; i < 10; i++){
            map.put(i+1L,"one");
        }
        for (int i = 20;i>0;i--){
            map.put(i + 1L,"one");
        }
        map.remove(9L);
        map.remove(10L);

        Set<Long> keyset =  map.keySet();
        for (Long key : keyset){
            System.out.println(key);
        }

    }
}
