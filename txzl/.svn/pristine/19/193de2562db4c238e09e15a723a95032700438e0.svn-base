package com.tenwa.business.daoImpl;


import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.tenwa.business.dao.TableDao;
import com.tenwa.business.model.Table;
import com.tenwa.business.model.database.DBEnum;
import com.tenwa.business.model.database.DataBasePager;
import com.tenwa.business.model.database.DataBasePaging;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.WebUtil;

@Repository(value="tableDao")
public class TableDaoImpl  extends AbstractBaseDaoImpl  implements TableDao 
{
		@Resource(name="jdbcTemplate")
	    private JdbcTemplate jdbcTemplate;
		
		@Resource(name="hibernateTemplate")
		private HibernateTemplate hibernateTemplate;
		
		@Resource(name="redisTemplate")
		private RedisTemplate<String, String> redisTemplate;
		
		@SuppressWarnings("unchecked")
		@Override
		public RedisTemplate<String, String> getRedisTemplate() throws Exception {
			return this.redisTemplate;
		}
		
	   @Override
       public void getTableInfo(final Table table,final boolean isLoadTreeData) throws Exception
       {
		    if(isLoadTreeData)
		    {
		      this.getTableExportExcelInfo(table,isLoadTreeData);
			  return; 
		    }
		    
    	    DataBasePager dataBasePager = new DataBasePager(){
			@Override
			public DataBasePaging getDataBasePaging() {
				String dbType = ResourceUtil.getDBType();
				String DataBasePagingImplClassName = DBEnum.valueOf(dbType).getDBPagerClassName();
				try {
					return (DataBasePaging)Class.forName(DataBasePagingImplClassName).newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			public JdbcTemplate getJdbcTemplate() {
				try {
					return ("dataSource".equals(table.getDataSourceName())) ? TableDaoImpl.this.jdbcTemplate : (new JdbcTemplate((DataSource)WebUtil.getApplicationContext().getBean(table.getDataSourceName())));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			public int getLimit() {
				return table.getPageSize();
			}

			@Override
			public String getSourceSqlWithoutPaging() {
				return table.getTargetSql();
			}

			@Override
			public int getStart() {
				return table.getStart();
			}
			@Override
			public String getPredefinedSql() {
				return table.getPredefinedSql();
			}
			@Override
			public String getOrderColumn() {
				return table.getOrderColumn();
			}			
			@Override
			public List<String> getReplaceKeyList() {
				return table.getReplaceKeyList();
			}

			@Override
			public List<String> getReplaceValueList() {
				return table.getReplaceValueList();
			}
			@Override
			public String getExtractendSql() {
				return table.getExtractendSql();
			}
			@Override
			public String getExtractstartSql() {
					return table.getExtractstartSql();
			}

			@Override
			public String getDataAuthorityCondtion() {
				// TODO Auto-generated method stub
				return table.getDataAuthority();
			}
			
    	   };
    	   table.setTotal(dataBasePager.getPagingDatas(false));
    	   table.setDatas(dataBasePager.getDatas());
    	   table.setColumnTypesMapping(dataBasePager.getColumnTypesMapping());
    	   table.setColumnsJsonArray(dataBasePager.getColumnsJsonArray());
       }
	   @Override
       public void getTableExportExcelInfo(final Table table,final boolean isLoadTreeData) throws Exception
       {
    	    DataBasePager dataBasePager = new DataBasePager(){
    			@Override
    			public DataBasePaging getDataBasePaging() {
    				String dbType = ResourceUtil.getDBType();
    				String DataBasePagingImplClassName = DBEnum.valueOf(dbType).getDBPagerClassName();
    				try {
    					return (DataBasePaging)Class.forName(DataBasePagingImplClassName).newInstance();
    				} catch (InstantiationException e) {
    					e.printStackTrace();
    				} catch (IllegalAccessException e) {
    					e.printStackTrace();
    				} catch (ClassNotFoundException e) {
    					e.printStackTrace();
    				}
    				return null;
    			}

			@Override
			public JdbcTemplate getJdbcTemplate() {
				try {
					return ("dataSource".equals(table.getDataSourceName())) ? TableDaoImpl.this.jdbcTemplate : (new JdbcTemplate((DataSource)WebUtil.getApplicationContext().getBean(table.getDataSourceName())));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			public int getLimit() {
				return table.getPageSize();
			}

			@Override
			public String getSourceSqlWithoutPaging() {
				if(isLoadTreeData)
				{
					return table.getTargetSql();
				}
				return table.getExcelTargetSql();
			}
			@Override
			public String getPredefinedSql() {
				return table.getPredefinedSql();
			}
			@Override
			public String getOrderColumn() {
				return table.getOrderColumn();
			}		
			@Override
			public List<String> getReplaceKeyList() {
				return table.getReplaceKeyList();
			}

			@Override
			public List<String> getReplaceValueList() {
				return table.getReplaceValueList();
			}
			@Override
			public int getStart() {
				return table.getStart();
			}
			
			@Override
			public String getExtractendSql() {
				return table.getExtractendSql();
			}
			@Override
				public String getExtractstartSql() {
					return table.getExtractstartSql();
			}
			@Override
			public String getDataAuthorityCondtion() {
				// TODO Auto-generated method stub
				return table.getDataAuthority();
			}
			
    	   };
    	   table.setTotal(dataBasePager.getPagingDatas(true));
    	   table.setDatas(dataBasePager.getDatas());
    	   table.setColumnTypesMapping(dataBasePager.getColumnTypesMapping());
    	   table.setColumnsJsonArray(dataBasePager.getColumnsJsonArray());
       }
	
	@Override
	public HibernateTemplate getHibernateTemplate() throws Exception {
		return this.hibernateTemplate;
	}
	
	@Override
	public JdbcTemplate getJdbcTemplate() throws Exception {
		return this.jdbcTemplate;
	}
}
