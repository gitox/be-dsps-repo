package com.dsps.beans;

public class BMediaItem {
	private String link; 
	private String title;
	private String features;
	private String type; 
	private String quality;
	private String rating;
	private String added;
	private String size;
	private int seeders; 
	private int leechers;

	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getAdded() {
		return added;
	}
	public void setAdded(String added) {
		this.added = added;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getSeeders() {
		return seeders;
	}
	public void setSeeders(int seeders) {
		this.seeders = seeders;
	}
	public int getLeechers() {
		return leechers;
	}
	public void setLeechers(int leechers) {
		this.leechers = leechers;
	}
	
	private int getInt(String value){
		value = value.replace(",","");
		return Integer.valueOf(value);
	}

	public void setAttributes(String nodeName,String nodeValue){
		switch(nodeName){
		case "v" :
			this.setRating(nodeValue);
			break;
		case "a":
			this.setAdded(nodeValue);
			break;
		case "s":
			this.setSize(nodeValue);
			break;
		case "u":
			this.setSeeders(getInt(nodeValue));
			break;
		case "d":
			this.setLeechers(getInt(nodeValue));
		}
	}
}
