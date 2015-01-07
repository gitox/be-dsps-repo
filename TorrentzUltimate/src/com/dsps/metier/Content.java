package com.dsps.metier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Content {

	public static final String HTML_PATH = "C:\\test\\TzUMate\\";
	public static final String HTML_FILE = "test.html";
	private String url_string = "http://torrentz.eu/search?q=1080p";

	public List<String> getUrlContent(){

		URL url;
		List<String> results = new ArrayList<String>();
		
		try {
			// get URL content
			url = new URL(url_string);
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));

			String inputLine;

			//save to this filename
			String fileName = HTML_PATH+HTML_FILE;
			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			//use FileWriter to write file
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String extract = "";
			boolean found = false;
			while ((inputLine = br.readLine()) != null) {
//				bw.write(inputLine);
//				System.out.println(inputLine);
				if(inputLine.contains("<b> peers </b>")){
					found = true;
				}
				
				if(found){
					extract = Regex.getTitle(inputLine);
					results.add(extract);
					System.out.println(extract);
				}
			}

			bw.close();
			br.close();

			System.out.println("Done");
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}
}
