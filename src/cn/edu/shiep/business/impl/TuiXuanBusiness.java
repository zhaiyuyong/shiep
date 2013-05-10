package cn.edu.shiep.business.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONObject;
import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.dao.CommonDAO;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.util.JsonUtil;

public class TuiXuanBusiness extends AbsBusiness{

	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		RequestModel model = new RequestModel();
		try {
			String body = reqModel.getBody();
			JSONObject params = JSONObject.fromObject(body).getJSONObject("data");
			String classId = params.getString("teacherClassId");
			String userId = params.getString("userId");
			int flag = CommonDAO.instance().tuike(classId,userId);
			if(flag>0){
				model.setHead("1");
			}
		} catch (Exception e) {
			model.setHead("0");
			e.printStackTrace();
		}
		result = JsonUtil.object2json(model);
		System.out.println("TuiXuanBusiness result = "+result);
		try {
			result = URLEncoder.encode(result, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
