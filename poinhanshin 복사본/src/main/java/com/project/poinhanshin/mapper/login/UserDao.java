package com.project.poinhanshin.mapper.login;


import com.project.poinhanshin.domain.member.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    void save(User user);
    User findByLoginId(String id);
    User login(@Param("id") String id, @Param("password") String password);
}