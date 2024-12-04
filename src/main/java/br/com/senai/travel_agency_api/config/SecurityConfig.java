package br.com.senai.travel_agency_api.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.senai.travel_agency_api.repositories.UsuarioRepository;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final UsuarioRepository usuarioRepository;

	public SecurityConfig(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(HttpMethod.GET, "/api/destinos/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers(HttpMethod.POST, "/api/destinos/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.PUT, "/api/destinos/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.PATCH, "/api/destinos/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers(HttpMethod.DELETE, "/api/destinos/**").hasRole("ADMIN")
					.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
		;

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> usuarioRepository.findByUsername(username)
			.map(user -> {
				return new User(
					user.getUsername(),
					user.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
			})
			.orElseThrow(() -> {
				return new UsernameNotFoundException("Usuário não encontrado");
			});
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
