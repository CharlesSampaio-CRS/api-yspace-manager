package br.com.app.space.repository;

import br.com.app.space.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Integer> {

    @Query(value = "SELECT * FROM yspacedb.applications  where id_applications = :idModule", nativeQuery = true)
    Optional<List<Page>> findByIdModule(@Param("idModule") Integer idModule);

    Page findByName(@Param("name") String name);
}
