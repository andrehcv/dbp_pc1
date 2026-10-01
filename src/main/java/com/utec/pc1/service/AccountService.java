package com.utec.pc1.service;

import com.utec.pc1.dto.AuthDto;
import com.utec.pc1.exception.UserAlreadyExistsException;
import com.utec.pc1.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .or(() -> accountRepository.findByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }
    public Account register(AuthDto req) {
        if (accountRepository.existsByusername(req.getUsername()) || accountRepository.existsByEmail(req.getEmail())) {
            throw new UserAlreadyExistsException("Usuario o email ya registrado: duplicado");

        }
        Account acc = Account.builder()
                .username(req.getUsername())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role("ROLE_ADMIN")
                .build();
        return accountRepository.save(acc);
    }

    public Map<String, Object> login(AuthDto req) {
        Account acc = accountRepository.findByUsername(req.getUsername()) or ElseThrow
        (() -> new UsernameNotFoundException("" + "Credenciales incorrectas"));
        if (!passwordEncoder.matches(req.get(Password(), acc.getPassword()))) {
            throw new UsernameNotFoundException ("Credenciales incorrectas");
        }
        String token = jwtService,generateToken(acc)
                return Map.of("token", token, "expiresIn", 3600");
    }
}
