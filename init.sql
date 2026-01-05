-- public.sys_dict definition

-- Drop table

-- DROP TABLE public.sys_dict;

CREATE TABLE public.sys_dict (
	id varchar(32) NOT NULL,
	dict_name varchar(64) NULL,
	dict_code varchar(64) NULL,
	description varchar(128) NULL,
	create_by varchar(32) NULL,
	create_time timestamp NULL,
	update_by varchar(32) NULL,
	update_time timestamp NULL,
	tenementguid varchar(36) NULL
);
CREATE UNIQUE INDEX sys_dict_id_idx ON public.sys_dict USING btree (id);


-- public.sys_dict_item definition

-- Drop table

-- DROP TABLE public.sys_dict_item;

CREATE TABLE public.sys_dict_item (
	id varchar(32) NOT NULL,
	dict_id varchar(32) NULL,
	item_text varchar(100) NULL,
	item_value varchar(100) NULL,
	description varchar(255) NULL,
	sort_order int2 NULL,
	status varchar(10) NULL,
	create_by varchar(32) NULL,
	create_time timestamp NULL,
	update_by varchar(32) NULL,
	update_time timestamp NULL
);
CREATE UNIQUE INDEX sys_dict_item_id_idx ON public.sys_dict_item USING btree (id);


-- public.sys_log definition

-- Drop table

-- DROP TABLE public.sys_log;

CREATE TABLE public.sys_log (
	id varchar(32) NOT NULL,
	log_type varchar(1) NULL,
	log_content varchar(100) NULL,
	operate_type varchar(1) NULL,
	user_id varchar(100) NULL,
	user_name varchar(100) NULL,
	ip varchar(32) NULL,
	"method" varchar(500) NULL,
	request_url varchar(225) NULL,
	request_param varchar(500) NULL,
	request_type varchar(10) NULL,
	cost_time numeric(18) NULL,
	create_by varchar(32) NULL,
	create_time timestamp NULL,
	update_by varchar(32) NULL,
	update_time timestamp NULL,
	tenementguid varchar(36) NULL
);
CREATE UNIQUE INDEX sys_log_id_idx ON public.sys_log USING btree (id);