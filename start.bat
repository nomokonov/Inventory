chcp 1251 >nul
SET CONFIG_LDAPDOMAIN=nvm.local
SET CONFIG_LDAPURL=ldap://192.168.122.128:389
SET CONFIG_LDAPSEARCHBASE=ou=ATB,dc=nvm,dc=local
SET CONFIG_LDAPADMINGROUP=Inventory_admins
SET CONFIG_LDAPUSERGROUP=Inventory_users
SET CONFIG_LDAPFILTER=^(^|^(memberOf=cn=Inventory_admins,ou=Группы безопасности,ou=ATB,dc=nvm,dc=local^)^(memberOf=cn=Inventory_users,ou=Группы безопасности,ou=ATB,dc=nvm,dc=local^)^)
SET CONFIG_UPLOADPATH=d:\uploads
java   -jar    target\Inventory-list.jar 