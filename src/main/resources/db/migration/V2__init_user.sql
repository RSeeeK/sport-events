CREATE TABLE IF NOT EXISTS main."user"
(
    id uuid NOT NULL DEFAULT main.uuid_generate_v1mc(),
    employee_code text NOT NULL,
    password text,
    first_name text,
    middle_name text,
    last_name text,
    image_url text,
    role text,
    CONSTRAINT pk_user_id PRIMARY KEY (id)
);
