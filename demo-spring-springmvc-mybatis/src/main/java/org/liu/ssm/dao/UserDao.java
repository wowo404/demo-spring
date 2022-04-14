package org.liu.ssm.dao;

import org.apache.ibatis.annotations.Param;
import org.liu.ssm.model.User;

public interface UserDao {
    User getById(@Param("id") Long id);
}
