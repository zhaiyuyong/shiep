package cn.edu.shiep.business.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.Record;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.dao.CommonDAO;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.model.record.DayClass;
import cn.edu.shiep.model.record.OkClass;
import cn.edu.shiep.model.res.OneDayClassResModel;
import cn.edu.shiep.model.res.TodayClassModel;
import cn.edu.shiep.util.JsonUtil;

public class TodayClassBusiness extends AbsBusiness {
	
	private static Logger log = Logger.getLogger(TodayClassBusiness.class);

	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		try {
			String body = reqModel.getBody();
			JSONObject jsonObject = JSONObject.fromObject(body).getJSONObject("data");
			String studentId = jsonObject.getString("userId");
			String dayOfWeek = jsonObject.getString("dayOfWeek");
			CommonDAO dao = CommonDAO.instance();
			List<Record> list = dao.getDayOne(studentId, Integer.parseInt(dayOfWeek));
			OneDayClassResModel model = convert(list);
			result = JsonUtil.object2json(model);
			System.out.println("TodayClassBusiness result = "+result);
			result = URLEncoder.encode(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
		return result;
	}
	
	private OneDayClassResModel convert(List<Record> list) throws DataSetException{
		OneDayClassResModel model = new OneDayClassResModel();
		model.setRecordamount(5);
		OkClass record1 = new OkClass();
	    OkClass record2 = new OkClass();
		OkClass record3 = new OkClass();
	    OkClass record4 = new OkClass();
		OkClass record5 = new OkClass();
		for(int i=0;i<list.size();i++){
			Record record = list.get(i);
			if("1-2".equals(record.getValue("classtime").asString())){
				record1.setClassAddress(record.getValue("classaddress").asString());
				record1.setClassName(record.getValue("classname").asString());
				record1.setClassTimeNo(record.getValue("classtime").asString());
				record1.setDayOfWeek(record.getValue("dayofweek").asString());
				record1.setIdOfClass(record.getValue("uuid").asString());
				record1.setTeacherOfClass(record.getValue("teachername").asString());
			}else if("3-4".equals(record.getValue("classtime").asString())){
				record2.setClassAddress(record.getValue("classaddress").asString());
				record2.setClassName(record.getValue("classname").asString());
				record2.setClassTimeNo(record.getValue("classtime").asString());
				record2.setDayOfWeek(record.getValue("dayofweek").asString());
				record2.setIdOfClass(record.getValue("uuid").asString());
				record2.setTeacherOfClass(record.getValue("teachername").asString());
			}else if("5-6".equals(record.getValue("classtime").asString())){
				record3.setClassAddress(record.getValue("classaddress").asString());
				record3.setClassName(record.getValue("classname").asString());
				record3.setClassTimeNo(record.getValue("classtime").asString());
				record3.setDayOfWeek(record.getValue("dayofweek").asString());
				record3.setIdOfClass(record.getValue("uuid").asString());
				record3.setTeacherOfClass(record.getValue("teachername").asString());
			}else if("7-8".equals(record.getValue("classtime").asString())){
				record4.setClassAddress(record.getValue("classaddress").asString());
				record4.setClassName(record.getValue("classname").asString());
				record4.setClassTimeNo(record.getValue("classtime").asString());
				record4.setDayOfWeek(record.getValue("dayofweek").asString());
				record4.setIdOfClass(record.getValue("uuid").asString());
				record4.setTeacherOfClass(record.getValue("teachername").asString());
			}else if("9-11".equals(record.getValue("classtime").asString())){
				record5.setClassAddress(record.getValue("classaddress").asString());
				record5.setClassName(record.getValue("classname").asString());
				record5.setClassTimeNo(record.getValue("classtime").asString());
				record5.setDayOfWeek(record.getValue("dayofweek").asString());
				record5.setIdOfClass(record.getValue("uuid").asString());
				record5.setTeacherOfClass(record.getValue("teachername").asString());
			}
		}
		List<OkClass> rclist = new ArrayList<OkClass>();
		rclist.add(record1);
		rclist.add(record2);
		rclist.add(record3);
		rclist.add(record4);
		rclist.add(record5);
		model.setRclist(rclist);
		return model;
	}
	

}

