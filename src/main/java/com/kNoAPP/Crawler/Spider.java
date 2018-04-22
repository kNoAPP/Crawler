package com.kNoAPP.Crawler;

import java.util.ArrayList;
import java.util.List;

public class Spider {

	private List<String> hasVisited = new ArrayList<String>();
	private List<String> toVisit = new ArrayList<String>();

	public void search(String url, int size) {
		toVisit.add(url);
		
		while(hasVisited.size() < size) {
			String next = nextUrl();
			if(next != null) {
				SpiderLeg leg = new SpiderLeg(next);
				if(toVisit.size() < 10000) toVisit.addAll(leg.getLinks());
			} else break;
		}
		
		System.out.println("[Done] Visited " + hasVisited.size() + " web page(s).");
	}
	
	public void search(String url) {
		toVisit.add(url);
		
		while(true) {
			String next = nextUrl();
			if(next != null) {
				SpiderLeg leg = new SpiderLeg(next);
				if(toVisit.size() < 10000) toVisit.addAll(leg.getLinks());
			} else break;
		}
		
		System.out.println("[Done] Visited " + hasVisited.size() + " web page(s).");
	}

	private String nextUrl() {
		String nextUrl;
		do {
			if(toVisit.size() > 0) nextUrl = toVisit.remove(0);
			else return null;
		} while(hasVisited.contains(nextUrl));
		hasVisited.add(nextUrl);
		return nextUrl;
	}
}
