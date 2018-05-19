package com.tenwa.business.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import com.tenwa.business.dao.ChartDao;
import com.tenwa.business.model.Chart;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;

@Repository(value="chartDao")
public class ChartDaoImpl  implements ChartDao 
{
   @Resource(name="jdbcTemplate")
   private JdbcTemplate jdbcTemplate;
   @Override
   public void getChartData(Chart chart) throws Exception
   {
	   if(!"dataSource".equals(chart.getDataSourceName()))
	   {
		   this.jdbcTemplate.setDataSource((DataSource)WebUtil.getApplicationContext().getBean(chart.getDataSourceName()));
	   }
	   final Chart tempchar=chart;
	   
	   List<List<String>> datas = this.jdbcTemplate.execute(StringUtil.getClearWhereSQL(chart.getTargetSql()),new PreparedStatementCallback<List<List<String>>>(){
			@Override
			public List<List<String>> doInPreparedStatement(PreparedStatement preparedstatement) throws SQLException,DataAccessException
			{
				for(int index = 0;index<tempchar.getReplaceValueList().size();index++){
					preparedstatement.setString((index+1),tempchar.getReplaceValueList().get(index));
				}
				ResultSet rs = preparedstatement.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				List<List<String>> list = new ArrayList<List<String>>();
				while(rs.next())
				{
					list.add(new ArrayList<String>());
					for(int index = 1;index<=rsmd.getColumnCount();index++)
					{
						List<String> content = (List<String>)list.get(list.size()-1);
						content.add(rs.getString(index));
					}
				}
				rs.close();
				return list;
			}
	   });
	   chart.setDatas(datas);
   }
}
