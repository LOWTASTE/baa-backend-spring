package com.lowt.baabackend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowt.baabackend.service.Impl.BaaUsersServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan("com.lowt.baabackend.mapper.backend")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BaaUsersServiceImpl baaUsersService;

    @Bean
    PasswordEncoder passwordEncoder() {
        //使用明文密码：为了简单方便测试
        return NoOpPasswordEncoder.getInstance();
        //暗文密码：会用salt加密
//        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置注入的自定义认证实现类userService，必须实现了UserDetailsService接口
        auth.userDetailsService(baaUsersService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers需要写在 .anyRequest()之前
                /* anyRequest 已经包含了其他请求了，在它之后如果还配置其他请求也没有任何意义。
                 anyRequest 应该放在最后，表示除了前面拦截规则之外，剩下的请求要如何处理。具体可以稍微查看一下源码,
                 在拦截规则的配置类 AbstractRequestMatcherRegistry 中*/
                .antMatchers("/admin/**").hasRole("admin")//以/admin作为前缀的请求必须具有admin权限才能访问（当前也必须认证通过）
                .antMatchers("/user/**").hasRole("user")//以/user作为前缀的请求必须具有user权限才能访问（当前也必须认证通过）
                .anyRequest().authenticated()//任何请求都认证过放行
                .and()//方法表示结束当前标签，上下文回到HttpSecurity，开启新一轮的配置。
                .formLogin()//使用表单认证
                .loginProcessingUrl("/doLogin")//指定登录页面提交数据的接口
                .successHandler((req, resp, authentication) -> {
                    Object principal = authentication.getPrincipal();//获取认证成功的用户对象
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    //使用Jackson将对象转换为JSON字符串
                    out.write(new ObjectMapper().writeValueAsString(principal));//将登录成功的对象基于JSON响应
                    out.flush();
                    out.close();
                })
                .failureHandler((req, resp, e) -> {//根据异常信息判断哪一操作出现错误
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("status", 400);
                    if (e instanceof LockedException) {
                        map.put("msg", "账户被锁定，请联系管理员!");
                    } else if (e instanceof CredentialsExpiredException) {
                        map.put("msg", "密码过期，请联系管理员!");
                    } else if (e instanceof AccountExpiredException) {
                        map.put("msg", "账户过期，请联系管理员!");
                    } else if (e instanceof DisabledException) {
                        map.put("msg", "账户被禁用，请联系管理员!");
                    } else if (e instanceof BadCredentialsException) {
                        map.put("msg", "用户名或者密码输入错误，请重新输入!");
                    }
                    out.write(new ObjectMapper().writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                .permitAll()//放行自定义登录页面请求
                .and()
                .logout()//默认注销接口/logout
                .logoutUrl("/logout")//默认注销的URL
                //基于前后端分离开发，前端发起/logout请求，后端自定义注销成功处理逻辑：返回json提示成功
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("注销成功");
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .csrf().disable()//关闭csrf攻击拦截
                //配置未认证提示，给未认证（登录成功）访问其他请求时，给前端响应json提示
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, authException) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write("尚未登录，请先登录");
                            out.flush();
                            out.close();
                        }
                );
    }
}
