create table brand
(
	id serial not null
		constraint brand_pkey
			primary key,
	name varchar not null
);

alter table brand owner to postgres;

create table car
(
	id serial not null
		constraint car_pkey
			primary key,
	brandid integer
		constraint car_brandid_fkey
			references brand,
	color varchar not null,
	createdate date not null,
	price integer
);

alter table car owner to postgres;

create table region
(
	id_region serial not null
		constraint region_pkey
			primary key,
	name_region varchar
);

alter table region owner to postgres;

create table brand_package
(
	id_brand_package serial not null
		constraint brand_package_pkey
			primary key,
	name_brand_package varchar
);

alter table brand_package owner to postgres;

create table retail_chains
(
	id_retail_chains serial not null
		constraint retail_chains_pkey
			primary key,
	name_retail_chains varchar,
	region_id integer
		constraint retail_chains_region_id_fkey
			references region,
	commercial_terms double precision
);

alter table retail_chains owner to postgres;

create table product
(
	id_product serial not null
		constraint product_pkey
			primary key,
	name_product varchar,
	brand_package_id integer
		constraint product_brand_package_id_fkey
			references brand_package,
	prime_cost numeric(2),
	price numeric(2)
);

alter table product owner to postgres;

create table logistic
(
	id_logistic serial not null
		constraint logistic_pkey
			primary key,
	region_id integer
		constraint logistic_region_id_fkey
			references region,
	brand_package_id integer
		constraint logistic_brand_package_id_fkey
			references brand_package,
	delivery_cost double precision
);

alter table logistic owner to postgres;

create table request_action
(
	id_request_action serial not null
		constraint request_action_pkey
			primary key,
	retail_chain_id integer
		constraint request_action_retail_chain_id_fkey
			references retail_chains,
	product_id integer
		constraint request_action_product_id_fkey
			references product,
	start_date date,
	end_date date,
	discount double precision,
	method_discount varchar,
	action_cost integer
);

alter table request_action owner to postgres;

create table seasonality
(
	id_seasonality serial not null
		constraint seasonality_pkey
			primary key,
	month integer,
	brand_package_id integer
		constraint seasonality_brand_package_id_fkey
			references brand_package,
	coefficient double precision
);

alter table seasonality owner to postgres;

create table "user"
(
	id_user serial not null
		constraint user_pkey
			primary key,
	login varchar,
	password varchar,
	user_role varchar
);

alter table "user" owner to postgres;

create table note
(
	id serial not null
		constraint note_pkey
			primary key,
	description varchar not null,
	created_time timestamp not null
);

alter table note owner to postgres;

