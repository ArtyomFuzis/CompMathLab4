package com.fuzis.compmathlab4.Repos;

import com.fuzis.compmathlab4.Entities.UserStat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatRepo extends CrudRepository<UserStat, Integer> {
    List<UserStat> getUserStatByUserName(String userName);
    List<UserStat> getUserStatsByStatBefore(Integer statBefore);
    List<UserStat> getUserStatsByStatAfter(Integer statAfter);
}
