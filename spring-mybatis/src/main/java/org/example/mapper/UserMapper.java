package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.User;

import java.util.List;

/**
 * @author chenj
 */
@Mapper
public interface UserMapper {

    User getUserById(Long id);

    List<User> getUsersByRid(Long rid);

}
