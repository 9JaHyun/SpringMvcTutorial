package com.edu3tospring.domain.reply;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    @Query("SELECT r from Reply r where r.bulletin = :id order by r.created_at desc")
    public List<Reply> findByBulletinId(@Param("id") Integer id);

    @Query("SELECT r from Reply r where r.notice = :id order by r.created_at desc")
    public List<Reply> findByNoticeId(@Param("id") Integer id);
}
