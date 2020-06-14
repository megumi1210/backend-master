package org.example.mapper;


import org.example.model.Role;

import java.util.List;

/**
 * 根据映射文件使用动态代理生成实体类
 * 普通增删改查demo
 */
public interface RoleMapper {

     int insertRole(Role role);
     int deleteRole(Long id);
     int updateRole(Role role);
     Role getRole(Long id);
     List<Role> findRoles(String roleName);

}
