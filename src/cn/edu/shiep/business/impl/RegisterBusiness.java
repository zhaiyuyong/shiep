package cn.edu.shiep.business.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONObject;
import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.dao.CommonDAO;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.util.JsonUtil;

public class RegisterBusiness extends AbsBusiness{

	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		try {
			RequestModel requestModel = new RequestModel();
			String body = reqModel.getBody();
			JSONObject params = JSONObject.fromObject(body).getJSONObject("data");
			String userName = params.getString("userName");
			String password = params.getString("password");
			int flag = CommonDAO.instance().register(userName,password);
			reqModel.setHead(flag+"");
		} catch (Exception e) {
			reqModel.setHead("0");
			e.printStackTrace();
		}
		result = JsonUtil.object2json(reqModel);
		try {
			result = URLEncoder.encode(result,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
