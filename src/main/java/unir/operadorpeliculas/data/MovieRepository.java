package unir.operadorpeliculas.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import unir.operadorpeliculas.model.pojo.MovieModel;

public interface MovieRepository extends JpaRepository<MovieModel, Long>{
	
	Optional<MovieModel> findById(Long id);
}
