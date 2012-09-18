package edu.stu.mobile.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ParseJson {
	public String getWebserviceJson(String url) {
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
}
