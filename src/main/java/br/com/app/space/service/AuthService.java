package br.com.app.space.service;

import br.com.app.space.model.AuthRequest;
import br.com.app.space.model.AuthResponse;

public interface AuthService {

    AuthResponse register(AuthRequest request);

    AuthResponse authenticate(AuthRequest request);
}
