package org.example.mapper;

import org.example.model.User;

import java.util.List;

/**
 * @author chenj
 */
public interface UserMapper {

    User getUserById(Long id);

    List<User> getUsersByRid(Long rid);

}
