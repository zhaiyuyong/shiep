package cn.edu.shiep.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.torque.Torque;
import org.apache.torque.util.BasePeer;

/**
 * @author huangxq
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DBUtils {
	private static Logger log = Logger.getLogger(DBUtils.class.getName());

	public DBUtils() {
	}
 
	public static boolean init(String dbConfigFile) {
		// 初始化torque
		boolean flag = true;
		try {
			Torque.init(dbConfigFile);
			System.out.println("---------- 初始化torque成功---------");
		} catch (Exception exx) {
			log.error(exx);
			flag = false;
		}
		return flag;
	}

	public static boolean init(Configuration dbConfig) {
		// 初始化torque
		boolean flag = true;
		try {
			Torque.init(dbConfig);
			System.out.println("---------- 初始化torque成功---------");
		} catch (Exception exx) {
			log.error(exx);
			flag = false;
		}
		return flag;
	}

	public static Connection getDBConn() {
		// 取得数据库链接
		Connection conn = null;
		try {
			conn = Torque.getConnection();
		} catch (Exception exx) {
			log.error(exx);
		}
		return conn;
	}

	public static Connection getDBConn(String dbName) {
		// 取得数据库链接
		Connection conn = null;
		try {
			conn = Torque.getConnection(dbName);
		} catch (Exception exx) {
			log.error(exx);
		}
		return conn;
	}

	public static Connection getDBConn(boolean autoCommitFlag) {
		// 取得数据库链接
		Connection conn = null;
		try {
			conn = Torque.getConnection();
			setAutoCommit(conn , autoCommitFlag);
		} catch (Exception exx) {
			log.error(exx);
		}
		return conn;
	}

	public static Connection getDBConn(String dbName, boolean autoCommitFlag) {
		// 取得数据库链接
		Connection conn = null;
		try {
			conn = Torque.getConnection(dbName);
			setAutoCommit(conn , autoCommitFlag);
		} catch (Exception exx) {
			log.error(exx);
		}
		return conn;
	}

	public static void closeDBConn(Connection conn) {
		// 关闭数据库链接
		try {
			if (conn != null) {
				setAutoCommit(conn , true);// 无论如何，都应该在连接关闭前，将AutoCommit 设置为
				// true
				Torque.closeConnection(conn);
				conn = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeDBConn(Connection conn, boolean autoCommitFlag) {
		// 关闭数据库链接
		try {
			if (conn != null) {
				setAutoCommit(conn , true);// 无论传什么值，都应该在连接关闭前，将AutoCommit 设置为
				// true
				Torque.closeConnection(conn);
				conn = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeDBStatement(Statement s) {
		// 关闭Statement
		try {
			if (s != null) {
				s.close();
				s = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeDBObject(Statement s, Connection conn) {
		// 关闭Statement
		try {
			if (s != null) {
				s.close();
				s = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
		// 关闭数据库链接
		try {
			if (conn != null) {
				setAutoCommit(conn , true);// 无论如何，都应该在连接关闭前，将AutoCommit 设置为
				// true
				Torque.closeConnection(conn);
				conn = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
	}
	public static void closeDBResultSet(ResultSet r) {
		// 关闭ResultSet
		try {
			if (r != null) {
				r.close();
				r = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}		
	}	
	public static void closeDBObject(Statement s, Connection conn,
			boolean autoCommitFlag) {
		// 关闭Statement
		try {
			if (s != null) {
				s.close();
				s = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
	
		// 关闭数据库链接
		try {
			if (conn != null) {
				setAutoCommit(conn , true);// 无论传什么值，都应该在连接关闭前，将AutoCommit 设置为
				// true
				Torque.closeConnection(conn);
				conn = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
	}
	public static void closeDBObject(Statement s, ResultSet r ,Connection conn,
			boolean autoCommitFlag) {
		// 关闭Statement
		try {
			if (s != null) {
				s.close();
				s = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
		// 关闭ResultSet
		try {
			if (r != null) {
				r.close();
				r = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}		
		// 关闭数据库链接
		try {
			if (conn != null) {
				setAutoCommit(conn , true);// 无论传什么值，都应该在连接关闭前，将AutoCommit 设置为
				// true
				Torque.closeConnection(conn);
				conn = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void commit(Connection conn) {
		try {
			if (conn != null) {
				if (conn.getMetaData().supportsTransactions()
					&& conn.getAutoCommit() == false)
				{
					conn.commit();
					conn.setAutoCommit(true);
				}
			}
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null) {
                if (conn.getMetaData().supportsTransactions()
                    && conn.getAutoCommit() == false)
                {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
			}
		} catch (Exception ee) {
			log.error(ee);
		}
	}
	public static void setAutoCommit(Connection conn , boolean autoCommitFlag)
	{
		try
		{
			if (conn.getMetaData().supportsTransactions())
			{
				conn.setAutoCommit(autoCommitFlag);
			}
		}
		catch (Exception e)
		{
			log.error(e);
		}	
	}

	public static List query(String querySql) {
		List results = null;
		try {
			log.debug("excute query sql is : " + querySql);
			results = BasePeer.executeQuery(querySql);
			if(results != null )
				log.debug("excute query sql results size == " + results.size());
			else
				log.error("excute query sql results is null ");
			
		} catch (Exception ee) {
			results = null;
			log.debug("excute query sql have an exception : \r\n");
			log.error(ee);
		}
		return results;
	}
	// singleRecord = true 表示只返回一条记录
	// singleRecord = false 表示返回所有记录
	public static List query(Connection conn, boolean singleRecord,
			String querySql) {
		List results = null;
		try {
			if (conn != null) {
				log.debug("excute query sql is : " + querySql);
				results = BasePeer.executeQuery(querySql, singleRecord, conn);
				if(results != null )
					log.debug("excute query sql results size == " + results.size());
				else
					log.error("excute query sql results is null ");				
			}
			else
			{
				log.error("not excute query sql, because db connection is null ");
				results = null;
			}
		} catch (Exception ee) {
			results = null;
			log.error(ee);
		}
		return results;
	}
	// singleRecord = true 表示只返回一条记录
	// singleRecord = false 表示返回所有记录
	public static List query(Connection conn,
			String querySql) {
		List results = null;
		boolean singleRecord = false;
		results = query(conn , singleRecord , querySql);
		return results;
	}

	//查询数据库指定配置的数据库
	public static List query(String dbName ,String querySql) {
		List results = null;
		try {
			Connection conn = DBUtils.getDBConn(dbName);
			if(conn != null)
			{
				results = query(conn , querySql);
			}
			else
			{
				log.error("not excute query sql, because db connection is null ");
				results = null;
			}
		} catch (Exception ee) {
			results = null;
			log.debug("excute query sql have an exception : \r\n");
			log.error(ee);
		}
		return results;
	}

	public static int insertOrUpdate(String insertSql) {
		int results = -1;
		try {
			log.debug("excute insert or update sql is : " + insertSql);
			results = BasePeer.executeStatement(insertSql);
			log.debug("excute insert or update sql result == " + results);
		} catch (Exception ee) {
			results = -1;	
			log.error("excute insert or update sql have an exception : \r\n");			
			log.error(ee);
		}
		return results;
	}

	public static int insertOrUpdate(Connection conn, String insertSql) {
		int results = -1;
		try {
			if (conn != null) {
				log.debug("excute insert or update sql is : "  + insertSql);
				results = BasePeer.executeStatement(insertSql, conn);
				log.debug("excute insert or update sql result == " + results);
			}
			else
			{
				log.error("not excute insert or update sql, because db connection is null ");
			}
		} catch (Exception ee) {
			results = -1;
			log.error("excute insert or update sql have an exception : \r\n");			
			log.error(ee);
		}
		return results;
	}
}
