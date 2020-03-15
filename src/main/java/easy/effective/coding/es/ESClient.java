package easy.effective.coding.es;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public class ESClient {
    private  TransportClient client = null;

    public void init(){
        Settings setting = Settings.builder()
                .put("cluster.name", "elas_cluster")
                .put("client.transport.ignore_cluster_name", true)
//                    .put("client.transport.nodes_sampler_interval", 5)
                .put("client.transport.sniff", true)
                .build();
        client = new PreBuiltTransportClient(setting)
                .addTransportAddress(
                        new TransportAddress(new InetSocketAddress("192.168.124.132",9300))
                );
        if (client != null){
            System.out.println("client ok!");
        }else {
            System.out.println("client is null!");
        }
    }

    /**
     * 使用id查询
     */
    public void mgetById(){
        GetRequestBuilder getRequestBuilder = client.prepareGet("","","").setFetchSource("company",null);
    }

    /**
     * 使用id查询指定字段
     */
    public void mgetUseItem(){
        MultiGetRequest.Item item = new MultiGetRequest.Item("","","");
        //item.storedFields();
    }

}
