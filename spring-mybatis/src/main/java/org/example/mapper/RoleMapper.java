package org.example.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.Role;


import java.util.List;

/**
 * 根据映射文件使用动态代理生成实体类
 * 普通增删改查demo
 */
@Mapper
public interface RoleMapper {

     int insertRole(Role role);
     int deleteRole(Long id);
     int updateRole(Role role);
     Role getRole(Long id);
    // List<Role> findRoles(PageParams pageParams, @Param("roleName") String roleName);

     List<Role> findRolesByUid(Long uid);

     List<Role> findRolesByIds(@Param("ids") List<Long> ids);


}
