CREATE TABLE `admin2groups` (
  `users_adminid` INT UNSIGNED NOT NULL ,
  `groups_groupid` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`users_adminid`, `groups_groupid`) ,
  INDEX `fk_admin_has_group_group` (`groups_groupid` ASC) ,
  INDEX `fk_admin_has_group_admin` (`users_adminid` ASC) ,
  CONSTRAINT `fk_admin_has_group_admin`
    FOREIGN KEY (`users_adminid` )
    REFERENCES `users` (`userid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_admin_has_group_group`
    FOREIGN KEY (`groups_groupid` )
    REFERENCES `groups` (`groupid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);