package appui.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*正则类*/
public class RegExp {
	public boolean match(String reg, String str) {
        return Pattern.matches(reg, str);
    }
  
    public List<String> find(String reg, String str) {
        Matcher matcher = Pattern.compile(reg).matcher(str);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }
}
