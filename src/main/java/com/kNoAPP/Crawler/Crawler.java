package com.kNoAPP.Crawler;

public class Crawler {

	public static void main(String[] args) {
		Spider spider = new Spider();
		spider.search(args.length > 0 ? args[0] : "https://www.reddit.com/");
	}
}
