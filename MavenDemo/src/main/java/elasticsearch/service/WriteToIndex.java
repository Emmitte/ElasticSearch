package elasticsearch.service;

import java.util.List;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import elasticsearch.util.DataFactory;

public class WriteToIndex {
	
	/**
	 * 数据写入索引
	 * @param client
	 * @param index
	 * @return
	 * @throws Exception 
	 */
	public void write(Client client, String index, String tablename, String filePath) throws Exception{
		
		//创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
        IndexRequestBuilder requestBuilder = client.prepareIndex(index, tablename).setRefresh(true);
		List<String> dataList = DataFactory.dataCreater(filePath);
	    for(String data : dataList){
	    	//System.out.println(data);
	    	requestBuilder.setSource(data).execute().actionGet();
	    }
	}
}
