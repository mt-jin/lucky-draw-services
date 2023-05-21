ALTER TABLE lucky_draw
    RENAME COLUMN max_entries TO total_entry_limit;

ALTER TABLE lucky_draw
    RENAME COLUMN entry_number TO total_entry_number;

ALTER TABLE lucky_draw
    ADD COLUMN IF NOT EXISTS user_entry_limit bigint;