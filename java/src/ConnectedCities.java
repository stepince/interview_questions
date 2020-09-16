import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;

/**
 * This class determine whether two cities are connected. 
 *   
 * Usage: java ConnectedCities city1 city2
 * Example java ConnectedCities boston "new york"
 *
 *
 * Note: ConnectedCities.txt has the list of connected cities
 */ 

public class ConnectedCities {

    private final Map<String, Set<String>> cities = new HashMap<>();

    public ConnectedCities() throws Exception {
        Class<?> cls = Class.forName("ConnectedCities");
        ClassLoader classLoader = cls.getClassLoader();
        URL url = classLoader.getResource("ConnectedCities.txt");
        assert url != null;
        Files.lines(Paths.get(url.toURI())).filter(s->!s.isEmpty()).forEach( line -> {
            String[] cityPair = line.split("\\s*,\\s*");
            if (cityPair.length != 2) {
                System.err.println("Invalid line: no city pair, " + line);
                return;
            }
            addCityConnection(cityPair[0], cityPair[1]);
            addCityConnection(cityPair[1], cityPair[0]);
        });
        if ( this.cities.size() == 0 ) {
            System.err.println("Invalid input file: no cites, ConnectedCities.txt");
        }
    }

    private void addCityConnection(String start, String end ) {
        start = start.toLowerCase().trim();
        end = end.toLowerCase().trim();
        cities.computeIfAbsent(start, k->new HashSet<>()).add(end);
    }

   /**
   * This method determines whether two cities are connected
   * @param cityStart The start city
   * @param cityEnd The end city
   * @return boolean This returns whether a city is connected
   */
    public boolean isConnected(String cityStart, String cityEnd) {
        Set<String> visited = new HashSet<>();
        Stack<String> st = new Stack<>();
        visited.add(cityStart);
        st.push(cityStart);
        while ( ! st.empty() ) {
            String city = st.pop();
            for ( String child: this.cities.getOrDefault(city,new HashSet<>()) ){
                if ( child.equals(cityEnd) ) return true;
                if (visited.contains(child)) continue;
                visited.add(child);
                st.push(child);
            }
        }
        return false;
    }

    public static void main( String[] args ) throws Exception {
//        String start = args[0].toLowerCase().trim();
//        String end = args[1].toLowerCase().trim();
        String start = "nyc";
        String end = "la";
        ConnectedCities connectedCities = new ConnectedCities();
        if ( connectedCities.isConnected( start, end) ) {
            System.out.println("yes");
        }
        else {
            System.out.println("no");
        }
    }
}