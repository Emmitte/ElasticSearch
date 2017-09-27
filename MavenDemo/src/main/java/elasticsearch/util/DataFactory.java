package elasticsearch.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class DataFactory {
	public static List<String> dataCreater(String path) throws Exception {
		
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String line=null;
		List<String> dataList = new ArrayList<String>();
		
		while((line = br.readLine()) != null){
			String[] data = line.split("\t");
			Map<String, String> map = new HashMap<String, String>();
			map.put("ID", data[0]);
			map.put("IP", data[1]);
			map.put("VALUE_DAY", data[2]);
			map.put("GROUPNAME", data[3]);
			map.put("TYPE_MACHINE", data[4]);
			map.put("DATE", data[5]);
			map.put("TYPE", data[6]);
			JSONObject jsonObject = JSONObject.fromObject(map);
			String str = jsonObject.toString();
			dataList.add(str);
		}
		
		br.close();
		fr.close();
		
		return dataList;
	}
}
