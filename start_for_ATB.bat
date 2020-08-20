chcp 1251 >nul
SPRING_DATASOURCE_URL: jdbc:postgresql://192.168.88.62:5432/inventory
SPRING_DATASOURCE_USERNAME: inventory
SPRING_DATASOURCE_PASSWORD: 123456
SET CONFIG_LDAPDOMAIN=atb.su
SET CONFIG_LDAPURL=ldap://atb.su:389
SET CONFIG_LDAPSEARCHBASE=ou=ATB,dc=atb,dc=su
SET CONFIG_LDAPADMINGROUP=chita_admins
SET CONFIG_LDAPUSERGROUP=Пользователи домена
SET CONFIG_LDAPFILTER=^(^|^(memberOf=cn=chita_admins,uo=Admins,uo=ГО,ou=_ATB - Группы упрвления,dc=atb,dc=su^)^(memberOf=cn=Пользователи домена,cn=Users,dc=atb,dc=su^)^)
SET CONFIG_UPLOADPATH=d:\upload
java   -jar    target\Inventory-list.jar