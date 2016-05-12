package cn.edu.zucc.chenxg.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GetObjectAttribute {
	

	/**
	 * java����bean��get����
	 * 
	 * @param objectClass
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Method getGetMethod(Class objectClass, String fieldName) {
		StringBuffer sb = new StringBuffer();
		sb.append("get");
		sb.append(fieldName.substring(0, 1).toUpperCase());
		sb.append(fieldName.substring(1));
		try {
			return objectClass.getMethod(sb.toString());
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * ִ��get����
	 * 
	 * @param o
	 *            ִ�ж���
	 * @param fieldName
	 *            ����
	 */
	public static Object invokeGet(Object o, String fieldName) {
		Method method = getGetMethod(o.getClass(), fieldName);
		try {
			return method.invoke(o, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String[] getFieldName(Object o) {
		try {
			Field[] fields = o.getClass().getDeclaredFields();
			String[] fieldNames = new String[fields.length];
			for (int i = 0; i < fields.length; i++) {
				fieldNames[i] = fields[i].getName();
				System.out.println(fields[i].getName());
			}
			return fieldNames;
		} catch (SecurityException e) {
			e.printStackTrace();
			// System.out.println(e.toString());
		}
		return null;
	}

}
