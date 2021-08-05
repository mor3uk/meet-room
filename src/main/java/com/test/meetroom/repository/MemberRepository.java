package com.test.meetroom.repository;

import com.test.meetroom.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    void deleteAllByEvent_Id(Long eventId);
}
