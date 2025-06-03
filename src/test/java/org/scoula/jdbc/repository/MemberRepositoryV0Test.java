package org.scoula.jdbc.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.scoula.jdbc.domain.Member;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws Exception {
        Member member = new Member("memberV5", 10000);
        // save
        Member savedMember = repository.save(member);

        // findById
        Member findMember = repository.findById(savedMember.getMemberId());
        assertEquals(savedMember.getMemberId(), findMember.getMemberId());
        Assertions.assertThat(findMember).isEqualTo(savedMember);

        // update: money: 10000 -> 20000
        repository.update(member.getMemberId(),20000);
        Member updatedMember = repository.findById(savedMember.getMemberId());
        assertEquals(updatedMember.getMoney(), 20000);
        Assertions.assertThat(updatedMember.getMoney()).isEqualTo(20000);

        // delete
        repository.delete(savedMember.getMemberId());
        assertThrows(NoSuchElementException.class, () -> repository.findById(savedMember.getMemberId()));
        Assertions.assertThatThrownBy(() -> repository.findById(savedMember.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);



    }




}