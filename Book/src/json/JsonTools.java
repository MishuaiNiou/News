package json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTools {

	public static List<String> parseJsonList(String jsonString){
		List<String> list=new ArrayList<String>();
		try {
			JSONObject jsonObject=new JSONObject(jsonString);
			JSONArray jsonArray=jsonObject.getJSONArray("Edata");
			for(int i=0;i<jsonArray.length();i++){
				list.add(jsonArray.getString(i));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public static List<Map<String,Object>> parseJsonMaps(String jsonString){
	      List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	      try {
			JSONObject jsonObject=new JSONObject(jsonString);
			JSONArray jsonArray=jsonObject.getJSONArray("books");
			for(int i=0;i<jsonArray.length();i++){
				JSONObject object=jsonArray.getJSONObject(i);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("name", object.getString("name"));
				map.put("introduce", object.getString("introduce"));
				map.put("img", object.getString("img"));
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	      return list;
	}

}
