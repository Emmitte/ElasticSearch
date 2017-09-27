package elasticsearch.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

public class SearchIndex {
	
	/**
	 * 查询全部内容，实现分页查询
	 * */
	public List<String> searchAll(Client client, String index, String table) {
		
		SearchResponse response = client.prepareSearch().setIndices(index).setTypes(table).get();
		int n = (int) response.getHits().totalHits();
		int size = 100;
		List<String> retList = new ArrayList<String>();
		
		for(int i = 0;i < n;){
			response = client.prepareSearch().setIndices(index).setTypes(table).setScroll(TimeValue.timeValueMinutes(2)).setFrom(i).setSize(size).get();
			i += size;
			SearchHits searchHits = response.getHits();
			for (SearchHit hit : searchHits) {
		        retList.add(hit.getSourceAsString());
		    }
		}
		return retList;
	}
	
	/**
	 * 使用过滤器查询，实现分页查询
	 * */
	public List<String> queryByFilter(Client client, String index, String table) {
		
		// 查询groupname为"压力测试"的数据
	    QueryBuilder queryBuilder = QueryBuilders.matchQuery("GROUPNAME", "压力测试");
	    
		SearchResponse response = client.prepareSearch().setIndices(index).setTypes(table).setQuery(queryBuilder).get();
		int n = (int) response.getHits().totalHits();
		System.out.println(n);
		int size = 100;
		List<String> retList = new ArrayList<String>();
		
		response = client.prepareSearch().setIndices(index).setTypes(table).setScroll(TimeValue.timeValueMinutes(5)).setSize(n).setQuery(queryBuilder).get();
		SearchHits searchHits = response.getHits();
		for (SearchHit hit : searchHits) {
	        retList.add(hit.getSourceAsString());
	    }
		
		return retList;
	}

}
