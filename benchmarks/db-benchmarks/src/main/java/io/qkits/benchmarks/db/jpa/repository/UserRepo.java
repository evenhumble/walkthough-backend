package io.qkits.benchmarks.db.jpa.repository;

import io.qkits.benchmarks.db.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, Integer> {
    /**
     * An interesting way to generate sql
     * it is same as use native sql
     * and naming convention
     *
     * @param code
     * @return
     */
    List<User> findByCode(String code);

    List<User> findByIdAndCode(Integer id, String code);

    @Query("select u from User u where u.code=?1")
    List<User> findByCodeByHQuery(String code);

    @Query(value = "select * from users where code = ?1", nativeQuery = true)
    List<User> findByCodeByNativeQuery(String code);

}

