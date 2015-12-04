import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class asyncronous implements Runnable {
	private Thread t;
	private String url;
	private String base;
	private int MaxDepth;
	private int CurrentDepth;
	public static ArrayList<String> urls;
	private Map<String, String> container;
	
	//constructor
	
	asyncronous(String BaseURL, String baseIn, ArrayList<String> urlsIn, int Depth, int Current, Map<String, String> in){
	       url=BaseURL;
	       base=baseIn;
	       urls=urlsIn;
	       MaxDepth=Depth;
	       CurrentDepth=Current;
	       container=in;
	}
	
	public void run() {
		try{
			int i=0;
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements links = doc.select("a");
			for(Element e: links){
				System.out.println(i);
				i++;
				//only if this website has no longer been visited
				if(!urls.contains(e.attr("abs:href"))){
					//eliminates pictures and pdfs, and common website links
					if(!e.attr("abs:href").contains(".jpg")){
						if(!e.attr("abs:href").contains("#")){
							if(!e.attr("abs:href").contains(".pdf")){
								if(!e.attr("abs:href").contains("google.com")){
									if(!e.attr("abs:href").contains("facebook.com")){
										if(!e.attr("abs:href").contains("linkedin.com")){
											if(!e.attr("abs:href").contains("twitter.com")){
												if(!e.attr("abs:href").contains("web.archive.org")){
													//makes sure it doesn't leave the website
													if(e.attr("abs:href").contains(base)){
														urls.add(e.attr("abs:href"));
														
														//System.out.println(e.attr("abs:href"));
																												
														//limits depth search
														if(CurrentDepth<=MaxDepth){
															//recursive call
															asyncronous A1 = new asyncronous(e.attr("abs:href"),base, urls, MaxDepth, CurrentDepth+1,container);
															System.out.println("Thread Open");
															A1.run();
															container.put(e.attr("abs:href"), getUrlSource(e.attr("abs:href")));		
															urls.add(e.attr("abs:href"));
															
															this.wait();
															System.out.println("Thread Wait Over");
														}
														else{
															container.put(e.attr("abs:href"), getUrlSource(e.attr("abs:href")));		
															urls.add(e.attr("abs:href"));
															
															System.out.println("Thread Close");
															this.notify();
														}
														
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch(IOException ex) {		
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	// pulls source from url
	public String getUrlSource(String url) {
		//System.out.println("Pull Source");
		try {
			URL yahoo = new URL(url);
			URLConnection yc = yahoo.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuilder a = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
				a.append(inputLine);
			in.close();

			//test
			//System.out.println(a.toString());
			return a.toString();
		} catch (IOException e) {
			return "";
		}
	}



}
