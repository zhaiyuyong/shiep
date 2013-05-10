package cn.edu.shiep.business.impl;

import net.sf.json.JSONObject;
import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.dao.CommonDAO;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.util.JsonUtil;

public class XuanKeBusiness extends AbsBusiness{

	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		RequestModel model = new RequestModel();
		model.setHead("0");
		try {
			String body = reqModel.getBody();
			JSONObject params = JSONObject.fromObject(body).getJSONObject("data");
			String userId = params.getString("userId");
			String classId = params.getString("teacherClassId");
			int flag = CommonDAO.instance().xuanke(userId,classId);
			if(flag==2){
				model.setHead("1");
				model.setBody("已经选了该课");
			}else if(flag==1){
				model.setHead("1");
				model.setBody("选课成功");
			}else {
				model.setHead("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.setHead("0");
		}
		result = JsonUtil.object2json(model);
		return result;
	}

}
