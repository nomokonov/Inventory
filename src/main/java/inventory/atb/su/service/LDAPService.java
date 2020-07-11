package inventory.atb.su.service;

import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Service;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

@Service
public class LDAPService {

    public String getCN( LdapUserDetails userDetails) throws InvalidNameException {
        LdapName ldapDN = new LdapName(userDetails.getDn());

        String cn = "";
        for (Rdn rdn : ldapDN.getRdns()) {
            if (rdn.getType().equals("CN")) {
                cn = rdn.getValue().toString();
            }
        }
        return cn;
    }
}
