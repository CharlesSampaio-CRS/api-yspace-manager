package br.com.app.space.service.impl;

import br.com.app.space.Utils.ValidatorUtils;
import br.com.app.space.constants.MessagesConstants;
import br.com.app.space.model.*;
import br.com.app.space.repository.PageRepository;
import br.com.app.space.repository.ModulesRepository;
import br.com.app.space.repository.SpacesRepository;
import br.com.app.space.repository.UserRepository;
import br.com.app.space.security.JwtService;
import br.com.app.space.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PageRepository applicationRepository;
    private final ModulesRepository modulesRepository;
    private final SpacesRepository spacesRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(AuthRequest request) {

        long pid = ProcessHandle.current().pid();
        System.out.println(pid);
        

        if (request == null) {
            return AuthResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(MessagesConstants.EMPTY_REQUEST)
                    .build();
        }
        if (!ValidatorUtils.isEmailValido(request.getEmail())) {
            return AuthResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(MessagesConstants.INVALID_EMAIL)
                    .build();
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return AuthResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(MessagesConstants.USER_ALREADY_EXISTS)
                    .build();
        }

//        var modules = modulesRepository.findAll();
//        modules.forEach(mod -> mod.setApplications(applicationRepository.findAllByIdModule(mod.getId())));

        var user = Users.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .activated(true)
                .createAt(new Date())
                .build();
        var userSaved = userRepository.save(user);

//       var spaceDefault =  Spaces.builder().id(MessagesConstants.DEFAULT_SPACE)
//                        .modules(modules)
//                                .user(Users.builder().id(userSaved.getId()).build()
//                                        ).build();
//
//        spacesRepository.save(spaceDefault);

        return AuthResponse.builder()
                .message(MessagesConstants.SUCCESSFUL_TOKEN_GENERATOR)
                .code(HttpStatus.CREATED.value()).build();
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
            user.setLastAccess(new Date());
            userRepository.saveAndFlush(user);
            return AuthResponse.builder()
                    .token(generateToken(user)).build();
        } catch (Exception e) {
            return AuthResponse.builder()
                    .message(MessagesConstants.AUTHENTICATE_ERROR).build();
        }
    }

    private String generateToken(Users user) {
        return jwtService.generateToken(user);
    }

}
