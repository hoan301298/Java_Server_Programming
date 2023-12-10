package vamk.fi.e2000575.server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vamk.fi.e2000575.server.config.JwtService;
import vamk.fi.e2000575.server.entity.RegisterRequest;
import vamk.fi.e2000575.server.entity.UserData;
import vamk.fi.e2000575.server.repository.UserDataRepository;

@Service
@AllArgsConstructor
public class AuthenticationService {

        @Autowired
        private UserDataRepository userDataRepository;
        private final PasswordEncoder passwordEncoder;

        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {

                UserData user = new UserData();
                if (!userDataRepository.findByUsername(request.getUsername()).isPresent()) {
                        user.setUsername(request.getUsername());
                        user.setPassword(passwordEncoder.encode(request.getPassword()));
                        user.setRole("ADMIN");
                        userDataRepository.save(user);
                        var jwtToken = jwtService.generateToken(user);
                        return AuthenticationResponse.builder()
                                        .token(jwtToken)
                                        .build();
                } else {
                        return null;
                }

        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));
                var user = userDataRepository.findByUsername(request.getUsername())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build();
        }
}