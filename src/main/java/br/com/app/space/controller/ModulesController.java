package br.com.app.space.controller;

import br.com.app.space.model.Module;
import br.com.app.space.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/modules")
@RequiredArgsConstructor
public class ModulesController {

    private final ModuleService moduleService;

    @GetMapping("/")
    public ResponseEntity<List<Module>> modules() {
        var response = moduleService.findAll();
        if (response == null) {
            return new ResponseEntity<>( new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ArrayList<>(response), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public Module findByName(@PathVariable("name") String name) {
        return moduleService.findByName(name);
    }

}
