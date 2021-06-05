CREATE TABLE IF NOT EXISTS main.event
(
    id uuid NOT NULL DEFAULT main.uuid_generate_v1mc(),
    name text,
    event_date timestamp without time zone,
    registration_over boolean,
    event_limit bigint,
    description text,
    address text,
    image_url text,
    CONSTRAINT pk_event_id PRIMARY KEY (id)
);