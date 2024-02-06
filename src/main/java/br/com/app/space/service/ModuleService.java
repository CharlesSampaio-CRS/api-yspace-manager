package br.com.app.space.service;

import br.com.app.space.model.Module;

import java.util.List;

public interface ModuleService {

    List<Module> findAll();

    Module findByName(String name);

}
