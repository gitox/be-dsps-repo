package com.dsps.metier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	private static final String REGEX = "<dl><dt><a href=\"(/[\\w]*?)\">(.*?)<b>";

	public static String getTitle(String input){
		StringBuffer buf = new StringBuffer();
		Pattern p = Pattern.compile(REGEX);
		//  get a matcher object
		Matcher m = p.matcher(input);
		
		while(m.find()) {
			buf.append("<a href=\"https://torrentz.eu");
			buf.append(m.group(1));
			buf.append("\">");
			buf.append(m.group(2));
			buf.append("</a>");
		}
		
		return buf.toString();
	}
}
