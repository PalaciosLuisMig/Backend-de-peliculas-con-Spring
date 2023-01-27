package unir.operadorpeliculas.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import unir.operadorpeliculas.model.pojo.RentalModel;
import unir.operadorpeliculas.model.pojo.MovieModel;

public interface RentRepository extends JpaRepository<RentalModel, Long>{
	 
	Optional<RentalModel> findById(Long id); 
	
	@Query("SELECT m from RentalModel m WHERE m.movie_id = ?1")
	List<RentalModel> findByMovieId(MovieModel movieid);

}
