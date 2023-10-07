create table applications(
    id serial primary key,
    full_enrollee_name varchar(40),
    full_boss_name varchar(40),
    department varchar(40),
    current_position varchar(40),
    experience_month bigint,
    achievements text,
    accepted boolean
)