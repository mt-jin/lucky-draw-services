CREATE TABLE prize
(
    id            bigint generated by default as identity,
    lucky_draw_id bigint,
    name          varchar(25),
    description   varchar(100),
    prize_value   varchar(25),
    stock         bigint NOT NULL DEFAULT '0',
    probability   decimal,
    categories    varchar(100),
    CONSTRAINT fk_prize_lucky_draw FOREIGN KEY (lucky_draw_id)
        REFERENCES lucky_draw (id)
);
