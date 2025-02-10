package com.example.schedulemanagerdevelop.repository;

import com.example.schedulemanagerdevelop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByUsername(String username);
    Optional<Member> findByEmail(String email);

    default Member findMemberByUsernameOrElseThrow(String username) {
        return findMemberByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists username = " + username));
    }

    default Member findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id));
    }

    default Member findByEmailOrElseThrow(String email) {
        return findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists email = " + email));
    }
}
