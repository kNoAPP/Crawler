package com.kNoAPP.Crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg {

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private String url;
	private List<String> links = new ArrayList<String>();
	private Document htmlDocument;
	private boolean success;

	public SpiderLeg(String url) {
		this.url = url;
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			Document htmlDocument = connection.get();
			this.htmlDocument = htmlDocument;
			
			if(connection.response().statusCode() == 200) { // 200 is the HTTP OK status code										
				if(connection.response().contentType().contains("text/html")) {
					System.out.println("[Visit] " + url);
					Elements linksOnPage = htmlDocument.select("a[href]");
					System.out.println("  - Found (" + linksOnPage.size() + ") links");
					for(Element link : linksOnPage) links.add(link.absUrl("href"));
					
					success = true;
				} else {
					System.out.println("[FailureA] " + url);
					success = false;
				}
			}
		} catch (Exception e) {
			System.out.println("[FailureB] " + url);
			success = false; 
		}
	}
	
	public String getURL() {
		return url;
	}

	public boolean wasSuccessful() {
		return success;
	}
	
	public boolean searchForWord(String searchWord) {
		if(success && htmlDocument != null) {
			String bodyText = htmlDocument.body().text();
			return bodyText.toLowerCase().contains(searchWord.toLowerCase());
		}
		return false;
	}

	public List<String> getLinks() {
		return links;
	}

}
