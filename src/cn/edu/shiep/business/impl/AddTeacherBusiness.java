package cn.edu.shiep.business.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.dao.CommonDAO;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.util.JsonUtil;

public class AddTeacherBusiness  extends AbsBusiness{

	private Logger log = Logger.getLogger(AddTeacherBusiness.class);
	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		RequestModel requestModel = new RequestModel();
		try {
			String body = reqModel.getBody();
			JSONObject params = JSONObject.fromObject(body).getJSONObject("data");
			String jobOfTeacher = params.getString("jobOfTeacher");
			String nameOfTeacher = params.getString("nameOfTeacher");
			int flag = CommonDAO.instance().addTeacher(jobOfTeacher,nameOfTeacher);
		  requestModel.setHead(""+flag);
		} catch (Exception e) {
			e.printStackTrace();
			requestModel.setHead("0");
		}
		result = JsonUtil.object2json(requestModel);
		log.debug("增加老师返回消息：："+result);
		try {
			result = URLEncoder.encode(result,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("增加老师返回消息 utf-8：："+result);
		return result;
	}

}
