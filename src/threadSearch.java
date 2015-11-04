import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class threadSearch implements Runnable {
		public String source;
		public String url;
		public static ArrayList<String> StaticUrls;
		
		threadSearch(String urlIn, ArrayList<String> StaticUrlsIn){
			url=urlIn;
			StaticUrls=StaticUrlsIn;
		}
		public void run() {
			source=getUrlSource(url);
			hasStatic();
		}
	
		//makes sure source isn't null then searches it for href to liveperson
		private void hasStatic(){
			if(! source.equals(null)){
				if(source.contains("server.iad.liveperson.net")){
					StaticUrls.add(url);
					//System.out.println(url+" has a static button");
				}
			}
		}
		
		//pulls source from url
		 private String getUrlSource(String url)  {
			 try{
		         URL yahoo = new URL(url);
		         URLConnection yc = yahoo.openConnection();
		         BufferedReader in = new BufferedReader(new InputStreamReader(
		                 yc.getInputStream(), "UTF-8"));
		         String inputLine;
		         StringBuilder a = new StringBuilder();
		         while ((inputLine = in.readLine()) != null)
		             a.append(inputLine);
		         in.close();
		
		         return a.toString();
			 }catch(IOException e){
				 return "";
			 }
	     }
		 
		
}
