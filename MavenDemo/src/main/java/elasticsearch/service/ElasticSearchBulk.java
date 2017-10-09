package elasticsearch.service;

import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

import elasticsearch.util.DataFactory;

public class ElasticSearchBulk {
	/**
	 * 批量导出
	 * */
	public SearchHits bulkOut(Client client, String index, String type) {
		
		SearchResponse response = client.prepareSearch(index)
		        .setTypes(type).setQuery(QueryBuilders.matchAllQuery())
		        .execute().actionGet();
		SearchHits resultHits = response.getHits();
		
		return resultHits;
	}
	
	/**
	 * 批量导入
	 * @throws Exception 
	 * */
	public void bulkIn(Client client, String index, String type, String filePath, String[] columnNameList, String token) throws Exception {
		
		List<String> dataList = DataFactory.dataCreater(filePath,columnNameList,token);
		
		BulkRequestBuilder bulkRequest=client.prepareBulk();
		
		for(String data : dataList){
			bulkRequest.add(client.prepareIndex(index,type).setSource(data));
	    }
		bulkRequest.execute().actionGet();
	}
}
