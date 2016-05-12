package cn.edu.zucc.chenxg.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GetAndSetObjectAttribute {
	/**
	 * 
	 * java����bean��set����
	 * 
	 * 
	 * 
	 * @param objectClass
	 * 
	 * @param fieldName
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public static Method getSetMethod(Class objectClass, String fieldName) {

		try {

			Class[] parameterTypes = new Class[1];

			Field field = objectClass.getDeclaredField(fieldName);

			parameterTypes[0] = field.getType();

			StringBuffer sb = new StringBuffer();

			sb.append("set");

			sb.append(fieldName.substring(0, 1).toUpperCase());

			sb.append(fieldName.substring(1));

			Method method = objectClass
					.getMethod(sb.toString(), parameterTypes);

			return method;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	/**
	 * 
	 * ִ��set����
	 * 
	 * 
	 * 
	 * @param oִ�ж���
	 * 
	 * @param fieldName����
	 * 
	 * @param valueֵ
	 */

	public static void invokeSet(Object o, String fieldName, Object value) {

		Method method = getSetMethod(o.getClass(), fieldName);

		try {

			method.invoke(o, new Object[] { value });

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

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
