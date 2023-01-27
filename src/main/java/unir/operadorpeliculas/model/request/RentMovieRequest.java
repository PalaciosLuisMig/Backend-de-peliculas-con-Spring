package unir.operadorpeliculas.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unir.operadorpeliculas.model.pojo.MovieModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentMovieRequest {
	
	private Long rent_id;
	private MovieModel movie_id;

}
