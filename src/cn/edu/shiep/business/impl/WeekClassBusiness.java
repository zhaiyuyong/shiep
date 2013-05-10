package cn.edu.shiep.business.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import net.sf.json.JSONObject;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.Record;

import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.dao.CommonDAO;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.model.record.DayClass;
import cn.edu.shiep.model.record.OkClass;
import cn.edu.shiep.model.res.WeekClassResModel;
import cn.edu.shiep.util.JsonUtil;

public class WeekClassBusiness extends AbsBusiness {

	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		RequestModel model = new RequestModel();
		try {
			String body = reqModel.getBody();
			JSONObject params = JSONObject.fromObject(body).getJSONObject("data");
			List<Record> list1 = CommonDAO.instance().getDayOne(
					params.getString("userId"), 1);
			List<Record> list2 = CommonDAO.instance().getDayOne(
					params.getString("userId"), 2);
			List<Record> list3 = CommonDAO.instance().getDayOne(
					params.getString("userId"), 3);
			List<Record> list4 = CommonDAO.instance().getDayOne(
					params.getString("userId"), 4);
			List<Record> list5 = CommonDAO.instance().getDayOne(
					params.getString("userId"), 5);
			List<Record> list6 = CommonDAO.instance().getDayOne(
					params.getString("userId"), 6);
			List<Record> list7 = CommonDAO.instance().getDayOne(
					params.getString("userId"), 7);
			WeekClassResModel weekClassResModel = result(list1, list2, list3, list4, list5, list6, list7);
			model.setBody(JsonUtil.object2json(weekClassResModel));
			model.setHead("1");
		} catch (Exception e) {
			model.setHead("0");
			e.printStackTrace();
		}
		result = JsonUtil.object2json(model);
		System.out.println("一周课程 结果：："+result);
		try {
			result = URLEncoder.encode(result, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	private WeekClassResModel result(List<Record> list1,List<Record> list2,List<Record> list3,List<Record> list4,List<Record> list5,List<Record> list6,List<Record> list7) throws DataSetException{
		WeekClassResModel weekClassResModel = new WeekClassResModel();
		DayClass day1 = convert(list1);
		DayClass day2 = convert(list2);
		DayClass day3 = convert(list3);
		DayClass day4 = convert(list4);
		DayClass day5 = convert(list5);
		DayClass day6 = convert(list6);
		DayClass day7 = convert(list7);
		weekClassResModel.setDay1(day1);
		weekClassResModel.setDay2(day2);
		weekClassResModel.setDay3(day3);
		weekClassResModel.setDay4(day4);
		weekClassResModel.setDay5(day5);
		weekClassResModel.setDay6(day6);
		weekClassResModel.setDay7(day7);
		return weekClassResModel;
	}

	private DayClass convert(List<Record> list) throws DataSetException {
		DayClass day = new DayClass();
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
		day.setRecord1(record1);
		day.setRecord2(record2);
		day.setRecord3(record3);
		day.setRecord4(record4);
		day.setRecord5(record5);
		return day;
	}

}
