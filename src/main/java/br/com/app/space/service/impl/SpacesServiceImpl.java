package br.com.app.space.service.impl;

import br.com.app.space.constants.MessagesConstants;
import br.com.app.space.model.Page;
import br.com.app.space.model.Spaces;
import br.com.app.space.model.Users;
import br.com.app.space.model.dto.SpacesDto;
import br.com.app.space.model.error.Response;
import br.com.app.space.repository.PageRepository;
import br.com.app.space.repository.SpacesRepository;
import br.com.app.space.repository.UserRepository;
import br.com.app.space.service.SpacesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpacesServiceImpl implements SpacesService {

    private final SpacesRepository spacesRepository;
    private final UserRepository userRepository;
    private final PageRepository pageRepository;
    @Override
    public ResponseEntity<Spaces> save(SpacesDto dto) {
        var result = spacesRepository.findByName(dto.getName());

        if(result != null){
           return new ResponseEntity(Response.builder().build().badRequest(), HttpStatus.BAD_REQUEST);
        }

        var user = userRepository.findById(dto.getIdUser());
        List<Page> apps = new ArrayList<>();
        dto.getApplications().forEach(app -> {
            apps.add(pageRepository.findByName(app));
        });

        var entity = Spaces.builder()
                .applications(apps)
                .createAt(new Date())
                .user(Users.builder().id(user.get().getId()).build())
                .name(dto.getName())
                .build();
        try{
            var saved = spacesRepository.save(entity);
            return new ResponseEntity(Response.builder().body(saved).message(MessagesConstants.OK).timeStamp(new Date()).build().success(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(Response.builder().build().badRequest(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Spaces> findAll() {
        return spacesRepository.findAll();
    }
}
