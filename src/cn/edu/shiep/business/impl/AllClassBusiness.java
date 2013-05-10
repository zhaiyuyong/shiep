package cn.edu.shiep.business.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.Record;

import net.sf.json.JSONObject;
import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.dao.CommonDAO;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.model.record.ClassModel;
import cn.edu.shiep.model.res.ClassResModel;
import cn.edu.shiep.util.JsonUtil;

public class AllClassBusiness extends AbsBusiness{
	
	private Logger log = Logger.getLogger(AllClassBusiness.class);

	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		String body = reqModel.getBody();
		JSONObject jsonObject = JSONObject.fromObject(body).getJSONObject("data");
		try {
			List<Record> list = CommonDAO.instance().getAllClass(jsonObject.getString("searchName"));
			ClassResModel classResModel = convert(list);
			result = JsonUtil.object2json(classResModel);
			System.out.println(result);
			result = URLEncoder.encode(result,"UTF-8");
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return result;
	}
	
	private ClassResModel convert(List<Record> list) throws DataSetException{
		ClassResModel classResModel = new ClassResModel();
		classResModel.setRecordamount(list.size());
		List<ClassModel> rclist = new ArrayList<ClassModel>();
		ClassModel classModel = null;
		for(int i=0;i<list.size();i++){
			Record record = list.get(i);
			classModel = new ClassModel();
			System.out.println(record.getValue("uuid").asString());
			classModel.setClassId(record.getValue("uuid").asString());
			classModel.setClassName(record.getValue("className").asString());
			rclist.add(classModel);
		}
		classResModel.setRclist(rclist);
		return classResModel;
	}

}
