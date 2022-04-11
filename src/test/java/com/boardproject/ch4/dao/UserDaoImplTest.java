package com.boardproject.ch4.dao;

import com.boardproject.ch4.domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Test
    public void count() throws Exception{
        userDao.deleteAll();
        int count = userDao.count();
        assertTrue(count==0);
    }

    @Test
    public void insertUserTest() throws Exception {
        //given
        userDao.deleteAll();
        UserDto user = new UserDto("kiseo", "1234", "smith");

        //when
        int rowNum = userDao.insertUser(user);
        int count = userDao.count();

        //then
        assertTrue(rowNum==1);
        assertTrue(count==1);
    }

    @Test
    public void deleteUserTest() throws Exception{
        //given
        userDao.deleteAll();
        UserDto user = new UserDto("testId", "1234", "kim");
        userDao.insertUser(user);

        //when
        int rowNum = userDao.deleteUser(user);

        //then
        assertTrue(rowNum==1);
    }

    @Test
    public void updateTest() throws Exception{
        //given
        userDao.deleteAll();
        UserDto user = new UserDto("qwer", "1234", "Kim");
        userDao.insertUser(user);

        //when
        UserDto changeUser = new UserDto("qwer", "1234", "Lee");
        userDao.updateUser(changeUser);

        //then
        UserDto findUser = userDao.selectUser("qwer");
        assertTrue(findUser.getName().equals("Lee"));
    }
}
