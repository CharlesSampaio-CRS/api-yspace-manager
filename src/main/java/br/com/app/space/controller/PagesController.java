package br.com.app.space.controller;

import br.com.app.space.model.Page;
import br.com.app.space.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class PagesController {

    private final PageService pageService;

    @GetMapping("/")
    public ResponseEntity<List<Page>> pages() {
        var response = pageService.findAll();
        if (response == null) {
            return new ResponseEntity<>( new ArrayList<>(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ArrayList<>(response), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public Optional<Page> pagesByName(@PathVariable("name") String name) {
        return pageService.findByName(name);
    }
}
