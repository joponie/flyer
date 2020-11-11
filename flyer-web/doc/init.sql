CREATE TABLE `user`
(
    `id`          int(11) unsigned    NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`    varchar(64)         NOT NULL COMMENT '用户名',
    `password`    varchar(64)         NOT NULL COMMENT '密码',
    `mobile`      varchar(64)         NOT NULL DEFAULT '' COMMENT '手机号',
    `create_by`   int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '创建人ID',
    `update_by`   int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '更新人ID',
    `create_time` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delFlg`      tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记：0=未删除；1=已删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户';