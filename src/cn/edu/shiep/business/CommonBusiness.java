package cn.edu.shiep.business;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.edu.shiep.model.HeadModel;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.model.SysreqModel;
import cn.edu.shiep.servlet.InitServlet;
import cn.edu.shiep.util.CryptTool;
import cn.edu.shiep.util.JsonUtil;
import cn.edu.shiep.util.MD5;
import cn.edu.shiep.util.MapSortUtil;

public class CommonBusiness {

	private static Logger log = Logger.getLogger(CommonBusiness.class);

	/**
	 * ��ȡ�������
	 * 
	 * @return
	 */
	public static String getParamterStr(HttpServletRequest request) {
		String requestJsonStr = null;
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = request.getInputStream();
			InputStreamReader isReader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isReader);
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			requestJsonStr = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestJsonStr;
	}
	/**
	 *����ҵ������
	 * 
	 * @author Administrator
	 * 
	 */
	public static RequestModel parseJsonString(String reqMsg) {
		JSONObject jsonObject = null;
		RequestModel requestModel = null;
		try {
			String newmsg = URLDecoder.decode(reqMsg, "UTF-8");
			jsonObject = JSONObject.fromObject(newmsg);
			String head = jsonObject.getString("head");
			String body = jsonObject.getString("body");
			
			log.debug("��������head��"+head);
			log.debug("��������body��"+body);
			
			requestModel = new RequestModel(head,body);
			String  resStr = JsonUtil.object2json(requestModel);
			log.debug("����������Ϣ��"+resStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestModel;
	}
	
	/**
	 * json ת��Ϊ Obj(SysreqModel)
	 * 
	 * @return
	 */
	public static SysreqModel JSONbodyStr2Obj(String body) {
		SysreqModel sysreqModel = null;
	 	JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(body);
			sysreqModel = new SysreqModel();
			sysreqModel.setData(jsonObject.getString("data"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysreqModel;
	}
	
	
	/**
	 * ��������SIGNǩ��
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String createReqSign(JSONObject jsonObject) {
		String sign = null;
		try {
			String summarykey = MD5.encrypt(InitServlet.summarykey);
			StringBuffer sBuffer = new StringBuffer("");
			Map<String,String> map = MapSortUtil.sortMapByKey(jsonObject);
			for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
				 String key = (String) iterator.next();
				if (!key.equals("sysreq")&&!key.equals("rclist")) {
					sBuffer.append(map.get(key));
				}
			}
			String sbString = sBuffer.append(summarykey).toString();
			log.debug("ReqSign MD5����ǰ��"+sbString);
			sign = MD5.encrypt(sbString);
			log.debug("ReqSign MD5���ܺ�"+sign);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��������Sign�쳣��"+e);
		}
		return sign;
	}
	
	/**
	 * json ת��Ϊ Obj(HeadModel)
	 * 
	 * @return
	 */
	public static HeadModel JSONheadStr2Obj(String head) {
		HeadModel headModel = null;
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(head);
			headModel = new HeadModel();
			headModel.setMethod(jsonObject.getString("method"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return headModel;
	}
}
