package cn.edu.shiep.server;

public class ObjectFactory {

	public static Object createFactory(String className) {
		Object res = null;
		try {
			res = Class.forName(className).newInstance();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
			return null;
		} catch (InstantiationException ex) {
			ex.printStackTrace();
			return null;
		} catch (Exception e) {
			return null;
		}
		return res;
	}
}
