package cn.edu.shiep.business.impl;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.edu.shiep.business.AbsBusiness;

import cn.edu.shiep.dao.CommonDAO;

import cn.edu.shiep.model.RequestModel;

import cn.edu.shiep.util.JsonUtil;


public class LoginBusiness extends AbsBusiness{
	private static Logger log = Logger.getLogger(LoginBusiness.class);

	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		RequestModel model = new RequestModel();
		model.setHead("0");
		try {
			CommonDAO commonDAO = CommonDAO.instance();
			String body = reqModel.getBody();
			JSONObject jsonObject = JSONObject.fromObject(body).getJSONObject("data");
			String flag = commonDAO.checkLogin(jsonObject.getString("username"), jsonObject.getString("password"));
			if(flag!=null){
				model.setHead("1");
				model.setBody(flag);
			}else {
				model.setHead("0");
			}
			result = JsonUtil.bean2json(model);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			result = JsonUtil.bean2json(model);
		}
		return result;
	}

}
