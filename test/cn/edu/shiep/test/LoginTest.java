package cn.edu.shiep.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

import cn.edu.shiep.util.JsonUtil;

public class LoginTest {

	@Test
	public void test() throws Exception{
		JSONObject head = new JSONObject();
		head.put("method", "todayClass");
		JSONObject data = new JSONObject();
		data.put("userId", "c46f9a40-a5f3-4b18-9416-75072f11d06a");
		data.put("dayOfWeek", "5");
		JSONObject body = new JSONObject();
		body.put("data", data);
		
		JSONObject result = new JSONObject();
		result.put("head", head);
		result.put("body", body);
		System.out.println(result.toString());
		
		JSONObject flag = sendPostRequest("http://127.0.0.1:8088/shiep/MainServlet", result, "utf-8");
		
		//System.out.println(flag.getString("body"));
		System.out.println(flag.toString());

		
		
		
	}
	
	
	public static JSONObject sendPostRequest(String path, JSONObject data, String enc) throws Exception{
		JSONObject result = new JSONObject();
		result.put("head", "0");
		result.put("body", "");
		byte[] entitydata = data.toString().getBytes();//得到实体的二进制数据
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);//如果通过post提交数据，必须设置允许对外输出数据
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entitydata.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		outStream.flush();
		outStream.close();
		InputStream in = null;
		int length = -1;
		byte[] buffer = new byte[1024];
		StringBuffer sb = new StringBuffer();
		if(conn.getResponseCode()==200){
			in = conn.getInputStream();
			while((length=in.read(buffer))!=-1){
				sb.append(new String(buffer,0,length));
			}
			System.out.println("222"+sb.toString());
			String sbstring =  URLDecoder.decode(sb.toString(), "UTF-8");
			result = JSONObject.fromObject(sbstring);			
			//System.out.println(result.get("head"));
			return result;
		}
		return result;
	}
	
	public static boolean sendGetRequest(String path, Map<String, String> params, String enc) throws Exception{
		StringBuilder sb = new StringBuilder(path);
		sb.append('?');
		// ?method=save&title=435435435&timelength=89&
		for(Map.Entry<String, String> entry : params.entrySet()){
			sb.append(entry.getKey()).append('=')
				.append(URLEncoder.encode(entry.getValue(), enc)).append('&');
		}
		sb.deleteCharAt(sb.length()-1);
		
		URL url = new URL(sb.toString());
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		if(conn.getResponseCode()==200){
			return true;
		}
		return false;
	}
	
	public static boolean sendPostRequest22(String path, Map<String, String> params, String enc) throws Exception{
		// title=dsfdsf&timelength=23&method=save
		StringBuilder sb = new StringBuilder();
		if(params!=null && !params.isEmpty()){
			for(Map.Entry<String, String> entry : params.entrySet()){
				sb.append(entry.getKey()).append('=')
					.append(URLEncoder.encode(entry.getValue(), enc)).append('&');
			}
			sb.deleteCharAt(sb.length()-1);
		}
		System.out.println("sb==="+sb.toString());
		byte[] entitydata = sb.toString().getBytes();//得到实体的二进制数据
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);//如果通过post提交数据，必须设置允许对外输出数据
		//Content-Type: application/x-www-form-urlencoded
		//Content-Length: 38
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entitydata.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		outStream.flush();
		outStream.close();
		if(conn.getResponseCode()==200){
			return true;
		}
		return false;
	}
}
