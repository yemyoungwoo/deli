//package ymw.delivery.config;
//
//@Override
//protected void configure(HttpSecurity http) throws Exception {
//	http.csrf().disable();
//	
//	http.authorizeRequests()
//		.antMatchers("/admin/**").hasRole("ADMIN")
//		.antMatchers("/user/**").hasAnyRole("ADMIN, USER")
//		.anyRequest().permitAll()
//	.and()
//		.formLogin()
//		.loginPage("/") // 인증 필요한 페이지 접근시 이동페이지
//		.loginProcessingUrl("/login")
//		.successHandler(loginSuccess)
//		.failureHandler(loginFail)
//	.and()
//		.logout()
//		.logoutSuccessUrl("/myPage")
//	.and()
//		.rememberMe()
//		.key("rememberKey")
//		.rememberMeCookieName("rememberMeCookieName")
//		.rememberMeParameter("remember-me")
//		.tokenValiditySeconds(60 * 60 * 24 * 7)
//		.userDetailsService(loginDetailService)
//	;
//}
