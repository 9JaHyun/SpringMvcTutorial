package com.edu3tospring.domain.bulletin;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BulletinRepository extends JpaRepository<Bulletin, Integer> {

    @Query("SELECT b FROM Bulletin b where b.title like %:title% order by b.createDate desc")
    public List<Bulletin> findByTitle(@Param("title") String title);
}
