package elasticsearch.service;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public class CreateIndex {
	
	/**
	 * 创建空索引  默认setting 无mapping
	 * @param client
	 * @param index
	 * @return
	 */
	public boolean createSimpleIndex(Client client, String index){
	    IndicesAdminClient indicesAdminClient = client.admin().indices();
	    CreateIndexResponse response = indicesAdminClient.prepareCreate(index).get();
	    return response.isAcknowledged();
	}
	
	/**
	 * 创建索引 指定setting,创建mapper
	 * @param client
	 * @param index
	 * @return
	 */
	public boolean createIndex(Client client, String index){
	    // settings
	    Settings settings = Settings.builder()
	    		.put("index.number_of_shards", 1)
	    		.put("index.number_of_replicas", 0)
	    		//.put("cluster.name", "poc")
	    		//.put("node.name", "node1")
	    		//.put("client.transport.ignore_cluster_name", true)
	    		//.put("node.client", true)
	    		//.put("client.transport.sniff", true)
	    		.build();
	    // mapping
	    XContentBuilder mappingBuilder;
	    try {
	        mappingBuilder = XContentFactory.jsonBuilder()
	                .startObject()
	                    .startObject(index)
	                        .startObject("properties")
	                            .startObject("ID").field("type", "string").field("store", "yes").endObject()
	                            .startObject("IP").field("type", "string").field("store", "yes").endObject()
	                            .startObject("VALUE_DAY").field("type", "string").field("store", "yes").endObject()
	                            .startObject("GROUPNAME").field("type", "string").field("store", "yes").endObject()
	                            .startObject("TYPE_MACHINE").field("type", "string").field("store", "yes").endObject()
	                            .startObject("DATE").field("type", "string").field("store", "yes").endObject()
	                            .startObject("TYPE").field("type", "string").field("store", "yes").endObject()
	                        .endObject()
	                    .endObject()
	                .endObject();
	    } catch (Exception e) {
	        System.out.println("--------- createIndex 创建 mapping 失败：" + e);
	        return false;
	    }
	    IndicesAdminClient indicesAdminClient = client.admin().indices();
	    CreateIndexResponse response = indicesAdminClient.prepareCreate(index)
	            .setSettings(settings)
	            .addMapping(index, mappingBuilder)
	            .get();
	    return response.isAcknowledged();
	}
}
