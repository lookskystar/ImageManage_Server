package com.image.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.image.admin.dao.DelDao;
import com.image.common.pojo.DelRec;
import com.image.common.util.AbstractDao;
import com.image.common.util.PageModel;

public class DelDaoImpl extends AbstractDao implements DelDao {

	@SuppressWarnings("unchecked")
	public PageModel<DelRec> findDelList() {
		return findPageModel("from DelRec d order by d.delTime desc");
	}

	public int  deleteJc(String btime, String etime, long areaId) {
		String hql = "delete from JcRec j where j.taskDate >= ? and j.taskDate <= ? and j.areaId=?";
		return getSession().createQuery(hql).setString(0, btime).setString(1, etime)
				.setLong(2, areaId).executeUpdate();
	}

	public int deleteProcedureInfoRec(String btime, String etime, long areaId) {
		String sql = "delete from procedure_inforec p where p.jcrec_id in (select j.jc_recid from jc_rec j where j.task_date >=? and j.task_date <=? and j.area_id=?)";
		return getSession().createSQLQuery(sql).setString(0, btime)
				.setString(1, etime).setLong(2, areaId).executeUpdate();
	}

	public int deleteTakedetailRec(String btime, String etime, long areaId) {
		String sql = "delete from take_detail_rec t where t.pro_id in(select a.prorec_id from procedure_inforec a where a.jcrec_id in(select b.jc_recid from jc_rec b where b.task_date>=? and b.task_date<=? and b.area_id=?))";
		return getSession().createSQLQuery(sql).setString(0, btime)
				.setString(1, etime).setLong(2, areaId).executeUpdate();
	}

	public void save(DelRec delRec) {
		getHibernateTemplate().save(delRec);
	}

	@SuppressWarnings("unchecked")
	public List<DelRec> findDelRecPage(int pageSize, int startRow) {
		StringBuilder builder=new StringBuilder();
		builder.append("  from DelRec d order by d.delTime desc");
		Query query=getSession().createQuery(builder.toString());
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		return query.list();
	}

	public int getDelRecCount() {
		StringBuilder builder=new StringBuilder();
		builder.append("select count(r.id) from DelRec r ");
		Object obj=getSession().createQuery(builder.toString()).uniqueResult();
		return Integer.parseInt(obj+"");
	}
}
