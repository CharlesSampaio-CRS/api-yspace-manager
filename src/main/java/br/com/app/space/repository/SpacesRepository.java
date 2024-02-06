package br.com.app.space.repository;


import br.com.app.space.model.Spaces;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpacesRepository extends JpaRepository<Spaces, Integer> {

    Spaces findByName(String name);
}
