package com.lizhi.demo.security;

import com.lizhi.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lenovo
 */
@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public WebSecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

//    @Override
//    public void init(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService)
//                .passwordEncoder(new PasswordEncoder() {
//                    @Override
//                    public String encode(CharSequence charSequence)
//                    {
//                        return charSequence.toString();
//                        //return SecureUtil.md5(charSequence.toString());
//                    }
//
//                    @Override
//                    public boolean matches(CharSequence charSequence, String s) {
//                        //return SecureUtil.md5(charSequence.toString()).equals(s);
//                        return charSequence.toString().equals(s);
//                    }
//                });
//        // auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user1").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
//    }

}
