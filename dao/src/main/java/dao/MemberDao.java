package dao;

import domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MemberDao {
    @Select("select * from member where id=#{id}")
    Member findById(String id) throws Exception;
}
