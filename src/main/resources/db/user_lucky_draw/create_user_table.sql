CREATE TABLE "user"
(
    user_id        bigint primary key generated by default as identity,
    user_name      varchar(25),
    password       varchar(100)
);
