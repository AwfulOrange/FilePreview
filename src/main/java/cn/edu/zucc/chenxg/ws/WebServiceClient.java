package cn.edu.zucc.chenxg.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import cn.edu.zucc.chenxg.model.Filepreview;
import cn.edu.zucc.chenxg.utils.JSONConstructer;

public class WebServiceClient {
	public List<Filepreview> getUserDefaultFile() throws JSONException {
		Client c = Client.create();
		WebResource r = c.resource("http://localhost:8080/FilePreview/rest/Preview/User/1");

		String response = r.type(MediaType.TEXT_PLAIN).get(String.class);
		List<Filepreview> list = new ArrayList<Filepreview>();
		JSONArray obja = new JSONArray(response);
		if (obja.getJSONObject(0).getBoolean("result")) {
			JSONArray obja2 = JSONConstructer.removeFirstJSONObject(obja);
			list = new ArrayList<Filepreview>();
			for (int i = 0; i < obja2.length(); i++) {
				list.add(JSONConstructer.JSON2Bean(obja2.getJSONObject(i), new Filepreview()));

			}
			System.out.println(list.size());
		}

		return list;
		// new CruiseInfoHelper(context).insertItineraryInfo(list);

	}

	public List<Filepreview> Convert(int SID) throws JSONException {
		Client c = Client.create();
		WebResource r = c.resource("http://localhost:8080/FilePreview/rest/Preview/Convert/"+SID);

		String response = r.type(MediaType.TEXT_PLAIN).get(String.class);
		List<Filepreview> list = new ArrayList<Filepreview>();
		JSONArray obja = new JSONArray(response);
		if (obja.getJSONObject(0).getBoolean("result")) {
			JSONArray obja2 = JSONConstructer.removeFirstJSONObject(obja);
			list = new ArrayList<Filepreview>();
			for (int i = 0; i < obja2.length(); i++) {
				list.add(JSONConstructer.JSON2Bean(obja2.getJSONObject(i), new Filepreview()));

			}
			System.out.println(list.size());
		}

		return list;
		// new CruiseInfoHelper(context).insertItineraryInfo(list);

	}
}
