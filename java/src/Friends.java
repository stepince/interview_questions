import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/*
    Amazon interview question.
    Find intermediate friends.
    Degrees of separation
 */
public class Friends {

    // vertices
    public Map<String, Set<String>> friendsMap = new HashMap<>();

    // This is not needed for implementation.
    // Can assume getFriends() method exist
    public Friends() throws Exception {
        Class<?> cls = Class.forName("Friends");
        ClassLoader classLoader = cls.getClassLoader();
        URL url = classLoader.getResource("Friends.txt");
        assert url != null;
        Files.lines(Paths.get(url.toURI())).filter(s->!s.isEmpty()).forEach(line -> {
            String[] friendList = line.split(":");
            if (friendList.length != 2) {
                System.err.println("Invalid line: no friends, " + line);
                return;
            }
            String person = friendList[0].trim();
            friendsMap.computeIfAbsent(person, (k)-> new HashSet<>()).addAll(Arrays.asList(friendList[1].trim().split("\\s*,\\s*")));
            for( String friend: friendsMap.get(person)) {
                friendsMap.computeIfAbsent(friend, (k)-> new HashSet<>()).add(person);
            }
        });
        if ( this.friendsMap.size() == 0 ) {
            System.err.println("Invalid input file: no cites, Friends.txt");
        }
    }
    public Set<String> getFriends(String person){
        return friendsMap.get(person);
    }

    // dfsVisit
    public boolean findIntermediateFriendsUtil(String personA, String personB, Stack<String> path, Set<String> visited){
        visited.add(personA);
        path.push(personA);
        for ( String friend: getFriends(personA) ){
             if ( personB.equals(friend)) return true;
             if ( visited.contains(friend)) continue;
             if ( findIntermediateFriendsUtil(friend, personB, path, visited ) ) return true;
        }
        path.pop();
        return false;
    }

    // dfs (find path)
    public List<String> findIntermediateFriends(String personA, String personB){

        for ( String friend: getFriends(personA) ){
            Stack<String> path = new Stack<>();
            if ( findIntermediateFriendsUtil(friend, personB, path, new HashSet<>(Collections.singletonList(personA)) ) ) {
                return path;
            }
        }
        return null;
    }

    public static void main( String[] args ) throws Exception {
        String personA = "amanda";
        String personB = "fred";
        System.out.println( new Friends().findIntermediateFriends(personA,personB));
    }
}
