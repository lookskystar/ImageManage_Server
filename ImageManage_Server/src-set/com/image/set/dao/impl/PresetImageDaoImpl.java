package com.image.set.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.image.common.pojo.DictWorktype;
import com.image.common.pojo.ProcedureInfo;
import com.image.common.pojo.ProcedureStep;
import com.image.common.util.AbstractDao;
import com.image.common.util.PageModel;
import com.image.set.dao.PresetImageDao;

public class PresetImageDaoImpl extends AbstractDao implements PresetImageDao {

	@SuppressWarnings("unchecked")
	public PageModel<ProcedureInfo> findPresetAll(String jcStype, String proName) {
		String hql = "from ProcedureInfo p where p.status = 0";
		List<Object> params = new ArrayList<Object>();
		if (jcStype != null && !jcStype.equals("")) {
			hql += " and p.jcType=?";
			params.add(jcStype);
		}
		if (proName != null && !proName.equals("")) {
			hql += " and p.proName=?";
			params.add(proName);
		}
		return findPageModel(hql, params.toArray());
	}

	@SuppressWarnings("unchecked")
	public List<String> findJcstypeAll() {
		String sql = "select distinct jcstype from dict_jcstype";
		List<String> jcStypes = this.getSession().createSQLQuery(sql).list();
		return jcStypes;
	}

	@SuppressWarnings("unchecked")
	public List<String> findProcedureAll() {
		String sql = "select distinct pro_name from procedure_info";
		List<String> pros = this.getSession().createSQLQuery(sql).list();
		return pros;
	}

	public void deletePreset(long id) {
		String hql = "update ProcedureInfo p set p.status = 1 where p.proId=?";
		getSession().createQuery(hql).setLong(0, id).executeUpdate();
	}

	public void savePreset(ProcedureInfo procedureInfo) {
		getHibernateTemplate().saveOrUpdate(procedureInfo);
	}

	public ProcedureInfo findProcedureById(long id) {
		return getHibernateTemplate().get(ProcedureInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ProcedureStep> findProcedureImageById(long id) {
		return getSession().createQuery("from ProcedureStep p where p.proId=? order by p.proSn desc")
				.setLong(0, id).list();
	}

	public void saveProcedureStep(ProcedureStep procedureStep) {
		getHibernateTemplate().save(procedureStep);
	}

	public void deleteProcedureStepById(long stepId) {
		getHibernateTemplate().delete(this.findProcedureStepById(stepId));

	}

	public ProcedureStep findProcedureStepById(long stepId) {
		return getHibernateTemplate().get(ProcedureStep.class, stepId);
	}

	@SuppressWarnings("unchecked")
	public boolean findProNum(String proNum) {
		List<ProcedureInfo> procedureInfos = getHibernateTemplate().find("from ProcedureInfo p where p.proNum = " + proNum + "");
		if (procedureInfos != null && procedureInfos.size() > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<DictWorktype> findDictWorktype() {
		return getHibernateTemplate().find("from DictWorktype");
	}

	@SuppressWarnings("unchecked")
	public Object findProcedure(long id) {
		String sql = "select * from procedure_info p ,dict_worktype d where p.pro_id = ? and p.work_id = d.workid";
		List results = getSession().createSQLQuery(sql).setLong(0, id).list();
		if (results != null && results.size() > 0) {
			Object[] obj = (Object[]) results.get(0);
			return obj;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object> findPresets(int pageSize, int startRow, String jcStype,	String proName) {
		String sql = "select * from procedure_info p,dict_worktype d where p.status = 0 and p.work_id = d.workid";
		if (jcStype != null && !jcStype.equals("")) {
			sql += " and p.jc_type='" + jcStype + "'";
		}
		if (proName != null && !proName.equals("")) {
			sql += " and p.pro_name='" + proName + "'";
		}
		Query query=getSession().createSQLQuery(sql);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		List results = query.list();
		return results;
	}

	public int findPresetsCount(String jcType, String proName) {
		StringBuilder builder = new StringBuilder();
		builder.append("select count(p.pro_id) from procedure_info p where p.status=0");
		if (jcType != null && !jcType.equals("")) {
			builder.append("and p.jc_type='" + jcType + "'");
			System.out.println(builder);
		}
		if (proName != null && !proName.equals("")) {
			builder.append("and p.pro_name='" + proName + "'");
			System.out.println(builder);
		}
		Object obj = getSession().createSQLQuery(builder.toString()).uniqueResult();
		return Integer.parseInt(obj + "");
	}
}
