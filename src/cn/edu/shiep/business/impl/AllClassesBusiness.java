package cn.edu.shiep.business.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.Record;

import net.sf.json.JSONObject;
import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.dao.CommonDAO;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.model.record.OkClass;
import cn.edu.shiep.model.res.OkClassResModel;
import cn.edu.shiep.util.JsonUtil;

public class AllClassesBusiness extends AbsBusiness{

	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		String body = reqModel.getBody();
		JSONObject jsonObject = JSONObject.fromObject(body).getJSONObject("data");
		String searchName = jsonObject.getString("searchName");
		String userId = jsonObject.getString("userId");
		try {
			List<Record> list = CommonDAO.instance().getAllOKClass(searchName, userId);
			OkClassResModel model = convert(list,userId);
			result = JsonUtil.object2json(model);
			System.out.println("result====="+result);
			result = URLEncoder.encode(result,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	private OkClassResModel convert(List<Record> list,String userId) throws DataSetException{
		OkClassResModel okClassResModel = new OkClassResModel();
		okClassResModel.setRecordamount(list.size());
		List<OkClass> rclist = new ArrayList<OkClass>();
		OkClass okClass = null;
		List<Record> list2 = CommonDAO.instance().selectXuanClass(userId);
		for(int i=0;i<list.size();i++){
			Record record = list.get(i);
			okClass = new OkClass();
			okClass.setClassAddress(record.getValue("classaddress").asString());
			okClass.setClassName(record.getValue("classname").asString());
			okClass.setClassTimeNo(record.getValue("classtime").asString());
			okClass.setDayOfWeek(record.getValue("dayofweek").asString());
			okClass.setIdOfClass(record.getValue("uuid").asString());
			okClass.setTeacherOfClass(record.getValue("teachername").asString());
			String classId = record.getValue("uuid").asString();
			okClass.setIsAddDelete("0");
			
			for(int j=0;j<list2.size();j++){
				String class_id = list2.get(j).getValue("class_id").asString();
				if(classId.equals(class_id)){
					okClass.setIsAddDelete("1");
					break;
				}else {
					continue;
				}
			}
			rclist.add(okClass);
		}
		okClassResModel.setRclist(rclist);
		return okClassResModel;
	}

}
