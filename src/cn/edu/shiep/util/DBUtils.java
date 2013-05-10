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
		// ��ʼ��torque
		boolean flag = true;
		try {
			Torque.init(dbConfigFile);
			System.out.println("---------- ��ʼ��torque�ɹ�---------");
		} catch (Exception exx) {
			log.error(exx);
			flag = false;
		}
		return flag;
	}

	public static boolean init(Configuration dbConfig) {
		// ��ʼ��torque
		boolean flag = true;
		try {
			Torque.init(dbConfig);
			System.out.println("---------- ��ʼ��torque�ɹ�---------");
		} catch (Exception exx) {
			log.error(exx);
			flag = false;
		}
		return flag;
	}

	public static Connection getDBConn() {
		// ȡ�����ݿ�����
		Connection conn = null;
		try {
			conn = Torque.getConnection();
		} catch (Exception exx) {
			log.error(exx);
		}
		return conn;
	}

	public static Connection getDBConn(String dbName) {
		// ȡ�����ݿ�����
		Connection conn = null;
		try {
			conn = Torque.getConnection(dbName);
		} catch (Exception exx) {
			log.error(exx);
		}
		return conn;
	}

	public static Connection getDBConn(boolean autoCommitFlag) {
		// ȡ�����ݿ�����
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
		// ȡ�����ݿ�����
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
		// �ر����ݿ�����
		try {
			if (conn != null) {
				setAutoCommit(conn , true);// ������Σ���Ӧ�������ӹر�ǰ����AutoCommit ����Ϊ
				// true
				Torque.closeConnection(conn);
				conn = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeDBConn(Connection conn, boolean autoCommitFlag) {
		// �ر����ݿ�����
		try {
			if (conn != null) {
				setAutoCommit(conn , true);// ���۴�ʲôֵ����Ӧ�������ӹر�ǰ����AutoCommit ����Ϊ
				// true
				Torque.closeConnection(conn);
				conn = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeDBStatement(Statement s) {
		// �ر�Statement
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
		// �ر�Statement
		try {
			if (s != null) {
				s.close();
				s = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
		// �ر����ݿ�����
		try {
			if (conn != null) {
				setAutoCommit(conn , true);// ������Σ���Ӧ�������ӹر�ǰ����AutoCommit ����Ϊ
				// true
				Torque.closeConnection(conn);
				conn = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
	}
	public static void closeDBResultSet(ResultSet r) {
		// �ر�ResultSet
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
		// �ر�Statement
		try {
			if (s != null) {
				s.close();
				s = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
	
		// �ر����ݿ�����
		try {
			if (conn != null) {
				setAutoCommit(conn , true);// ���۴�ʲôֵ����Ӧ�������ӹر�ǰ����AutoCommit ����Ϊ
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
		// �ر�Statement
		try {
			if (s != null) {
				s.close();
				s = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}
		// �ر�ResultSet
		try {
			if (r != null) {
				r.close();
				r = null;
			}
		} catch (Exception exx) {
			log.error(exx);
		}		
		// �ر����ݿ�����
		try {
			if (conn != null) {
				setAutoCommit(conn , true);// ���۴�ʲôֵ����Ӧ�������ӹر�ǰ����AutoCommit ����Ϊ
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
	// singleRecord = true ��ʾֻ����һ����¼
	// singleRecord = false ��ʾ�������м�¼
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
	// singleRecord = true ��ʾֻ����һ����¼
	// singleRecord = false ��ʾ�������м�¼
	public static List query(Connection conn,
			String querySql) {
		List results = null;
		boolean singleRecord = false;
		results = query(conn , singleRecord , querySql);
		return results;
	}

	//��ѯ���ݿ�ָ�����õ����ݿ�
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
