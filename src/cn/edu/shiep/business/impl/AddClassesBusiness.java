package cn.edu.shiep.business.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONObject;
import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.dao.CommonDAO;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.util.JsonUtil;

public class AddClassesBusiness extends AbsBusiness{

	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		RequestModel requestModel = new RequestModel();
		try {
			String body = reqModel.getBody();
			JSONObject params = JSONObject.fromObject(body).getJSONObject("data");
			String nameOfClass = params.getString("nameOfClass");
			int flag = CommonDAO.instance().addClasses(nameOfClass);
			requestModel.setHead(""+flag);
		} catch (Exception e) {
			e.printStackTrace();
			requestModel.setHead("0");
		}
		result = JsonUtil.object2json(requestModel);
		try {
			result = URLEncoder.encode(result,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
