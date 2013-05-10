package cn.edu.shiep.business.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.Record;

import net.sf.json.JSONObject;
import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.dao.CommonDAO;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.model.record.OkClass;
import cn.edu.shiep.model.res.DetailOkClassResModel;
import cn.edu.shiep.util.JsonUtil;

public class DetailOkClassBusiness extends AbsBusiness{

	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		DetailOkClassResModel model = new DetailOkClassResModel();
		try {
			
			String body = reqModel.getBody();
			JSONObject params = JSONObject.fromObject(body).getJSONObject("data");
			String classId = params.getString("classId");
			List<Record> list = CommonDAO.instance().getOkClassDetailById(classId);
			model = convert(list);
			model.setHead("1");
		} catch (Exception e) {
			model.setHead("0");
		}
		result = JsonUtil.object2json(model);
		System.out.println("DetailOkClassBusiness result = "+result);
		try {
			result = URLEncoder.encode(result,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private DetailOkClassResModel convert(List<Record> list) throws DataSetException{
		DetailOkClassResModel model = new DetailOkClassResModel();
		if(list!=null&&list.size()>0){
			OkClass okClass = new OkClass();
			Record record = list.get(0);
			okClass.setClassAddress(record.getValue("classaddress").asString());
			okClass.setClassName(record.getValue("classname").asString());
			okClass.setClassTimeNo(record.getValue("classtime").asString());
			okClass.setDayOfWeek(record.getValue("dayofweek").asString());
			okClass.setIdOfClass(record.getValue("uuid").asString());
			okClass.setTeacherOfClass(record.getValue("teachername").asString());
			model.setData(okClass);
		}
		return model;
	}

}
