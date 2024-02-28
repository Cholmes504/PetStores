package api.endpoints;

//only URLS
//https://petstore.swagger.io/v2 -> baseURL
//.io/v2/user; io/v2/user/{username}; -> endpoints



public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2" ; 
	
	//User models
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{username}";
	public static String update_url = base_url+"/user/{username}";
	public static String delete_url = base_url+"/user/{username}";
	
	//Store Models
	
	
	
	//Pet Models
	
}
