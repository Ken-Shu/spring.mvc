package spring.mvc.session12.repository;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import spring.mvc.session12.entity.Job;

@Repository
public class JobDaoImpl implements JobDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int add(Job job) {
		String sql = "insert into job(jname, eid) values(?,?)";
		return jdbcTemplate.update(sql,job.getJname(),job.getEid());
	}

	@Override
	public int update(Job job) {
		String sql = "update job set jname=?, eid=? where jid=?";
		return jdbcTemplate.update(sql, job.getJname(),job.getEid(),job.getJid());
	}

	@Override
	public int delete(Integer jid) {
		String sql = "delete from job where jid=?";
		return jdbcTemplate.update(sql,jid);
	}

	@Override
	public int getCount() {
		String sql = "select count(*) from job";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public Job getById(Integer jid) {
		String sql = "select jid, jname, eid from job where jid=?";
		//new BeanPropertyRowMapper<Job>(Job.class) jdbc直接將資料表抓出得欄位內容 自動對應塞入資料欄位內
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Job>(Job.class), jid);
	}

	@Override
	public List<Job> query() {
		String sql = "select j.jid, j.jname, j.eid ," +
				 	 "e.eid  as employee_eid , e.ename as employee_ename, e.salary as employee_salary, e.createtime as employee_createtime "+
				 	 "from job j left join employee e on j.eid = e.eid ";
		ResultSetExtractor<List<Job>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("jid") // Job 主表的主鍵欄位
				.newResultSetExtractor(Job.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}

	@Override
	public List<Job> queryPage(int offset) {
		String sql = "select j.jid, j.jname, j.eid ," +
			 	 "e.eid  as employee_eid , e.ename as employee_ename, e.salary as employee_salary, e.createtime as employee_createtime "+
			 	 "from job j left join employee e on j.eid = e.eid ";
		
		// 加入分頁 sql
		if(offset >= 0) {
			sql += String.format(" limit %d offset %d",LIMIT, offset);
		}
		
		ResultSetExtractor<List<Job>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("jid") // Job 主表的主鍵欄位
				.newResultSetExtractor(Job.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
		}

}
