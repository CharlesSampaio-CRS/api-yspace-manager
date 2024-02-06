package br.com.app.space.service.impl;

import br.com.app.space.model.Module;
import br.com.app.space.repository.ModulesRepository;
import br.com.app.space.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModulesServiceImpl implements ModuleService {

    private final ModulesRepository repository;

    @Override
    public List<Module> findAll() {

               var response =  repository.findAll();
               return response;
    }

    @Override
    public Module findByName(String name) {
        return repository.findByName(name);
    }

}
