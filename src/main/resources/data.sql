drop table if exists users;
drop table if exists company;

CREATE TABLE users
(
    id   integer                NOT NULL,
    name character varying(256) NOT NULL,
    group_id integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE company
(
    id   integer                  NOT NULL,
    name character varying(256)   NOT NULL,
    PRIMARY KEY (id)
);
