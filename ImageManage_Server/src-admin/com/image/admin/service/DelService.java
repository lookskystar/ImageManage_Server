package com.image.admin.service;

import java.text.SimpleDateFormat;
import java.util.List;

import com.image.common.pojo.DelRec;
import com.image.common.util.PageModel;

public interface DelService {

	public final static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	/**
	 * 查询删除操作记录
	 * */
	public PageModel<DelRec> findDelList();

	/**
	 * 删除记录
	 * */
	public void deleteRec(String btime, String etime, long areaId);
	
	/**
	 * 分页查询DevicesInfo信息
	 * @param pageSize
	 * @param startRow
	 * @return
	 */
	public List<DelRec> findDelRecPage(int pageSize, int startRow);
	
	/**
	 * 得到总记录数
	 * @return
	 */
	public int getDelRecCount();
}
