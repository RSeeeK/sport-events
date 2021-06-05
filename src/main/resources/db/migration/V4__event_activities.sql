CREATE TABLE IF NOT EXISTS main.user_event
(
    event_id uuid NOT NULL,
    user_id uuid NOT NULL,
    participation_type text,
    approved boolean,
    CONSTRAINT fk_event_user_event_event_id FOREIGN KEY (event_id)
        REFERENCES main.event (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT fk_event_user_user_user_id FOREIGN KEY (user_id)
        REFERENCES main."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

CREATE TABLE IF NOT EXISTS main.user_activity
(
    id uuid NOT NULL DEFAULT main.uuid_generate_v1mc(),
    user_id uuid NOT NULL,
    type text,
    activity_date timestamp without time zone,
    image_url text,
    CONSTRAINT pk_user_activity_id PRIMARY KEY (id),
    CONSTRAINT fk_user_activity_user FOREIGN KEY (user_id)
        REFERENCES main."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);