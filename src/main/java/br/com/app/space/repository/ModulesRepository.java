package br.com.app.space.repository;


import br.com.app.space.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModulesRepository extends JpaRepository<Module, Integer> {

    List<Module> findAll();

    Module findByName(String name);

}
