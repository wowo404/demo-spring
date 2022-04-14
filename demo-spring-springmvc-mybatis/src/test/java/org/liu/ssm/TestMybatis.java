package org.liu.ssm;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.liu.ssm.dao.UserDao;
import org.liu.ssm.model.User;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatis {

    @Test
    public void test() throws IOException {
        InputStream resource = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.getById(1L);
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();
        resource.close();
    }

}
