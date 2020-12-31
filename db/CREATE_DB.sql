CREATE DATABASE `finance`;

use `finance`;

create table family_account
(
    id   int auto_increment
        primary key,
    name varchar(10) not null comment '家庭组名称',
    constraint family_account_name_uindex
        unique (name)
)
    comment '家庭表';

create table account
(
    id         int auto_increment comment '用户ID'
        primary key,
    family_id  int                  null comment '家庭组Id',
    login_name varchar(10)          not null comment '登录邮箱',
    `name`       varchar(10)          not null comment '昵称',
    pass_word  varchar(10)          not null comment '登录密码',
    is_admin   tinyint(1) default 0 not null comment '是否后台管理员',
    is_super_account tinyint(1) default 0 not null comment '是否为家庭管理员
                                                        默认为FALSE
                                                        首次创建家庭的人为TRUE',
    constraint account_login_name_uindex
        unique (login_name),
    constraint account_family_account_id_fk
        foreign key (family_id) references family_account (id)
)
    comment '用户表';

create table bond_account
(
    id         int auto_increment comment '证券账户Id'
        primary key,
    account_id int         not null comment '所属用户Id',
    `name`       varchar(10) not null comment '证券账户名',

    constraint bond_account_account_id_fk
        foreign key (account_id) references account (id)
)
    comment '证券账户表';

create table bond_list
(
    id              int auto_increment comment '持股明细Id'
        primary key,
    bond_account_id int         not null comment '来源账户',
    `name`            varchar(10) not null comment '股票名称',
    constraint bond_list_bond_account_id_fk
        foreign key (bond_account_id) references bond_account (id)
)
    comment '持股明细';

create table bond_io_list
(
    id           int auto_increment
        primary key,
    bond_list_id int                  not null,
    is_buy_in    tinyint(1) default 1 not null,
    constraint bond_io_list_bond_list_id_fk
        foreign key (bond_list_id) references bond_list (id)
)
    comment '证券流水表';

create table io_list
(
    id         int auto_increment comment '收入支出ID'
        primary key,
    account_id int                        not null,
    family_id  int                        not null comment '家庭Id',
    is_output  tinyint(1)    default 1    not null comment '是否为支出',
    money      decimal(9, 2) default 0.00 not null comment '金额',
    type       int           default 1    not null comment '支出（收入）
                                                            0:税收（工资）
                                                            1:衣食住行（股票）
                                                            2:医疗（分红）
                                                            3:其他（奖金）',
    `date`       date                       not null comment '日期   直接调用数据库DATE',
    `source`     varchar(10)                null comment '来源',
    `comment`    varchar(255)               null comment '相关备注',
    constraint io_list_account_id_fk
        foreign key (account_id) references account (id),
    constraint io_list_family_account_id_fk
        foreign key (family_id) references family_account (id)
)
    comment '收入支出表';


#SELECT * FROM account a left join bond_account ba on a.id = ba.account_id left join bond_list bl on ba.id = bl.bond_account_id
 #   left join bond_io_list bil on bl.id = bil.bond_list_id;