package cn.edu.shiep.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

@SuppressWarnings("unchecked")
public class JsonUtil {

	private static Logger log = Logger.getLogger(JsonUtil.class);

	/**
	 * 制作JSON--PageBean方法
	 * 
	 * @param list
	 *            结果集
	 * @param recordCount
	 *            总记录数
	 * @param pageNum
	 *            当前页
	 * @param pageSize
	 *            每页行数
	 * @return
	 */
	public static JSONObject getPageBean(List<Map> list, long recordCount, int pageNum, int pageSize) {
		long pageCount = 0;
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ja.add(getMap(list.get(i)));
			}
			if (recordCount % pageSize == 0)
				pageCount = recordCount / pageSize;
			else
				pageCount = recordCount / pageSize + 1;
		}
		jo.put("resultList", ja);
		jo.put("recordCount", recordCount);
		jo.put("pageNum", pageNum);
		jo.put("pageCount", pageCount);
		return jo;
	}
	
	public static List getList(JSONArray ja) {
		List list = new ArrayList();
		if (ja != null)
			for (int i = 0; i < ja.size(); i++) {
				list.add(getMap(ja.getJSONObject(i)));
			}
		return list;
	}

	public static Map getMap(JSONObject jo) {
		Map map = new HashMap();
		if (jo != null)
			for (Object o : jo.keySet()) {
				Object obj = map.get(o);
				if (obj == null)
					obj = "";
				else
					obj = obj + "";
				map.put(o, obj);
			}
		return map;
	}

	public static JSONArray getList(List<Map> list) {
		JSONArray ja = new JSONArray();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ja.add(getMap(list.get(i)));
			}
		}
		return ja;
	}

	/**
	 * response统一输出管理
	 * 
	 * @param o
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void responsePrint(Object o, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String str ="";
		if(o!=null){
			str= o.toString();
		}
		log.info("[" + new Date() + "]----Response.PrintWriter:" + str);
		pw.write(str);
		pw.flush();
	}

	/**
	 * JSON----封装MAP
	 * 
	 * @param map
	 *            结果集
	 * @return
	 */
	public static JSONObject getMap(Map map) {
		JSONObject jo = new JSONObject();
		if (map != null)
			for (Object o : map.keySet()) {
				Object obj = map.get(o);
				if (obj == null)
					obj = "";
				else
					obj = obj + "";
				jo.put(o, obj);
			}
		return jo;
	}

	/**
	 * 获取参数共用接口
	 * 
	 * @param request
	 * @param paramName
	 * @param defaultValue
	 * @return
	 * @throws Exception 
	 */
	public static String getParameter(HttpServletRequest request,
			String paramName, String defaultValue) throws Exception {
		String params=request.getParameter(paramName);
		return  (params!= null) ? params=new String(params.getBytes("iso-8859-1"),"gbk"): defaultValue;
	}
	
	
	
	/************************liu zheng yang***********************/
    /**
     * @param obj 任意对象
     * @return String
     */
    public static String object2json(Object obj) {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        } else if (obj instanceof String || obj instanceof Integer || obj instanceof Float || obj instanceof Boolean
                || obj instanceof Short || obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal
                || obj instanceof BigInteger || obj instanceof Byte) {
            json.append("\"").append(string2json(obj.toString())).append("\"");
        } else if (obj instanceof Object[]) {
            json.append(array2json((Object[]) obj));
        } else if (obj instanceof List) {
            json.append(list2json((List<?>) obj));
        } else if (obj instanceof Map) {
            json.append(map2json((Map<?, ?>) obj));
        } else if (obj instanceof Set) {
            json.append(set2json((Set<?>) obj));
        } else {
            json.append(bean2json(obj));
        }
        return json.toString();
    }
    /**
     * @param bean bean对象
     * @return String
     */
    public static String bean2json(Object bean) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        PropertyDescriptor[] props = null;
        try {
           if(null!=bean){
        	   props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
           }
        } catch (IntrospectionException e) {
        }
        if (props != null) {
            for (int i = 0; i < props.length; i++) {
                try {
                    String name = object2json(props[i].getName());
                    String value = object2json(props[i].getReadMethod().invoke(bean));
                    json.append(name);
                    json.append(":");
                    json.append(value);
                    json.append(",");
                } catch (Exception e) {
                	e.printStackTrace();
                }
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }
    /**
     * @param list list对象
     * @return String
     */
    public static String list2json(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    /**
     * @param array 对象数组
     * @return String
     */
    public static String array2json(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object obj : array) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    /**
     * @param map map对象
     * @return String
     */
    public static String map2json(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            for (Object key : map.keySet()) {
                json.append(object2json(key));
                json.append(":");
                json.append(object2json(map.get(key)));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }
    /**
     * @param set 集合对象
     * @return String
     */
    public static String set2json(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (Object obj : set) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    /**
     * @param s 参数
     * @return String
     */
    public static String string2json(String s) {
        if (s == null)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
            case '"':
                sb.append("\\\"");
                break;
            case '\\':
                sb.append("\\\\");
                break;
            case '\b':
                sb.append("\\b");
                break;
            case '\f':
                sb.append("\\f");
                break;
            case '\n':
                sb.append("\\n");
                break;
            case '\r':
                sb.append("\\r");
                break;
            case '\t':
                sb.append("\\t");
                break;
//            case '/':
//                sb.append("\\/");
//                break;
            default:
                if (ch >= '\u0000' && ch <= '\u001F') {
                    String ss = Integer.toHexString(ch);
                    sb.append("\\u");
                    for (int k = 0; k < 4 - ss.length(); k++) {
                        sb.append('0');
                    }
                    sb.append(ss.toUpperCase());
                } else {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }
    
    /**  
     * 把一个json数组串转换成实体数组  
     * @param jsonArrStr e.g. [{'name':'get'},{'name':'set'}]  
     * @param clazz e.g. Person.class  
     * @return Object[]  
     */  
     public static Object[] getDtoArrFromJsonArrStr(String jsonArrStr, Class clazz) {   
         JSONArray jsonArr = JSONArray.fromObject(jsonArrStr);   
         Object[] objArr = new Object[jsonArr.size()];   
         for (int i = 0; i < jsonArr.size(); i++) {   
             objArr[i] = JSONObject.toBean(jsonArr.getJSONObject(i), clazz);   
         }   
         return objArr;   
     }   
     
     public static Map<String, Object> parseJSON2Map(String jsonStr){
         Map<String, Object> map = new HashMap<String, Object>();
         //最外层解析
         JSONObject json = JSONObject.fromObject(jsonStr);
         for(Object k : json.keySet()){
             Object v = json.get(k); 
             //如果内层还是数组的话，继续解析
             if(v instanceof JSONArray){
                 List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                 Iterator<JSONObject> it = ((JSONArray)v).iterator();
                 while(it.hasNext()){
                     JSONObject json2 = it.next();
                     list.add(parseJSON2Map(json2.toString()));
                 }
                 map.put(k.toString(), list);
             } else {
                 map.put(k.toString(), v);
             }
         }
         return map;
     }
}
