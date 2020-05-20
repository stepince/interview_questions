
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HeaderFormatter {

    private static class Heading {
        final int weight;
        final String text;
        Heading( int weight, String text ){
           this.weight = weight;
           this.text = text;
        }
    }

    private static class Node {
        final Heading heading;
        final List<Node> children = new ArrayList<>();
        Node( Heading heading ){
            this.heading = heading;
        }
    }

    public static List<Heading> parse(List<String> lines ){
        Pattern pattern = Pattern.compile("H(\\d) (.*)");
        List<Heading> headings = new ArrayList<>();
        for ( String line: lines ){
            Matcher m = pattern.matcher(line);
            if ( m.find() ){
                int w = Integer.parseInt(m.group(1));
                String t = m.group(2);
                headings.add( new Heading(w,t));
            }
        }
        return headings;
    }

    public static Node toOutline( List<Heading> headings){
        Stack<Node> st = new Stack<>();
        Heading firstHeading = headings.stream().findFirst().orElse(null);
        Node top = new Node(firstHeading);
        st.push(top);
        headings.stream().skip(1).forEach( h -> {
            while( st.peek().heading.weight >= h.weight ) {
                st.pop();
            }
            Node node = new Node(h);
            Node parent = st.peek();
            parent.children.add(node);
            st.push(node);
        });
        return top;
   }

   private static String toHtmlChild(Node node){
       StringBuilder sb = new StringBuilder();
       String headText = node.heading == null ? "" : node.heading.text;
       sb.append("<li>");
       sb.append( headText );
       sb.append("\n");
       if ( node.children.size() != 0 ) {
           sb.append("<ol>\n");
           for (Node n : node.children) {
               sb.append(toHtmlChild(n));
           }
           sb.append("</ol>\n");
       }
       sb.append("</li>\n");
       return sb.toString();
   }

    public static String toHtml(Node node){
        return node == null ? "" : "<ol>\n" + toHtmlChild(node) + "</ol>\n";
    }

    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("HeaderFormatter");
        ClassLoader classLoader = cls.getClassLoader();
        String input = "HeaderFormatter.txt";
        URL url = classLoader.getResource(input);
        assert url != null;
        List<String> lines = Files.lines(Paths.get(url.toURI())).filter(s -> !s.isEmpty() ).collect(Collectors.toList());
        List<Heading> headings = HeaderFormatter.parse(lines);
        Node outline = HeaderFormatter.toOutline(headings);
        String html = HeaderFormatter.toHtml(outline);
        System.out.println(html);
    }
}
