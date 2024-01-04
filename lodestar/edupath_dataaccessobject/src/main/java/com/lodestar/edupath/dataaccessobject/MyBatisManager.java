package com.lodestar.edupath.dataaccessobject;
import java.io.InputStream;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.dto.IModel;



public final class MyBatisManager
{

	private static final Logger		OUT			= LoggerFactory.getLogger(MyBatisManager.class.getName());
	private SqlSessionFactory		sqlSessionFactory;
	private static MyBatisManager	dbManager	= new MyBatisManager();

	/**
	 * 
	 */
	private MyBatisManager()
	{
		synchronized (MyBatisManager.class)
		{
			try
			{
				Reader reader = Resources.getResourceAsReader("MybatisConfig.xml");
				Properties dbProperties = getDBConnProperties();
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, dbProperties);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				OUT.error("Exception", e);
			}
		}
	}

	private Properties getDBConnProperties()
	{
		Properties props = new Properties();
		try
		{
			InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("databaseconnection.properties");
			props.load(resourceAsStream);

			List<String> encryptedValueKeys = new ArrayList<String>();
			encryptedValueKeys.add("driver");
			encryptedValueKeys.add("url");
			encryptedValueKeys.add("user");
			encryptedValueKeys.add("password");

			if (props.getProperty("encrypted") == null || props.getProperty("encrypted").equalsIgnoreCase("true"))
			{
				for (Object key : props.keySet())
				{
					if (props.getProperty((String) key) != null && encryptedValueKeys.contains((String) key))
					{
//						props.put(key, AESCipher.decrypt(props.getProperty((String) key)));
					}
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return props;
	}

	public String getDBName()
	{
		String dbName = "";
		try
		{
			String urlString = (String) getDBConnProperties().get("url");
			dbName = urlString.split("/")[3];
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return dbName;
	}

	/**
	 * @return
	 */
	public static MyBatisManager getInstance()
	{
		return dbManager;
	}

	/**
	 * * To add a new IModel Object in DB, returns int with ID of newly inserted row
	 * 
	 * @param modelObject
	 * @param queryName
	 * @return
	 * @throws SQLException
	 */
	public int insert(String queryName, IModel modelObject) throws Exception
	{
		Integer pkId = null;
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			pkId = sqlSession.insert(queryName, modelObject);
			sqlSession.commit();
		}
		catch (Exception e)
		{
			if (sqlSession != null)
				sqlSession.rollback();
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
		return pkId;
	}

	/**
	 * @param modelObject
	 * @param queryName
	 * @return
	 * @throws SQLException
	 */
	public int update(String queryName, IModel modelObject) throws Exception
	{
		SqlSession sqlSession = null;
		int updateStatus = 0;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			updateStatus = sqlSession.update(queryName, modelObject);
			sqlSession.commit();
		}
		catch (Exception e)
		{
			if (sqlSession != null)
				sqlSession.rollback();
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
		return updateStatus;
	}

	/**
	 * @param object
	 * @param queryName
	 * @throws SQLException
	 */
	public int update(String queryName, Object object) throws Exception
	{
		SqlSession sqlSession = null;
		int updateStatus = 0;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			updateStatus = sqlSession.update(queryName, object);
			sqlSession.commit();
		}
		catch (Exception e)
		{
			if (sqlSession != null)
				sqlSession.rollback();
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
		return updateStatus;
	}

	/**
	 * @param modelObject
	 * @param queryName
	 * @throws SQLException
	 */
	public void deleteObjectById(String queryName, int id) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.delete(queryName, id);
			sqlSession.commit();
		}
		catch (Exception e)
		{
			if (sqlSession != null)
				sqlSession.rollback();
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	public void deleteObjectById(String queryName, Long id) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.delete(queryName, id);
			sqlSession.commit();
		}
		catch (Exception e)
		{
			if (sqlSession != null)
				sqlSession.rollback();
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param modelObject
	 * @param queryName
	 * @return
	 * @throws SQLException
	 */
	public Integer getUniqueCount(String queryName, Object modelObject) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return (Integer) sqlSession.selectOne(queryName, modelObject);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	public Integer getUniqueCount(String queryName, int id) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return (Integer) sqlSession.selectOne(queryName, id);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	public Integer getUniqueCount(String queryName, long id) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return (Integer) sqlSession.selectOne(queryName, id);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param queryName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object getResultAsObjectById(String queryName, int id) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return (Object) sqlSession.selectOne(queryName, id);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param queryName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object getResultAsObjectById(String queryName, long id) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return (Object) sqlSession.selectOne(queryName, id);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param queryName
	 * @param parametersObject
	 * @return
	 * @throws Exception
	 */
	public IModel getResultAsObject(String queryName, IModel parametersObject) throws Exception
	{
		SqlSession sqlSession = null;
		long startTime = System.currentTimeMillis();
		OUT.debug("Executing Ibatis Query : {} param: {}", queryName, parametersObject);
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return (IModel) sqlSession.selectOne(queryName, parametersObject);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			OUT.debug("Ibatis Query : {} executed in: {} ms", queryName, (System.currentTimeMillis() - startTime));
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	public Object getResultAsObject(String queryName, Object parametersObject) throws Exception
	{
		SqlSession sqlSession = null;
		long startTime = System.currentTimeMillis();
		OUT.debug("Executing Ibatis Query : {} param: {}", queryName, parametersObject);
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.selectOne(queryName, parametersObject);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			OUT.debug("Ibatis Query : {} executed in: {} ms", queryName, (System.currentTimeMillis() - startTime));
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param <T>
	 * @param queryName
	 * @param parameterObject
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> getResultAsList(String queryName, T parameterObject) throws Exception
	{
		return getResultAsList(queryName, parameterObject, 0, 0);
	}

	/**
	 * @param <T>
	 * @param queryName
	 * @param parameterObject
	 * @param skipResults
	 * @param maxResults
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> getResultAsList(String queryName, T parameterObject, int skipResults, int maxResults) throws Exception
	{
		SqlSession sqlSession = null;
		long startTime = System.currentTimeMillis();
		OUT.debug("Executing Ibatis Query : {} param: {}", queryName, parameterObject);
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			if (skipResults < 1)
			{
				skipResults = RowBounds.NO_ROW_OFFSET;
			}
			if (maxResults < 1)
			{
				maxResults = RowBounds.NO_ROW_LIMIT;
			}

			RowBounds bounds = new RowBounds(skipResults, maxResults);
			return sqlSession.selectList(queryName, parameterObject, bounds);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			OUT.debug("Ibatis Query : {} executed in: {} ms", queryName, (System.currentTimeMillis() - startTime));
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param queryName
	 * @param parametersObject
	 * @return
	 * @throws SQLException
	 */
	public <T> List<T> getResultList(String queryName, Object parameterObject) throws Exception
	{
		return getResultList(queryName, parameterObject, 0, 0);
	}

	/**
	 * @param queryName
	 * @param parameterObject
	 * @param skipResults
	 * @param maxResults
	 * @return
	 * @throws SQLException
	 */
	public <T> List<T> getResultList(String queryName, Object parameterObject, int skipResults, int maxResults) throws Exception
	{
		List<T> resultList = new ArrayList<T>();
		long startTime = System.currentTimeMillis();
		OUT.debug("Executing Ibatis Query : {} param: {}", queryName, parameterObject);
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			if (sqlSession == null)
			{
				System.out.println("sql Session is null");
			}
			else
			{
			}
			if (skipResults < 1)
				skipResults = RowBounds.NO_ROW_OFFSET;
			if (maxResults < 1)
				maxResults = RowBounds.NO_ROW_LIMIT;

			RowBounds bounds = new RowBounds(skipResults, maxResults);

			resultList = sqlSession.selectList(queryName, parameterObject, bounds);
		}
		catch (Exception e)
		{
			OUT.error("Exception ", e);
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
		OUT.debug("Ibatis Query : {} executed in: {} ms", queryName, (System.currentTimeMillis() - startTime));
		return resultList;
	}

	/**
	 * @return
	 */
	public SqlSession getSession()
	{
		return sqlSessionFactory.openSession();
	}

	/**
	 * @return
	 */
	public SqlSession getBatchSession()
	{
		return sqlSessionFactory.openSession(ExecutorType.BATCH);
	}

	public <T> void getResultByHandler(String queryName, T parameterObject, ResultHandler handler) throws Exception
	{
		getResultByHandler(queryName, parameterObject, handler, 0, 0);
	}

	public <T> void getResultByHandler(String queryName, T parameterObject, ResultHandler handler, int skipResults, int maxResults) throws Exception
	{
		SqlSession sqlSession = null;
		long startTime = System.currentTimeMillis();
		OUT.debug("Executing Ibatis Query : {} param: {}", queryName, parameterObject);
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			if (skipResults < 1)
				skipResults = RowBounds.NO_ROW_OFFSET;
			if (maxResults < 1)
				maxResults = RowBounds.NO_ROW_LIMIT;

			RowBounds bounds = new RowBounds(skipResults, maxResults);
			sqlSession.select(queryName, parameterObject, bounds, handler);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			OUT.debug("Ibatis Query : {} executed in: {} ms", queryName, (System.currentTimeMillis() - startTime));
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param modelObject
	 * @param queryName
	 * @throws SQLException
	 */
	public void deleteObjectByModel(String queryName, IModel modelObject) throws Exception
	{
		SqlSession sqlSession = null;
		long startTime = System.currentTimeMillis();
		OUT.debug("Executing Ibatis Query : {} param: {}", queryName, modelObject);
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.delete(queryName, modelObject);
			sqlSession.commit();
		}
		catch (Exception e)
		{
			if (sqlSession != null)
				sqlSession.rollback();
			throw e;
		}
		finally
		{
			OUT.debug("Ibatis Query : {} executed in: {} ms", queryName, (System.currentTimeMillis() - startTime));
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}
}

