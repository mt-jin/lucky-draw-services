CREATE TABLE user_lucky_draw
(
    id             bigint primary key generated by default as identity,
    lucky_draw_id  bigint NOT NULL,
    user_id        bigint NOT NULL,
    start_time     timestamp,
    end_time       timestamp,
    completed      boolean NOT NULL DEFAULT false,
    prize_id       bigint
);
