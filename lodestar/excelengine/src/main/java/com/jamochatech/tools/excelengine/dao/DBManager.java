/*
 * @(#) DBManager.java  
 * 
 * This software is the confidential and proprietary information of
 * JaMocha Tech Private Limited ("Confidential Information").
 * You shall not disclose such 'Confidential Information' and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with JaMocha Tech Private Limited.
 *
 * @Version 1.0
 * @Date Nov 4, 2011
 * @Author srishailam.p
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */
package com.jamochatech.tools.excelengine.dao;

import java.io.InputStream;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jamochatech.tools.excelengine.dto.IModel;

public class DBManager
{
	private SqlSessionFactory			sqlSessionFactory;

	private volatile static DBManager	dbManager	= null;

	private static final Logger			OUT			= LoggerFactory.getLogger(DBManager.class.getName());

	/**
	 * 
	 */
	private DBManager()
	{
		try
		{
			String resource = "com/jamochatech/tools/excelengine/dao/MyBatisConfig.xml";
			Properties dbProperties = getDBConnProperties();
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, dbProperties);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	private Properties getDBConnProperties()
	{
		Properties props = new Properties();
		try
		{
			InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("databaseconnection.properties");
			props.load(resourceAsStream);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return props;
	}

	/**
	 * @return
	 */
	public static DBManager getInstance()
	{
		if (dbManager == null)
		{
			synchronized (DBManager.class)
			{
				if (dbManager == null)
				{
					dbManager = new DBManager();
				}
			}
		}
		return dbManager;
	}

	/**
	 * * To add a new IModel Object in DB, returns number of rows affected.
	 * 
	 * @param modelObject
	 * @param queryName
	 * @return
	 * @throws SQLException
	 */
	public int insert(String queryName, Object modelObject) throws Exception
	{
		Integer rowsAffected = null;
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			rowsAffected = sqlSession.insert(queryName, modelObject);
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
		return rowsAffected;
	}

	/**
	 * @param modelObject
	 * @param queryName
	 * @throws SQLException
	 */
	public void update(String queryName, IModel modelObject) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.update(queryName, modelObject);
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

	public void updateObject(String queryName, Object modelObject) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.update(queryName, modelObject);
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

	/**
	 * @param modelObject
	 * @param queryName
	 * @return
	 * @throws SQLException
	 */
	public Integer getUniqueCount(String queryName, IModel modelObject) throws Exception
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

	/**
	 * @param queryName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IModel getResultAsObjectById(String queryName, int id) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return (IModel) sqlSession.selectOne(queryName, id);
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
	public Map<String, Object> getResultAsMap(String queryName, Object parametersObject) throws Exception
	{
		SqlSession sqlSession = null;
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
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			if (skipResults < 1)
				skipResults = RowBounds.NO_ROW_OFFSET;
			if (maxResults < 1)
				maxResults = RowBounds.NO_ROW_LIMIT;

			RowBounds bounds = new RowBounds(skipResults, maxResults);
			return sqlSession.selectList(queryName, parameterObject, bounds);
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
		OUT.debug("Executing Ibatis Query : " + queryName + "; " + parameterObject);
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
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
		OUT.debug("Ibatis Query : " + queryName + " executed in " + (System.currentTimeMillis() - startTime) + " ms");
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
	public boolean isServerReachable()
	{
		/* to do - implement a better way to check availability */

		return true;
	}

	/**
	 * @param parameterObject
	 * @param queryName
	 * @throws SQLException
	 */
	public void deleteObjectByModel(String queryName, Object parameterObject) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.delete(queryName, parameterObject);
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
	 * @throws SQLException
	 */
	public void deleteObjectByModel(String queryName, IModel modelObject) throws Exception
	{
		SqlSession sqlSession = null;
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
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	public Integer getUniqueCount(String queryName, Map<String, Object> paramMap) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return (Integer) sqlSession.selectOne(queryName, paramMap);
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

	public Long getSumOfCount(String queryName, Map<String, Object> paramMap) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return (Long) sqlSession.selectOne(queryName, paramMap);
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


	public Long getId(String queryName, Map<String, Object> paramMap) throws Exception
	{
		return getSumOfCount(queryName, paramMap);
	}

	
	
	public Map<String, Object> getResultAsMap(String queryName, Map<String, Object> paramMap, String mapKey) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.selectMap(queryName, paramMap, mapKey);
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
}
