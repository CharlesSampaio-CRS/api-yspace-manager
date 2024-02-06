package br.com.app.space.service;

import br.com.app.space.model.Spaces;
import br.com.app.space.model.dto.SpacesDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpacesService {

    ResponseEntity<Spaces> save(SpacesDto dto);
    List<Spaces> findAll();

}
