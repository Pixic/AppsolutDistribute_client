CREATE TABLE `groups2features` (
  `groups_groupid` INT UNSIGNED NOT NULL ,
  `features_featureid` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`groups_groupid`, `features_featureid`) ,
  INDEX `fk_group_has_feature_feature` (`features_featureid` ASC) ,
  INDEX `fk_group_has_feature_group` (`groups_groupid` ASC) ,
  CONSTRAINT `fk_group_has_feature_group`
    FOREIGN KEY (`groups_groupid` )
    REFERENCES `groups` (`groupid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_has_feature_feature`
    FOREIGN KEY (`features_featureid` )
    REFERENCES `features` (`featureid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);