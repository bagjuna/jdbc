package org.scoula.jdbc.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.scoula.jdbc.domain.Member;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    @DisplayName("1. CRUD 테스트")
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

    @Test
    @DisplayName("2. create 테스트")
    void create() throws SQLException {
        Member member = new Member("memberV1", 10000);
        Member savedMember = repository.save(member);
        assertNotNull(savedMember.getMemberId());
        assertEquals(member.getMoney(), savedMember.getMoney());
        Assertions.assertThat(savedMember).isEqualTo(member);
    }

    @Test
    @DisplayName("3. read 테스트")
    void read() throws SQLException {
        Member findMember = repository.findById("memberV1");
        assertNotNull(findMember);
        assertEquals("memberV1", findMember.getMemberId());
        Assertions.assertThat(findMember.getMoney()).isEqualTo(10000);
    }

    @Test
    @DisplayName("4. update 테스트")
    void update() throws SQLException {
        Member member = new Member("memberV1", 10000);
        repository.update(member.getMemberId(), 20000);
        Member updatedMember = repository.findById(member.getMemberId());
        assertEquals(20000, updatedMember.getMoney());
        Assertions.assertThat(updatedMember.getMoney()).isEqualTo(20000);
    }

    @Test
    @DisplayName("5. delete 테스트")
    void delete() throws SQLException {
        repository.delete("memberV1");
        assertThrows(NoSuchElementException.class, () -> repository.findById("memberV1"));
        Assertions.assertThatThrownBy(() -> repository.findById("memberV1"))
                .isInstanceOf(NoSuchElementException.class);
    }

}