package com.kNoAPP.Crawler;

import java.util.ArrayList;
import java.util.List;

public class Spider {

	private List<String> hasVisited = new ArrayList<String>();
	private List<String> toVisit = new ArrayList<String>();

	public void search(String url, int size) {
		toVisit.add(url);
		while(hasVisited.size() < size) {
			SpiderLeg leg = new SpiderLeg(nextUrl());
			if(toVisit.size() < 100) toVisit.addAll(leg.getLinks());
		}
		
		System.out.println("[Done] Visited " + hasVisited.size() + " web page(s).");
	}
	
	public void search(String url) {
		toVisit.add(url);
		while(true) {
			SpiderLeg leg = new SpiderLeg(nextUrl());
			if(toVisit.size() < 100) toVisit.addAll(leg.getLinks());
		}
	}

	private String nextUrl() {
		String nextUrl;
		do {
			nextUrl = toVisit.remove(0);
		} while(hasVisited.contains(nextUrl));
		hasVisited.add(nextUrl);
		return nextUrl;
	}
}
