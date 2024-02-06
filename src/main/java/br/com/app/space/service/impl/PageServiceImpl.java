package br.com.app.space.service.impl;

import br.com.app.space.model.Page;
import br.com.app.space.model.Module;
import br.com.app.space.repository.PageRepository;
import br.com.app.space.repository.ModulesRepository;
import br.com.app.space.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;

    @Override
    public List<Page> findAll() {
        return pageRepository.findAll();
    }

    @Override
    public Optional<Page> findByName(String name) {
        return Optional.ofNullable(pageRepository.findByName(name));
    }

}
