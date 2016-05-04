/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/6/24 15:26:04                           */
/*==============================================================*/


drop table if exists Article;

drop table if exists Role;

drop table if exists menu;

drop table if exists role_menu;

drop table if exists user;

/*==============================================================*/
/* Table: Article                                               */
/*==============================================================*/
create table Article
(
   ac_id                bigint not null auto_increment,
   user_id              bigint not null,
   title                varchar(300),
   content              text,
   up_time              date,
   read_num             bigint,
   primary key (ac_id)
);

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role
(
   role_id              bigint not null auto_increment,
   role_name            varchar(100),
   role_description     varchar(300),
   primary key (role_id)
);

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   menu_id              bigint not null auto_increment,
   menu_name            varchar(100),
   menu_url             varchar(300),
   fa_id                bigint,
   primary key (menu_id)
);

/*==============================================================*/
/* Table: role_menu                                             */
/*==============================================================*/
create table role_menu
(
   role_id              bigint not null,
   menu_id              bigint not null,
   primary key (role_id, menu_id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              bigint not null auto_increment,
   role_id              bigint not null,
   user_name            varchar(100),
   user_password        varchar(100),
   user_description     varchar(512),
   build_time           date,
   primary key (user_id)
);

alter table Article add constraint FK_user_article foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table role_menu add constraint FK_role_menu foreign key (role_id)
      references Role (role_id) on delete restrict on update restrict;

alter table role_menu add constraint FK_role_menu2 foreign key (menu_id)
      references menu (menu_id) on delete restrict on update restrict;

alter table user add constraint FK_Relationship_1 foreign key (role_id)
      references Role (role_id) on delete restrict on update restrict;

