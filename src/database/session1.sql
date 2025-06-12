# set autocommit = 1; # 자동 커밋 모드로 설정

# truncate table member;


# commit; # 커밋

# set autocommit = 1; # 수동 커밋 모드로 설정
# insert into member(member_id, money) VALUES ('memberA', 10000);

# set autocommit = 0; # 수동 커밋 모드로 설정
# update member set money = 500 where member_id = 'memberA';
# commit ;
select * from member;


