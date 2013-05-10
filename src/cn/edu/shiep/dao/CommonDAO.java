package cn.edu.shiep.dao;

import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.workingdogs.village.Record;

import cn.edu.shiep.servlet.InitServlet;
import cn.edu.shiep.util.DBUtils;

public class CommonDAO {
	private static CommonDAO instance;

	private CommonDAO() {
	}

	public static CommonDAO instance() {
		if (instance == null) {
			instance = new CommonDAO();
		}
		return instance;
	}

	private static Logger log = Logger.getLogger(CommonDAO.class);

	// 登录校验
	public String checkLogin(String username, String password) {
		String flag = null;
		List<Record> list = null;
		String sql = "select uuid,username,password from tbuser where username='"
				+ username + "' and password = '" + password + "'";
		System.out.println("sql=" + sql);
		try {
			list = DBUtils.query(sql);
			System.out.println("list    size = " + list.size());
			if (list != null && list.size() > 0) {
				flag = list.get(0).getValue("uuid").asString();
			}
		} catch (Exception e) {
			flag = null;
		}
		return flag;
	}

	public List<Record> getAllClass(String className) {
		String sql = "select * from tbclass  t where t.className like '%"
				+ className + "%'";
		List<Record> list = null;
		try {
			list = DBUtils.query(sql);
			System.out.println("---------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Record> getAllTeacher(String name) {
		String sql = "select * from tbteacher  t where t.teachername like '%"
				+ name + "%'";
		List<Record> list = null;
		try {
			list = DBUtils.query(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int createClass(String teacherid, String classid, String classtime,
			String classaddress, String comments, String dayOfWeek) {
		int flag = -1;
		String sql = "insert into tbteacher_class "
				+ "(uuid,teacherid,classid,classtime,classaddress,comments,dayOfWeek)"
				+ "values('" + UUID.randomUUID().toString() + "','" + teacherid
				+ "','" + classid + "','" + classtime + "','" + classaddress
				+ "','" + comments + "','" + dayOfWeek + "')";
		try {
			flag = DBUtils.insertOrUpdate(sql);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return flag;
	}

	public List<Record> getWeekClass() {
		return null;
	}

	public static void main(String[] args) throws ServletException {
		InitServlet servlet = new InitServlet();
		servlet.init();

		// String uuid = UUID.randomUUID().toString();
		// String sql = "insert into tbteacher values('"+uuid+"','王淑君','讲师')";
		// DBUtils.insertOrUpdate(sql);
		// try {
		// List<Record> list = CommonDAO.instance().getAllTeacher("刘");
		// for(Record record:list){
		// System.out.println(record.getValue("teacherName").asString());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		insertUser();

	}

	public static void insertUser() {
		String sql = "insert into tbuser values ('"
				+ UUID.randomUUID().toString() + "','admin','123456')";
		DBUtils.insertOrUpdate(sql);
	}

	public static void insertClass() {
		String sql = "insert into tbclass values ('"
				+ UUID.randomUUID().toString() + "','oracle基础')";
		DBUtils.insertOrUpdate(sql);
	}

	public static void insertUseClass() {
		String sql = "insert into tbuser_use_class values('"
				+ UUID.randomUUID().toString()
				+ "',"
				+ "'0706a308-4b73-42e3-9f1c-7fc5ace9526a','c46f9a40-a5f3-4b18-9416-75072f11d06a')";
		DBUtils.insertOrUpdate(sql);
	}

	/**
	 * 
	 * @param userId
	 * @param classId
	 * @return 1、-1系统错误 2、2已经选了该课 3、 1选课成功 4、0有冲突
	 */
	public int xuanke(String userId, String classId) {
		int flag = -1;
		String sql = "select * from tbuser_use_class t where t.class_Id='"
				+ classId + "' and t.userid='" + userId + "'";
		try {
			List<Record> list = DBUtils.query(sql);
			if (list.size() > 0) {
				flag = 2;// 已经选了该课
			} else {
				// sql = "select * from tbteacher_class where ";

				sql = "insert into tbuser_use_class(uuid,class_id,userid)"
						+ " values('" + UUID.randomUUID().toString() + "','"
						+ classId + "','" + userId + "')";
				int f = DBUtils.insertOrUpdate(sql);
				if (f > 0) {
					flag = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public List<Record> getAllOKClass(String searchName, String userId) {
		List<Record> list = null;
		String sql = "select t1.uuid, t1.classtime, t1.dayofweek, t1.classaddress , t2.classname, t3.teachername"
				+ " from tbteacher_class t1 INNER JOIN tbclass t2 on t1.classid= t2.uuid "
				+ "INNER JOIN tbteacher t3 on t1.teacherid= t3.uuid "
				+ "  where t2.classname like '%" + searchName + "%'";
		try {
			list = DBUtils.query(sql);
			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addTeacher(String jobOfTeacher, String nameOfTeacher) {
		int flag = -1;
		String sql = "select * from tbteacher t where t.teachername='"+nameOfTeacher+"'";
		
		try {
			List<Record> list = DBUtils.query(sql);
			if(list.size()>0){
				flag = 2;
			}else {
				 sql = "insert into tbteacher (uuid,teachername,teacherjob)"
						+ "values('" + UUID.randomUUID().toString() + "','"
						+ nameOfTeacher + "','" + jobOfTeacher + "')";
				flag = DBUtils.insertOrUpdate(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int addClasses(String nameOfClass) {
		int flag = -1;
		String sql = "select * from tbclass t where t.className='"
				+ nameOfClass + "'";

		try {
			List<Record> list = DBUtils.query(sql);
			if (list.size() > 0) {
				flag = 2;
			} else {
				sql = "insert into tbclass (uuid,className)" + "values('"
						+ UUID.randomUUID().toString() + "','" + nameOfClass
						+ "')";
				flag = DBUtils.insertOrUpdate(sql);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 根据学号查询一周的课
	 * 
	 * @param string
	 * @return
	 */

	public List<Record> getDayOne(String userId, int day) {
		List<Record> list = null;
		String sql = "select t1.uuid, t1.classtime, t1.dayofweek, t1.classaddress ,"
				+ " t2.classname, t3.teachername,t4.userid from tbteacher_class"
				+ " t1 INNER JOIN tbclass t2 on t1.classid= t2.uuid INNER JOIN "
				+ " tbteacher t3 on t1.teacherid= t3.uuid inner join tbuser_use_class t4 "
				+ " on t4.class_id=t1.uuid inner join tbuser t5 on t5.uuid= t4.userid "
				+ "where  t5.uuid='"
				+ userId
				+ "' and t1.dayofweek = '"
				+ day
				+ "'";
		try {
			list = DBUtils.query(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int tuike(String classId, String userId) {
		int flag = -1;
		String sql = "select * from tbuser_use_class t where t.class_Id='"
				+ classId + "' and t.userid='" + userId + "'";
		try {
			List<Record> list = DBUtils.query(sql);
			if (list.size() > 0) {
				sql = "delete from tbuser_use_class t where t.class_Id='"
						+ classId + "' and t.userid='" + userId + "' ";
				flag = DBUtils.insertOrUpdate(sql);
			} else {
				return flag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public List<Record> selectXuanClass(String userId) {
		List<Record> list = null;
		String sql = "select t.class_Id from tbuser_use_class t where t.userid='"
				+ userId + "'";
		try {
			list = DBUtils.query(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int register(String userName, String password) {
		int flag = -1;
		String sql = "select * from tbuser t where t.username = '" + userName
				+ "'";
		try {
			List<Record> list = DBUtils.query(sql);
			if (list.size() > 0) {
				flag = 2;
			} else {
				sql = "insert into tbuser(uuid,username,password)" + "values('"
						+ UUID.randomUUID().toString() + "','" + userName
						+ "','" + password + "')";
				flag = DBUtils.insertOrUpdate(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public List<Record> getOkClassDetailById(String classId) {
		List<Record> list = null;
		String sql = "select t1.uuid, t1.classtime, t1.dayofweek, t1.classaddress ,"
			+ " t2.classname, t3.teachername from tbteacher_class"
			+ " t1 INNER JOIN tbclass t2 on t1.classid= t2.uuid INNER JOIN "
			+ " tbteacher t3 on t1.teacherid= t3.uuid  and t1.uuid='"+classId+"'";
		try {
			list = DBUtils.query(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return list;
	}

}
