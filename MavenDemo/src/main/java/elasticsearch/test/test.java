package elasticsearch.test;

import java.util.List;

import org.elasticsearch.client.Client;

import elasticsearch.service.CreateIndex;
import elasticsearch.service.SearchIndex;
import elasticsearch.service.WriteToIndex;
import elasticsearch.util.ClientUtil;

public class test {
	
	/**
	 * 创建索引，主要创建mapper
	 * */
	public static void createIndex(ClientUtil clientUtil) {
		CreateIndex test = new CreateIndex();
		test.createIndex(clientUtil.getClient(), "poc");
	}
	
	/**
	 * 将元数据写入索引文件
	 * */
	public static void insertDataToIndex(ClientUtil clientUtil) {
		WriteToIndex test = new WriteToIndex();
		try {
			test.write(clientUtil.getClient(), "poc", "zabbixmetadata", "data/poc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询
	 * */
	public static void searchIndex(ClientUtil clientUtil) {
		SearchIndex search = new SearchIndex();
		//List<String> ret = search.searchAll(clientUtil.getClient(), "poc", "zabbixmetadata");
		List<String> ret = search.queryByFilter(clientUtil.getClient(), "poc", "zabbixmetadata");
		System.out.println(ret.size());
		System.out.println(ret);
	}

	public static void main(String[] args) throws Exception {
		ClientUtil clientUtil = new ClientUtil();
		//createIndex(clientUtil);        // 1.创建索引文件
		//insertDataToIndex(clientUtil);  // 2.将元数据写入索引
		//searchIndex(clientUtil);        // 3.查询
	}

}
