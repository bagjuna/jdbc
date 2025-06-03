# set autocommit = 0; # 수동 커밋 모드로 설정
# insert into member(member_id, money) values ('data3',10000);
# insert into member(member_id, money) values ('data4',20000);
# commit; # 커밋


# set lock_wait_timeout  = 60000; # 60초 동안 락 대기
# set autocommit = 0; # 수동 커밋 모드로 설정
# update member set money = 1000 where member_id = 'memberA';
commit ;
select * from member;