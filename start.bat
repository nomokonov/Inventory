chcp 1251 >nul
SET CONFIG_LDAPFILTER==(memberOf=cn=Access_Disable,ou=Группы безопасности,ou=ATB,dc=nvm,dc=local)
java   -jar    target\authenticating-ldap-0.0.1-SNAPSHOT.jar 