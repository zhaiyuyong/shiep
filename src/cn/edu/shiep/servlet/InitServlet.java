package cn.edu.shiep.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.workingdogs.village.Record;

import cn.edu.shiep.util.DBUtils;
import cn.edu.shiep.util.FileUtils;

public class InitServlet extends HttpServlet {

	private static Logger log = Logger.getLogger(InitServlet.class);
	private static final String LOG4J_CONFIG_FILE = "log4j.properties";
	private static final String COMMON_CONFIG_FILE = "common.properties";
	private static final String TORQUE_CONFIG_FILE = "torque.properties";
	public static PropertiesConfiguration commonConfig = null;
	public static String initpassword;// 初始密码
	public static String summarykey; // 摘要Key

	@Override
	public void init() throws ServletException {
		synchronized (this.getClass()) {
			log.debug("SHIEPServer init start ------------------------- ");
			ClassLoader classLoader = this.getClass().getClassLoader();
			InputStream commonIn = null;
			InputStream logIn = null;
			InputStream dbIn = null;
			try {
				// 加载‘common.properties’文件
				commonIn = classLoader.getResourceAsStream(COMMON_CONFIG_FILE);
				commonConfig = new PropertiesConfiguration();
				commonConfig.load(commonIn);
				System.out
						.println("SHIEPServer  load common.properites--------------");

				logIn = classLoader.getResourceAsStream(LOG4J_CONFIG_FILE);
				Properties p = new Properties();
				p.load(logIn);
				PropertyConfigurator.configure(p);

				log
						.debug("SHIEPServer log4j.properties init success -----------------");
				initpassword = commonConfig.getString("initpassword");
				log.debug("initpassword = " + initpassword);
				summarykey = commonConfig.getString("summarykey");
				log.debug("summarykey = " + summarykey);

				// 加载‘torque.properties’文件并初始化torque
				dbIn = classLoader.getResourceAsStream(TORQUE_CONFIG_FILE);
				PropertiesConfiguration dbConfig = new PropertiesConfiguration();
				dbConfig.load(dbIn);

				DBUtils.init(dbConfig);
				// 测试数据库连接情况下注释,注释完放开
				try {
					String sql = "SELECT to_char(sysdate, 'yyyy-mm-dd hh:mm:ss') FROM dual ";
					List<Record> results = DBUtils.query(sql);
					if (results != null && results.size() > 0) {
						Record record = (Record) results.get(0);
						log
								.debug(" 系统时间 == "
										+ (record.getValue(1).asString()));
						System.out.println(" 系统时间 == "+ (record.getValue(1).asString()));
					}
					log.debug(" database connection init success ");
				} catch (Exception ex) {
					log.error("数据库异常 == " + ex);
					ex.printStackTrace();
					log.error(ex);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				FileUtils.closeInputStream(commonIn);
				FileUtils.closeInputStream(logIn);
				FileUtils.closeInputStream(dbIn);
			}
			log.debug("SHIEPServer init() Ready to Rumble!");
		}
		log.debug("SHIEPServer init end ------------------------- ");
	}


}
