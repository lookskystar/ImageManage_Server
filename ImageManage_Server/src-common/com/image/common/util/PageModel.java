package com.image.common.util;

import java.io.Serializable;
import java.util.List;
/**
 * 处理分页的类
 * @author Administrator
 *
 */
public class PageModel<T> implements Serializable {
	
	private static final long serialVersionUID = 2108296080987745664L;
	/**
	 * 查询出来的记录总数
	 */
	private int count;
	/**
	 * 每页显示的数据
	 */
	private List<T> datas;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
}
