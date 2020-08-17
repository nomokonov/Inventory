package inventory.atb.su.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

import java.util.Arrays;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    private final CustomSuccessHandler customSuccessHandler;

    @Value("${config.ldap-searchbase}")
    public String LDAP_SEARCHBASE;

    @Value("${config.ldap-domain}")
    public String LDAP_DOMAIN;

    @Value("${config.ldap-url}")
    public String LDAP_URL;

    @Value("${config.ldap-filter}")
    public String LDAP_FILTER;

    @Value("${config.ldap-admingroup}")
    public String LDAP_ADMINGROUP;

    @Value("${config.ldap-usergroup}")
    public String LDAP_USERGROUP;

    @Value("${config.upload-path}")
    public  String UPLOAD_PATH;

    @Autowired
    public WebSecurityConfig(CustomSuccessHandler customSuccessHandler) {
        this.customSuccessHandler = customSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/rest/**" ).permitAll()

                .antMatchers("/user/**").access("hasAnyAuthority('" + LDAP_USERGROUP + "','" + LDAP_ADMINGROUP + "')")
                .antMatchers("/invmovings/**").access("hasAnyAuthority('" + LDAP_USERGROUP + "','" + LDAP_ADMINGROUP + "')")
                .antMatchers("/admin/**","/inventory/**").hasAuthority(LDAP_ADMINGROUP)
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(customSuccessHandler)
                .and().logout()    //logout configuration
                .logoutSuccessUrl("/login")

        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.authenticationProvider(activeDirectoryLdapAuthenticationProvider()).userDetailsService(userDetailsService());
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Arrays.asList(activeDirectoryLdapAuthenticationProvider()));
    }

    @Bean
    public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {

        ConfigPrint();
        ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(LDAP_DOMAIN, LDAP_URL, LDAP_SEARCHBASE);
        provider.setSearchFilter("(&(objectClass=user)(userPrincipalName={0})" + LDAP_FILTER + ")");
        provider.setConvertSubErrorCodesToExceptions(true);
        provider.setUseAuthenticationRequestCredentials(true);

        return provider;
    }

    private void ConfigPrint() {
        logger.info("LDAP_SEARCHBASE = " + LDAP_SEARCHBASE);
        logger.info("LDAP_DOMAIN =     " + LDAP_DOMAIN);
        logger.info("LDAP_URL =        " + LDAP_URL);
        logger.info("LDAP_ADMINGROUP = " + LDAP_ADMINGROUP);
        logger.info("LDAP_USERGROUP =  " + LDAP_USERGROUP);
        logger.info("LDAP_FILTER =     (&(objectClass=user)(userPrincipalName={0})" + LDAP_FILTER + ")");
        logger.info("UPLOAD_PATH =     " + UPLOAD_PATH);
    }

}
