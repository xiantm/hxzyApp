create table app_modules(
    id varchar2(40) primary key not null,
    name varchar2(100),
    image_normal varchar2(100),
    image_active varchar2(100),
    pid varchar2(40),
    ordernum number default '0'
);
comment on table app_modules is '用户模块信息表';
comment on column  app_modules.name is '模块名';
comment on column  app_modules.image_normal is '未激活图标';
comment on column  app_modules.image_active is '已激活图标';
comment on column  app_modules.ordernum is '排序号';

create table app_module_map(
   user_id varchar2(40),
   module_id varchar2(40),
   num number default '0'
)

comment on column   app_module_map.user_id is '用户ID';
comment on column   app_module_map.module_id is '模块ID';
comment on column   app_module_map.num is '当前工作数';