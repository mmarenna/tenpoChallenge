CREATE TABLE public.histories (
	id bigserial NOT NULL,
	"date" timestamp NULL,
	response varchar(255) NULL,
	uri varchar(255) NULL,
	CONSTRAINT histories_pkey PRIMARY KEY (id)
);

CREATE TABLE public.roles (
	id bigserial NOT NULL,
	"name" varchar(30) NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id),
	CONSTRAINT uk_ofx66keruapi6vyqpv6f2or37 UNIQUE (name)
);

CREATE TABLE public.users (
	id bigserial NOT NULL,
	email varchar(255) NULL,
	"password" varchar(60) NULL,
	username varchar(20) NULL,
	CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username),
	CONSTRAINT ukfnranlqhubvw04boopn028e6 UNIQUE (username, email),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE public.user_roles (
	user_id int8 NOT NULL,
	role_id int8 NOT NULL,
	CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id)
);


-- public.user_roles foreign keys

ALTER TABLE public.user_roles ADD CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id);
ALTER TABLE public.user_roles ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id);

-- insert roles
INSERT INTO roles ("name") VALUES('ROLE_USER');
INSERT INTO roles ("name") VALUES('ROLE_ADMIN');
