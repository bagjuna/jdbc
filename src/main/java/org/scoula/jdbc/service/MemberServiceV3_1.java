package org.scoula.jdbc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.jdbc.domain.Member;
import org.scoula.jdbc.repository.MemberRepositoryV2;
import org.scoula.jdbc.repository.MemberRepositoryV3;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_1 {

    private final MemberRepositoryV3 memberRepository;
    private final PlatformTransactionManager transactionManager;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionAttribute());
        try {
            // 비즈니스 로직
            bizLogic(fromId, toId, money);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw new IllegalStateException(e);
        }


    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById( fromId);
        Member toMember = memberRepository.findById( toId);

        memberRepository.update(fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update( toId, toMember.getMoney() + money);
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
        }

    }

}
