package unir.operadorpeliculas.service;

import java.util.List;

import unir.operadorpeliculas.model.pojo.RentalModel;
import unir.operadorpeliculas.model.request.RentMovieRequest;

public interface MovieService {

	RentalModel getRentId(String id);
	
	List<RentalModel> getRentMovieId(String movieId);
	
	RentalModel createRentMovie(RentMovieRequest request);
	
	RentalModel updateRentMovie(RentMovieRequest request);
	
	Boolean deleteRentMovie(RentMovieRequest request);
}
