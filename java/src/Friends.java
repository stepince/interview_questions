import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/*
    Amazon interview question.
    Find intermediate friends.
    Degrees of separation

    ALGORITHM is BFS with a predecessor map
 */
public class Friends {

    // vertices
    public Map<String, List<String>> friendsMap = new HashMap<>();

    // This is not needed for implementation.
    // Can assume getFriends() method exist
    public Friends() throws Exception {
        URL url  = ClassLoader.getSystemResource("Friends.txt");
        Files.lines(Paths.get(url.toURI())).filter(s->!s.isEmpty()).forEach(line -> {
            String[] friendList = line.split(":");
            if (friendList.length != 2) {
                System.err.println("Invalid line: no friends, " + line);
                return;
            }
            String person = friendList[0].trim();
            friendsMap.computeIfAbsent(person, (k)-> new ArrayList<>()).addAll(Arrays.asList(friendList[1].trim().split("\\s*,\\s*")));
            for( String friend: friendsMap.get(person)) {
                friendsMap.computeIfAbsent(friend, (k)-> new ArrayList<>()).add(person);
            }
        });
    }
    public List<String> getFriends(String person){
        return friendsMap.get(person);
    }

    public List<String> findIntermediateFriendsBfs(String personA, String personB){
        Set<String> visited = new HashSet<>();
        LinkedList<String> path = new LinkedList<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.add(personA);
        visited.add(personA);
        Map<String,String> predMap = new HashMap<>();

        outer: while( !queue.isEmpty() ){
            String friend = queue.remove();
            for ( String nei : getFriends(friend)){
                if ( nei.equals(personB)){
                    predMap.put(nei,friend);
                    break outer;
                }
                else if ( !visited.contains(nei) ){
                    visited.add(nei);
                    queue.add(nei);
                    predMap.put(nei,friend);
                }
            }
        }

        String cur = personB;
        while ( predMap.containsKey(cur) ){
            // ignore dest person
            if ( !cur.equals(personB) ) path.addFirst(cur);
            cur = predMap.get(cur);
        }
        return path;
    }

    public static void main( String[] args ) throws Exception {
        String personA = "amanda";
        String personB = "fred";
        Friends friends = new Friends();
        System.out.println( friends.findIntermediateFriendsBfs(personA,personB));
    }
}
