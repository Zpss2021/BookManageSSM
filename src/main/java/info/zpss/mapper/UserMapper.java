package info.zpss.mapper;

import info.zpss.entity.Account;
import info.zpss.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where username = #{username}")
    Account findUserByName(String username);

    @Select("select * from student")
    List<Student> getStudentList();
}
