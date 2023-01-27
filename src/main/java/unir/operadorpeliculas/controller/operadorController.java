package unir.operadorpeliculas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import unir.operadorpeliculas.service.MovieService;
import unir.operadorpeliculas.model.pojo.RentalModel;
import unir.operadorpeliculas.model.request.RentMovieRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/peliculas")
public class operadorController {
	
	@Autowired
	MovieService service;

	@GetMapping("/test")
	public String getTest() {
		return "OK";
	}
	
	@GetMapping( path = "/rent")
	public ResponseEntity<List<RentalModel>> getRent(@RequestParam("movieId") String movieId){
		
		log.info("Request received for movie rent {}", movieId);
		
		List<RentalModel> rentalModel = this.service.getRentMovieId(movieId);
		
		if(rentalModel != null) {
			log.info("Ingresa ok");
			log.info(rentalModel.toString());
			return ResponseEntity.ok().body(rentalModel);
		}
		else {
			log.info("No existe datos");
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(path = "/rent")
	public ResponseEntity<RentalModel> createRent(@RequestBody RentMovieRequest request){
		RentalModel createRent = this.service.createRentMovie(request);
		
		if(createRent != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createRent);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PatchMapping(path = "/rent")
	public ResponseEntity<RentalModel> updateRent(@RequestBody RentMovieRequest request){
		
		RentalModel updateRent = this.service.updateRentMovie(request);
		
		if(updateRent != null) {
			return ResponseEntity.status(HttpStatus.OK).body(updateRent);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping(path = "/rent")
	public ResponseEntity<Void> deleteRent(@RequestBody RentMovieRequest request) {
		
		Boolean delete = this.service.deleteRentMovie(request);
		
		if(Boolean.TRUE.equals(delete)) {
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.badRequest().build();
		}
		
	}

}
