package br.com.app.space.controller;


import br.com.app.space.model.Spaces;
import br.com.app.space.model.dto.SpacesDto;
import br.com.app.space.model.error.Response;
import br.com.app.space.service.SpacesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/spaces")
@RequiredArgsConstructor
public class SpacesController {

    private final SpacesService spacesService;

    @GetMapping("/")
    public List<Spaces> spaces() {
        return spacesService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Spaces> saveSpace(@RequestBody SpacesDto dto) {
        return spacesService.save(dto);
    }

}
