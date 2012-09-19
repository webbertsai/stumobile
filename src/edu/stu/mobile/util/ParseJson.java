package edu.stu.mobile.util;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ParseJson {
	public static String getWebserviceJson(String url) {
		String jsonData = "";
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			HttpResponse response;
			response = client.execute(get);
			HttpEntity resEntity = response.getEntity();
			jsonData = EntityUtils.toString(resEntity);
		} catch (Exception e) {
			System.out.println(e);
		}

		return jsonData;

	}

	public static String parseJson(String jsonDataString, List<String> name) {

		StringBuffer data = new StringBuffer();
		try {
			JSONArray jsonArray = new JSONArray(jsonDataString);
			String tmp = "";
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject webserviceData = jsonArray.getJSONObject(i);
				for (int j = 0; j < name.size(); j++) {
					tmp += webserviceData.getString(name.get(j));
				}
				data.append(tmp + ";");
			}
			data.delete(data.length() - 1, data.length());
		} catch (Exception e) {
			System.out.println(e);
		}

		return data.toString();
	}
}
