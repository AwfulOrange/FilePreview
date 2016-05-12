package cn.edu.zucc.chenxg.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





public class JSONConstructer {

	/**
	 * Method to construct JSON
	 * 
	 * @param tag
	 * @param status
	 * @return
	 */
	public static String constructJSON(String tag, boolean status, Object c) {
		JSONObject obj = new JSONObject();
		String[] names = GetAndSetObjectAttribute.getFieldName(c);
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			for (String name : names) {

				obj.put(name, GetAndSetObjectAttribute.invokeGet(c, name));

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj.toString();
	}
	
	public static JSONObject constructJSON(Object c) {
		JSONObject obj = new JSONObject();
		String[] names = GetObjectAttribute.getFieldName(c);
		try {
			for (String name : names) {

				obj.put(name, GetObjectAttribute.invokeGet(c, name));

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj;
	}

	/**
	 * Method to construct JSON with Error Msg
	 * 
	 * @param tag
	 * @param status
	 * @param err_msg
	 * @return
	 */
	public static String constructErrorJSON(String tag, boolean status,
			String err_msg) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("error_msg", err_msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}

	public static JSONArray removeFirstJSONObject(JSONArray array1) {
		JSONArray array2 = new JSONArray();
		for (int i = 1; i < array1.length(); i++) {
			try {
				array2.put(array1.getJSONObject(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return array2;

	}

	public static <T> T JSON2Bean(JSONObject jsonObject, T t) {
		String[] names = GetAndSetObjectAttribute.getFieldName(t);

		try {
			for (String name : names) {
				GetAndSetObjectAttribute.invokeSet(t, name,
						jsonObject.get(name));

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;
	}

}
