package cn.edu.shiep.business.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.Record;

import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.dao.CommonDAO;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.model.record.Teacher;
import cn.edu.shiep.model.res.TeacherResModel;
import cn.edu.shiep.util.JsonUtil;

public class TeacherListBusiness extends AbsBusiness{
	private static Logger log = Logger.getLogger(TeacherListBusiness.class);

	@Override
	public String doBusiness(RequestModel reqModel) {
		String result = null;
		String body = reqModel.getBody();
		JSONObject jsonObject = JSONObject.fromObject(body).getJSONObject("data");
		try {
			List<Record> list = CommonDAO.instance().getAllTeacher(jsonObject.getString("teacherName"));
			TeacherResModel resModel = convert(list);
			result = JsonUtil.object2json(resModel);
			System.out.println("result=="+result);
			result = URLEncoder.encode(result,"UTF-8");
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	private TeacherResModel convert(List<Record> list) throws DataSetException{
		TeacherResModel teacherResModel = new TeacherResModel();
		List<Teacher> rclist = new ArrayList<Teacher>();
		Teacher teacher = null;
		for(int i=0;i<list.size();i++){
			Record record = list.get(i);
			teacher = new Teacher();
			teacher.setTeacherId(record.getValue("uuid").asString());
			teacher.setTeacherName(record.getValue("teacherName").asString());
			teacher.setTeacherJob(record.getValue("teacherJob").asString());
			rclist.add(teacher);
		}
		teacherResModel.setRclist(rclist);
		teacherResModel.setRecordamount(rclist.size());
		return teacherResModel;	
	}

}
