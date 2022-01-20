package com.edu3tospring.domain.notice;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    @Query("SELECT n FROM Notice n where n.title like %:title% order by n.created_at desc")
    public List<Notice> findByTitle(@Param("title") String title);
}
