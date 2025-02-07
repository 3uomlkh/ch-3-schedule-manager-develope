package com.example.schedulemanagerdevelop.repository;

import com.example.schedulemanagerdevelop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
