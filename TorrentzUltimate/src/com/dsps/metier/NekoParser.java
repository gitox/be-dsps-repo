package com.dsps.metier;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XNIException;
import org.cyberneko.html.filters.DefaultFilter;

import com.moviejukebox.allocine.AllocineApi;
import com.moviejukebox.allocine.model.Movie;
import com.moviejukebox.allocine.model.MovieInfos;
import com.moviejukebox.allocine.model.Search;
import com.sun.org.apache.xerces.internal.xni.QName;

public class NekoParser{
	private static final String PARTNER_KEY = "100043982026";
	private static final String SECRET_KEY = "29d185d98c984a359e6e6f26a0474269";
	private String URL = null;

	public NekoParser(String URL){
		this.URL = URL;
	}

	public class HTMLFilter extends DefaultFilter{ 
		private Set links = new HashSet(); 
		private URI baseURI; 
		private int numA=0; 

		public HTMLFilter(URI baseURI){ 
			this.baseURI = baseURI.normalize(); 
		} 

		public Set getLinks(){ 
			return this.links; 
		} 

		public void startElement(QName element,XMLAttributes attributes,Augmentations augs) throws XNIException{ 
			for(int i=0;i<attributes.getLength();i++){ 
				if(attributes.getLocalName(i).toLowerCase().equals("href")){ 
					System.out.println("attrs:"+attributes.getValue(i)); 
					numA++;           
				} 
			} 
			//careful element names are case sensitive 
			if ( "DL".equals( element.rawname ) ) 
			{ 
				System.out.println("found DL!!!"); 
				String href = attributes.getValue( "HREF" ); 
				String hrefLowerCase = attributes.getValue( "href" ); 

				if(href!=null || hrefLowerCase !=null){ 
					System.out.println("href:"+href); 
					System.out.println("hrefL:"+hrefLowerCase); 
				} 
				links.add(hrefLowerCase); 

			} 
		} 


	} 

	public static void main(String []args) throws Exception{
		MovieInfos movieInfos = null;
		AllocineApi api = new AllocineApi(PARTNER_KEY, SECRET_KEY);
		Search search = api.searchMovies("avatar");
		List<Movie> movies = search.getMovies();
		String yearStr = "2009";
		int year = Integer.valueOf(yearStr);
		for (Movie movie : movies) {
			int code = movie.getCode();
			movieInfos = api.getMovieInfos(String.valueOf(code));
			if(movie.getProductionYear()==year){
				System.out.println(movieInfos.getOriginalTitle());
				System.out.println(movieInfos.getProductionYear());
				System.out.println("syno long : " + movieInfos.getSynopsis());
				System.out.println("syno court : " + movieInfos.getSynopsisShort());
				System.out.println("certification : " + movieInfos.getCertification());
				System.out.println("Code Allociné : " + movieInfos.getCode());
				System.out.println("Note Allociné : " + movieInfos.getPressRating());
				System.out.println("Note publique : " + movieInfos.getUserRating());
				System.out.println("distributeur : " + movieInfos.getDistributor());
				System.out.println("titre original : " + movieInfos.getOriginalTitle());
				System.out.println("Année de production : " + movieInfos.getProductionYear());
				System.out.println("Date de sortie : " + movieInfos.getReleaseDate());
				System.out.println("release state : " + movieInfos.getReleaseState());
				System.out.println("response status code : " + movieInfos.getResponseStatusCode());
				System.out.println("Durée : " + movieInfos.getRuntime());
				System.out.println("Acteurs : " + movieInfos.getActors());
				System.out.println("Réalisateurs : " + movieInfos.getDirectors());
				break;
			}
		}

	} 
} 
