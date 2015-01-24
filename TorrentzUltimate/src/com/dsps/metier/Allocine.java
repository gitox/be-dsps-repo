package com.dsps.metier;

import java.util.List;

import com.moviejukebox.allocine.AllocineApi;
import com.moviejukebox.allocine.model.Movie;
import com.moviejukebox.allocine.model.MovieInfos;
import com.moviejukebox.allocine.model.Search;

public class Allocine{
	private static final String PARTNER_KEY = "100043982026";
	private static final String SECRET_KEY = "29d185d98c984a359e6e6f26a0474269";
	private AllocineApi api;
	
	public Allocine(){
		api = new AllocineApi(PARTNER_KEY, SECRET_KEY);
	}
	
	public MovieInfos getMovieInfos(String titleYear) throws Exception{
		int nineties = titleYear.lastIndexOf("19");
		int twenties = titleYear.lastIndexOf("20");
		int yearPrefix = nineties+twenties;
		String title = titleYear.substring(0,yearPrefix).trim();
		String year = titleYear.substring(yearPrefix+1,yearPrefix+5).trim();
		return getMovieInfos(title, year);
	}
	
	public MovieInfos getMovieInfos(String title,String yearStr) throws Exception{
		Search search = api.searchMovies(title);
		List<Movie> movies = search.getMovies();
		int year = Integer.valueOf(yearStr);
		MovieInfos movieInfos=null;
		
		for (Movie movie : movies) {
			if(movie.getOriginalTitle().equalsIgnoreCase(title) && movie.getProductionYear()==year){
				int code = movie.getCode();
				movieInfos = api.getMovieInfos(String.valueOf(code));
				break;
			}
		}
		
		return movieInfos;
	} 
} 
