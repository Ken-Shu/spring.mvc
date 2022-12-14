package spring.mvc.session12.repository;

import java.util.List;

import spring.mvc.session12.entity.Employee;

public interface EmployeeDao {
	// 每頁筆數
	int LIMIT = 5;
	
	// 新增
	int add(Employee employee);
	
	// 修改
	int update(Employee employee);
	
	// 刪除
	int delete(Integer eid);
	
	// 查詢所有筆數
	int getcount();
	// 查詢單筆資料
	Employee getById(Integer eid);
	
	// 查詢所有資料 (不分頁)
	List<Employee> query();
	
	// 查詢所有資料 (分頁查詢) offset 表示要從哪一筆開始查 查 LIMIT 筆
	List<Employee> queryPage(int offset);
}
