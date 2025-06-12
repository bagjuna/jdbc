package org.scoula.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckedTest {

    /**
     *
     */
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }


    /**
     * Checked 예외는
     */
    static class Service {
        Repository repository = new Repository();

        public void callThrow() throws MyCheckedException {
            Repository repository = new Repository();
            repository.call();
        }

        /**
         * 예외를 잡아서 처리하는 경우
         */
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                log.info("예외 처리, message={}", e.getMessage());

            }

        }

    }
    static class Repository {

        public void call() throws MyCheckedException {
            log.info("Repository 호출");
            throw new MyCheckedException("ex");
        }
    }
}

