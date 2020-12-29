import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SimplifyPath {

    public static String simplifyPath(String path) {
        String[] paths =  path.split("/");
        List<String> l = new LinkedList<>( Arrays.asList(paths) );
        for(var iter = l.listIterator(); iter.hasNext(); ){
            String val = iter.next();
            if ( val.equals("") ) {
                iter.remove();
            }
            else if ( val.equals(".") ){
                iter.remove();
            }
            else if ( val.equals("..") ){
                iter.remove();
                if ( iter.hasPrevious() ){
                    iter.previous();
                    iter.remove();
                }
            }
        }
        if ( l.size() == 0 ) return "/";
        StringBuilder canonicalPath = new StringBuilder();
        for( String part: l ){
            canonicalPath.append("/").append(part);
        }
        return canonicalPath.toString();
    }

    public static void main(String[] args){
        String path = "//path/../path1/";
        System.out.println(simplifyPath(path));
    }
}
