import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PackageDependencies {

    Map<String, Set<String>> vertices  = new HashMap<>();

    public void add(String node, String nei){
        vertices.computeIfAbsent(node, (key)->new HashSet<>()).add(nei);
    }

    void topSortUtil(String node, Set<String> visited, Deque<String> deq ){
        visited.add(node);
        for ( String child: vertices.getOrDefault(node, Collections.emptySet()) ){
            if ( !visited.contains(child)) {
                topSortUtil(child, visited, deq);
            }
        }
        deq.addFirst(node);
    }

    public List<String> topSort(){
        LinkedList<String> l = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        for ( String n: vertices.keySet() ){
            if ( !visited.contains(n)) {
                topSortUtil(n,visited, l);
            }
        }
        return l;
    }

    public static void main(String[] args ) throws Exception {
        Class<?> cls = Class.forName("PackageDependencies");
        ClassLoader classLoader = cls.getClassLoader();
        URL resource = classLoader.getResource("PackageDependencies.txt");
        assert resource != null;
        List<String> lines = Files.lines(Paths.get(resource.toURI())).filter(l->!l.isEmpty()).collect(Collectors.toList());
        PackageDependencies packageDependencies = new PackageDependencies();

        for ( String line: lines ){
            String[] parts = line.split(":");
            String item = parts[0].trim();
            if ( parts.length > 1 ) {
                String dependencies = parts[1].trim();
                for (String parent : dependencies.split(",")) {
                    packageDependencies.add(parent, item);
                }
            }
        }
        System.out.println(packageDependencies.topSort());
    }
}
