chcp 1251 >nul
SET CONFIG_LDAPDOMAIN=atb.su
SET CONFIG_LDAPURL=ldap://atb.su:389
SET CONFIG_LDAPSEARCHBASE=ou=ATB,dc=atb,dc=su
SET CONFIG_LDAPADMINGROUP=chita_admins
SET CONFIG_LDAPUSERGROUP=Пользователи домена
SET CONFIG_LDAPFILTER=^(^|^(memberOf=cn=chita_admins,uo=Admins,uo=ГО,ou=_ATB - Группы упрвления,dc=atb,dc=su^)^(memberOf=cn=Пользователи домена,cn=Users,dc=atb,dc=su^)^)
SET CONFIG_UPLOADPATH=d:\upload
java   -jar    target\Inventory-list.jar