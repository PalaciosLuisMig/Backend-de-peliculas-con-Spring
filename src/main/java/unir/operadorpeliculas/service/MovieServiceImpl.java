package unir.operadorpeliculas.service;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unir.operadorpeliculas.data.RentRepository;
import unir.operadorpeliculas.model.pojo.MovieModel;
import unir.operadorpeliculas.model.pojo.RentalModel;
import unir.operadorpeliculas.model.request.RentMovieRequest;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private RentRepository rentRepository;
	
	//Para obtener la renta de un id especifico
	@Override
	public RentalModel getRentId(String id) {
		return this.rentRepository.findById(Long.valueOf(id)).orElse(null);
	}
	
	//Para todos los registros de rentas de una pelicula
	@Override
	public List<RentalModel> getRentMovieId(String movieId){
		
		MovieModel modelMovie = new MovieModel();
		modelMovie.setId(Long.valueOf(movieId));
		
		List<RentalModel> rentMovies = rentRepository.findByMovieId(modelMovie);
		return rentMovies.isEmpty() ? null : rentMovies;
	}
	
	//Para rentar la pelicula
	@Override
	public RentalModel createRentMovie(RentMovieRequest request) {
		if(request != null && request.getMovie_id().getId() != 0 ) {
			
			MovieModel modelMovie = new MovieModel();
			modelMovie.setId(request.getMovie_id().getId());
			
			List<RentalModel> rentMovies = rentRepository.findByMovieId(modelMovie);
			
			//Si es la primera vez que se alquila la pelicula
			if(rentMovies.size() == 0) {
				GregorianCalendar today = new GregorianCalendar(); 
				RentalModel rentalModel = RentalModel.builder().date_rental(today).movie_id(request.getMovie_id()).build();
				return rentRepository.save(rentalModel);
			}
			
			//Si existe la pelicula alquilada se revisa si esta devuelta para volver a alquilarla
			if(rentMovies.size() > 0 && rentMovies.get(rentMovies.size()-1).getDate_return() != null) {
				
				System.out.println("El número de rentas de la pelícual ID: " + request.getMovie_id().getId().toString() 
						+" es " + rentMovies.size());
				
				GregorianCalendar today = new GregorianCalendar(); 
				RentalModel rentalModel = RentalModel.builder().date_rental(today).movie_id(request.getMovie_id()).build();
				return rentRepository.save(rentalModel);
			}
			else {
				return null;
			}
			
		}
		else {
			return null;
		}
	}
	
	//Para devolver la pelicula
	@Override
	public RentalModel updateRentMovie(RentMovieRequest request) {
		
		RentalModel rentalModelFindById = rentRepository.findById(Long.valueOf(request.getRent_id())).orElse(null);
		GregorianCalendar today = new GregorianCalendar();
		
		if(rentalModelFindById != null) {
			RentalModel rentalModel = RentalModel.builder().id(request.getRent_id())
					.date_rental(rentalModelFindById.getDate_rental())
					.date_return(today)
					.movie_id(rentalModelFindById.getMovie_id())
					.build();
			
			return rentRepository.save(rentalModel);
		}
		else {
			return null;
		}
	}
	
	//Para eliminar una renta en especifico
	@Override
	public Boolean deleteRentMovie(RentMovieRequest request) {
		
		RentalModel rentalModelFindById = rentRepository.findById(Long.valueOf(request.getRent_id())).orElse(null);
		
		if(rentalModelFindById != null) {
			rentRepository.deleteById(Long.valueOf(request.getRent_id()));
			return Boolean.TRUE;
		}
		else {
			return Boolean.FALSE;
		}
	}

}
