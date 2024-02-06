package br.com.app.space.service;

import br.com.app.space.model.Page;

import java.util.List;
import java.util.Optional;

public interface PageService {

    List<Page> findAll();

    Optional<Page> findByName(String name);

}
