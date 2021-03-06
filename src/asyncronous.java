import java.io.IOException;
import java.util.ArrayList;

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
	
	asyncronous(String BaseURL, String baseIn, ArrayList<String> urlsIn, int Depth, int Current){
	       url=BaseURL;
	       base=baseIn;
	       urls=urlsIn;
	       MaxDepth=Depth;
	       CurrentDepth=Current;
	}
	
	public void run() {
		try{
			Document doc = Jsoup.connect(url).get();
			org.jsoup.select.Elements links = doc.select("a");
			for(Element e: links){
				//only if this website has no longer been visited
				if(!urls.contains(e.attr("abs:href"))){
					//eliminates pictures and pdfs
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
															//buildList(e.attr("abs:href"),base);
															asyncronous A1 = new asyncronous(e.attr("abs:href"),base, urls, MaxDepth, CurrentDepth++);
															A1.run();
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
			
		}
	}

}
