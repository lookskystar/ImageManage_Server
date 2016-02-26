package com.image.common.util;

import java.io.Serializable;

public class PagerHelper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1764209092777246703L;
	private static Pager pager;
	/**
	 * 分页辅助类
	 * @param currentPage:当前页
	 * @param pagerMethod:要执行的分页方法
	 * @param totalRows:总记录数
	 * @return
	 */
	public static Pager getPager(String currentPage,String pagerMethod,
			int totalRows) {

		// 定义pager对象，用于传到页面
//		if(pager==null){
//			pager = new Pager(totalRows);
//		}
		pager = new Pager(totalRows);
		// 如果当前页号为空，表示为首次查询该页
		// 如果不为空，则刷新pager对象，输入当前页号等信息
		if (currentPage != null) {
			pager.refresh(Integer.parseInt(currentPage));
		}

		if (pagerMethod != null) {
			if (pagerMethod.equals("first")) {
				pager.first();
			} else if (pagerMethod.equals("previous")) {
				pager.previous();
			} else if (pagerMethod.equals("next")) {
				pager.next();
			} else if (pagerMethod.equals("last")) {
				pager.last();
			}
		}
		return pager;
	}
}