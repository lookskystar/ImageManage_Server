package com.image.admin.service;

import java.util.List;

import com.image.common.pojo.DictFunctions;
import com.image.common.pojo.DictRoles;
import com.image.common.pojo.RolesFunction;


public interface RolesService {

	/**
	 * 保存角色
	 */
	public void saveRole(DictRoles dictroles);

	/**
	 * 根据角色ID 删除角色
	 */
	public String deleteRole(long roleID);

	/**
	 * 查找角色下是否存在用户
	 */
	public long countUsers(long roleID);

	/**
	 * 根据角色ID查找角色
	 */
	public DictRoles getDictRolesById(long roleID);
	
	/**
	 * 根据角色名字查找角色
	 */
	public DictRoles getDictRolesByName(String roleName);

	/**
	 * 修改角色
	 */
	public void updateRole(DictRoles dictroles);
	
	/**
	 * 查询所有角色
	 * */
	public List<DictRoles> listDictRoles();
	
	/**
	 * 列出主功能列表
	 * */
	public List<DictFunctions> listMainFunctionPrivs();
	
	/**
	 * 列出次功能列表
	 * */
	public List<DictFunctions> listSecFunctionPrivs();
	
	/**
	 * 通过ID查询功能名
	 * */
	public String findFunnameById(Long id);
	
	/**
	 * 保存角色功能表,将角色与功能关联
	 */
	public void saveRolesFunction(String[] ids,Long roleID);
	
	/**
	 * 根据角色ID列出角色功能表
	 * */
	public List<RolesFunction> getRolesFunctionById(long roleID);
	
	public void deleteRoleFunPrivsOfSQL(long roleID);
		
    public List<RolesFunction> getRolesFunctionByIdOfSQL(long roleID);
    
    public List<Object> getFuncIdByIdOfSQL(long roleID);
    
    public List<Object> listSecFuncIdOfFunctionPrivs();
    
    Object getFuncNameById(long funcId);
    
}
