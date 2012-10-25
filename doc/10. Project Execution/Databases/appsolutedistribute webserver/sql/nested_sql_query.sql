SELECT * 
FROM `users` 
WHERE `userid` 
IN (SELECT * FROM `users2groups` WHERE `groups_groupid`='4')
ORDER BY `userid`