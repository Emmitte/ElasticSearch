package elasticsearch.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class DataFactory {
	public static List<String> dataCreater(String path, String[] columnNameList, String token) throws Exception {
		
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String line=null;
		List<String> dataList = new ArrayList<String>();
		
		while((line = br.readLine()) != null){
			String[] data = line.split(token);
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i = 0;i < columnNameList.length;i++){
				map.put(columnNameList[i], data[i]);
			}
			JSONObject jsonObject = JSONObject.fromObject(map);
			String str = jsonObject.toString();
			dataList.add(str);
		}
		
		br.close();
		fr.close();
		
		return dataList;
	}
}
