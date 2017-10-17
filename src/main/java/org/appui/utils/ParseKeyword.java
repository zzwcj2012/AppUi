package appui.utils;

import java.util.List;
/*获取关键字*/
public class ParseKeyword {
	
	 public List<String> getKeywords(String p){
	        String reg = "(?<=(?<!\\\\)\\$\\{)(.*?)(?=(?<!\\\\)\\})";    
	        RegExp re = new RegExp();
	        List<String> list = re.find(reg, p);
	        return list;

	 }
	 
	    public static void main(String[] args) {
	        ParseKeyword p = new ParseKeyword();
	        System.out.println(p.getKeywords("a${a}a"));
	        System.out.println(p.getKeywords("a\\${a}a"));
	        System.out.println(p.getKeywords("a${a\\}a"));
	        System.out.println(p.getKeywords("a${a\\}a}a"));
	        System.out.println(p.getKeywords("a${a}a${"));
	        System.out.println(p.getKeywords("a${ab}a${a}"));
}
	    
}
