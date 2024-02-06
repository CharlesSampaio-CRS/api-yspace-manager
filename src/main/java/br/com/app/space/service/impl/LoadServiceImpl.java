package br.com.app.space.service.impl;

import br.com.app.space.model.Page;
import br.com.app.space.model.Module;
import br.com.app.space.repository.PageRepository;
import br.com.app.space.repository.ModulesRepository;
import br.com.app.space.repository.SpacesRepository;
import br.com.app.space.service.LoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.List.of;

@Service
@RequiredArgsConstructor
public class LoadServiceImpl implements LoadService {

    private final PageRepository pageRepository;
    private final ModulesRepository modulesRepository;
    private final SpacesRepository spacesRepository;

    @Override
    public void loadData(){

        var spaces = spacesRepository.findAll();
        spacesRepository.deleteAll(spaces);

        var mods =  modulesRepository.findAll();
       mods.stream().forEach(m ->{
            modulesRepository.deleteById(m.getId());
        });

       var apps = pageRepository.findAll();
       apps.stream().forEach(m ->{
            pageRepository.deleteById(m.getId());
        });

        var appsSocial = of(
                Page.builder().active(true).name("Instagram").url("https://www.instagram.com/").build(),
                Page.builder().active(true).name("Twitter").url("https://twitter.com/login?lang=pt").build(),
                Page.builder().active(true).name("Threads").url("https://www.facebook.com/threadsradio/").build()
        );
        var saved = pageRepository.saveAll(appsSocial);

        var modSocial = Module.builder()
                .active(true)
                .applications(saved)
                .icon("")
                .name("Social")
                .build();
        modulesRepository.save(modSocial);

        var appsWork = of(
                Page.builder().active(true).name("Office 365").url("https://login.microsoftonline.com/").build(),
                Page.builder().active(true).name("Google Suite").url("https://admin.google.com/ac/accountchooser?continue=https://gsuite.google.com/dashboard&pli=1").build()
        );

        pageRepository.saveAll(appsWork);
        modulesRepository.save(Module.builder()
                .active(true)
                .applications(appsWork)
                .icon("")
                .name("Work")
                .build());

        var appsInvestments = of(
                Page.builder().active(true).name("Infomoney").url("https://www.infomoney.com.br/").build(),
                Page.builder().active(true).name("Investing").url("https://br.investing.com/").build(),
                Page.builder().active(true).name("Crypto").url("https://coinmarketcap.com/pt-br/").build()
        );

        pageRepository.saveAll(appsInvestments);
        modulesRepository.save(Module.builder()
                .active(true)
                .applications(appsInvestments)
                .icon("")
                .name("Investments")
                .build());
    }

}
