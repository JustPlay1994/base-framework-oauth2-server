
/*=============== DDL ========================*/
create table users(
	username varchar2(50) not null primary key,
	password varchar2(50) not null,
	enabled Number(1) not null,
	accountNonExpired NUMBER(1) not null,
	credentialsNonExpired NUMBER(1) not null
);
comment on TABLE users is '用户认证表';
comment on column users.username is '用户名';
comment on column users.password is '密码';
comment on column users.enabled is '是否有效,1有效,0被禁用';
comment on column users.accountNonExpired is '账号是否有效,1账号有效,0账号无效';
comment on column users.credentialsNonExpired is '凭证是否有效,1凭证有效,0凭证无效';

create table authorities (
	username varchar2(50) not null,
	authority varchar2(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

comment on table authorities is '用户权限表';
comment on column authorities.username is '用户名';
comment on column authorities.authority is'拥有的权限名';

