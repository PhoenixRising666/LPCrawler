import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Crawler {
	 
	public ArrayList<String> urls = new ArrayList<String>();
	String BaseURL;
    String base;
    int depth;
	private String flag="";
    public static ArrayList<String> StaticUrls=new ArrayList<String>();

	
	public void CrawlerInit(String URLin, String Basein, int DepthIn){
        BaseURL=URLin;
        base=Basein;
        depth=DepthIn;
        urls.add(BaseURL);
    }
    
    public void runCrawler(){
        asyncronous A1 = new asyncronous(BaseURL, base, urls, depth ,0);
        A1.run();
    }
	
	
	
	public void runListURL(){
		threadSearch search;
		for(int i=0;i<urls.size();i++){
			
			if(0==i){
				//for update
				flag="Starting Static Link Search";
			}
			if((urls.size()/10)==i){
				//for update
				flag="10% of the webpages searched";
			}
			if((urls.size()/10)*2==i){
				//for update
				flag="20% of the webpages searched";
			}
			if((urls.size()/10)*3==i){
				//for update
				flag="30% of the webpages searched";
			}
			if((urls.size()/10)*4==i){
				//for update
				flag="40% of the webpages searched";
			}
			if((urls.size()/10)*5==i){
				//for update
				flag="50% of the webpages searched";
			}
			if((urls.size()/10)*6==i){
				//for update
				flag="60% of the webpages searched";
			}
			if((urls.size()/10)*7==i){
				//for update
				flag="70% of the webpages searched";
			}
			if((urls.size()/10)*8==i){
				//for update
				flag="80% of the webpages searched";
			}
			if((urls.size()/10)*9==i){
				//for update
				flag="90% of the webpages searched";
			}
			if(urls.size()==i){
				//for update
				flag="100% of the webpages searched";
			}
			search=new threadSearch(urls.get(i), StaticUrls);
			search.run();
		}
		flag="done";
		
	 }
	
	 public String flag(){
		 return flag;
	 }


}
