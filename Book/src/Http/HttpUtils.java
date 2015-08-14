package Http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	public static String sendPostMethod(String path,String encoding){
		String result="";
		HttpClient httpClient=new DefaultHttpClient();
		try {
			HttpPost httpPost=new HttpPost(path);
			HttpResponse httpResponse=httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode()==200){
			     result=EntityUtils.toString(httpResponse.getEntity(), encoding);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}
	
	public static byte[] sendPostMethod1(String path,String encoding){
		byte[] data=null;
		HttpClient client=new DefaultHttpClient();
		try {
			HttpPost post=new HttpPost(path);
			HttpResponse response=client.execute(post);
			if(response.getStatusLine().getStatusCode()==200){
				data=EntityUtils.toByteArray(response.getEntity());
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			client.getConnectionManager().shutdown();
		}
		return data;
		
	}

}
