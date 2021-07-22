package br.eletrosom.portalcolaborador.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import br.eletrosom.portalcolaborador.service.UsuarioService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  if (!registry.hasMappingForPattern("/login/**")) {
	     registry.addResourceHandler("/login/**").addResourceLocations("classpath:/assets/");
	  }
	}


	@Autowired
	private UsuarioService usuarioRepositoryImpl;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
		//converte senha fornecida pelo usuario para md5
        return new CustomPasswordEncoder();
    }

	/**
	 * REALIZA AS CONFIGURAÇÕES DE ACESSO
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				// .csrf().disable()
				.authorizeRequests()

				/*
				 * DETERMINA QUE PARA REALIZAR ESSA REQUEST PRECISA TER authoritie ACESSO_RH
				 */
				.antMatchers("/logado_rh/**","/salvarEnvio").hasAuthority("ACESSO_RH")
				//PARA REALIZAR ESTE REQUEST PPRECISA DE AUTHORITIE ACESSO_GESTOR
				.antMatchers("/logado_gestor/**").hasAuthority("ACESSO_GESTOR")
				//requests permitidas a todos usuarios
				.antMatchers("/fonts/**","/vendor/**","/css/**","/images/**","/js/**","/webfonts/**","/jquery/**").permitAll()
				.antMatchers("/","/index","/erro").permitAll()
				//requests permitidas somente a usuarios autenticados
				.antMatchers("/about","/home","/layout","/logado_usuario/**","/logado_chave/**").authenticated().anyRequest()
				//TODOS TEM ACESSO A PAGINA DE LOGIN
				.authenticated().and().formLogin()
	            .loginPage("/").usernameParameter("codigo").defaultSuccessUrl("/about", true)
	            .permitAll()
	            .and() 
				
				/*
				 * AQUI ESTAMOS INFORMANDO QUE QUANDO FOR REDIRECIONADO PARA O LINK
				 * http://localhost:8095/logout O USUÁRIO DEVE TER SUA SESSÃO FINALIZADA E
				 * REDIRECIONADO PARA A PÁGINA DE LOGIN
				 */
				.logout().logoutSuccessUrl("/").logoutUrl("/logout").permitAll();

		/*
		 * PÁGINA COM A MENSAGEM DE ACESSO NEGADO QUANDO O USUÁRIO NÃO TER UMA
		 * DETERMINADA PERMISSÃO DE ACESSO AO SISTEMA ELE VAI SER REDIRECIONADO PARA A
		 * URL ABAIXO
		 */
		http.exceptionHandling().accessDeniedPage("/acessoNegado");

	}
	

	
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	        auth.setUserDetailsService(usuarioRepositoryImpl);
	        auth.setPasswordEncoder(passwordEncoder());
	        return auth;
	    }

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(authenticationProvider());
		//builder.userDetailsService(usuarioRepositoryImpl).passwordEncoder(passwordEncoder());
	}


	/*
	 * CRIPTOGRAFANDO A SENHA PARA TESTE
	 */
	// public static void main(String[] args) {

	// System.out.println(passwordEncoder().encode("1472587"));
	// * Md5PasswordEncoder System.out.println(new
	// * MessageDigestPasswordEncoder("MD5").encode("1472587"));
	// System.out.println(new Criptografia().encode("1472587"));
	// }

}
