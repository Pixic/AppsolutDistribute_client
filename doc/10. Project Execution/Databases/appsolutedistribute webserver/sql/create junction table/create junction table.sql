CREATE TABLE `users2groups` (
  `users_userid` INT UNSIGNED NOT NULL ,
  `groups_groupid` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`users_userid`, `groups_groupid`) ,
  INDEX `fk_user_has_group_group` (`groups_groupid` ASC) ,
  INDEX `fk_user_has_group_user` (`users_userid` ASC) ,
  CONSTRAINT `fk_user_has_group_user`
    FOREIGN KEY (`users_userid` )
    REFERENCES `users` (`userid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_group_group`
    FOREIGN KEY (`groups_groupid` )
    REFERENCES `groups` (`groupid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);