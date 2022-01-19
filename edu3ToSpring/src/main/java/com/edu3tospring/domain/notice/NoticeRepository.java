package com.edu3tospring.domain.notice;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<NoticeJpa, Integer> {

    @Query("SELECT n FROM NoticeJpa n where n.title like %:title% order by n.createdAt desc")
    public List<NoticeJpa> findByTitle(@Param("title") String title);
}
