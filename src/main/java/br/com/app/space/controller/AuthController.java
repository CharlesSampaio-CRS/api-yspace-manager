package br.com.app.space.controller;

import br.com.app.space.model.AuthRequest;
import br.com.app.space.model.AuthResponse;
import br.com.app.space.service.AuthService;
import br.com.app.space.service.LoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final AuthService authService;
    private final LoadService loadService;
    private final CacheManager cacheManager;


    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request){
        return authService.register(request);
    }

    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody AuthRequest request){
        return authService.authenticate(request);
    }

    @GetMapping("/load")
    public void load() {
        cacheManager.getCacheNames()
                .parallelStream()
                .forEach(n -> cacheManager.getCache(n).clear());
        loadService.loadData();
    }

}
