package br.com.senai.travel_agency_api.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.senai.travel_agency_api.models.Role;
import br.com.senai.travel_agency_api.models.Usuario;
import br.com.senai.travel_agency_api.repositories.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {

  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

  public DataLoader(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(String... args) throws Exception {
    if (usuarioRepository.count() == 0) {
      Usuario admin = new Usuario();
      admin.setUsername("admin");
      admin.setPassword(passwordEncoder.encode("admin123"));
      admin.setRole(Role.ROLE_ADMIN);

      Usuario user = new Usuario();
      user.setUsername("user");
      user.setPassword(passwordEncoder.encode("user123"));
      user.setRole(Role.ROLE_USER);

      usuarioRepository.saveAll(Arrays.asList(admin, user));
    }
  }
}
