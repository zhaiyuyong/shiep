package cn.edu.shiep.business.impl;

import net.sf.json.JSONObject;
import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.dao.CommonDAO;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.util.JsonUtil;

public class AddTeacherClassBusiness extends AbsBusiness{

	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		RequestModel requestModel = new RequestModel();
		requestModel.setHead("0");
		try {
			String body = reqModel.getBody();
			JSONObject params = JSONObject.fromObject(body).getJSONObject("data");
			String teacherid = params.getString("teacherId");
			String classid = params.getString("classId");
			String classtime = params.getString("classTime");
			String classaddress = params.getString("classAddress");
			String comments = params.getString("comments");
			String dayOfWeek = params.getString("dayOfWeek");
			int flag = CommonDAO.instance().createClass(teacherid, classid, classtime, classaddress, comments,dayOfWeek);
			if(flag>0){
				requestModel.setHead("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			requestModel.setHead("0");
		}
		result = JsonUtil.object2json(requestModel);
		return result;
	}

}
