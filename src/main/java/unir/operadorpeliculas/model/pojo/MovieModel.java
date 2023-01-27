package unir.operadorpeliculas.model.pojo;

import java.util.GregorianCalendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "movies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MovieModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "release_date")
	private GregorianCalendar release_date;
	
	@Column(name = "language")
	private String language;
	
	@Column(name = "genres")
	private String genres;
	
	@Column(name = "overview")
	private String overview;
	
	@Column(name = "vote_average")
	private double vote_average;
	
	@Column(name = "video")
	private String video;
    
}
