package elasticsearch.test;

import java.util.List;

import org.elasticsearch.search.SearchHits;

import elasticsearch.service.CreateIndex;
import elasticsearch.service.ElasticSearchBulk;
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
			String[] columnNameList = {"ID","IP","VALUE_DAY","GROUPNAME","TYPE_MACHINE","DATE","TYPE"};
			test.write(clientUtil.getClient(), "poc", "zabbixmetadata", "data/poc", columnNameList, "\t");
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
	
	/**
	 * 批量导出
	 * */
	public static void bulkOut(ClientUtil clientUtil) {
		ElasticSearchBulk bulk = new ElasticSearchBulk();
		SearchHits hits = bulk.bulkOut(clientUtil.getClient(), "t_test", "t_test");
		for(int i = 0;i < hits.getHits().length;i++){
			String jsonData = hits.getHits()[i].getSourceAsString();
			System.out.println(jsonData);
		}
	}
	
	/**
	 * 批量导入
	 * */
	public static void bulkIn(ClientUtil clientUtil) {
		ElasticSearchBulk bulk = new ElasticSearchBulk();
		String[] columnNameList = {"sex","name","id","age"};
		try {
			bulk.bulkIn(clientUtil.getClient(), "t_test", "t_test", "data/t_test",columnNameList,"\t");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		ClientUtil clientUtil = new ClientUtil();
		//createIndex(clientUtil);        // 1.创建索引文件
		//insertDataToIndex(clientUtil);  // 2.将元数据写入索引
		//searchIndex(clientUtil);        // 3.查询
		//bulkOut(clientUtil);            // 4.批量导出
		//bulkIn(clientUtil);             // 5.批量导入
	}

}
