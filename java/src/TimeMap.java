import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/*




 */


class TimeMap {

    Map<String, TreeMap<Integer,String>> map = new HashMap<>();
    /** Initialize your data structure here. */
    public TimeMap() {

    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, (k)->new TreeMap<>()).put(timestamp,value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer,String> tree =  map.get(key);
        if ( tree == null ) return "";
        Integer k = tree.floorKey(timestamp);
        return k == null ? "" : tree.get(k);
    }

    /**
     * Your TimeMap object will be instantiated and called as such:
     * TimeMap obj = new TimeMap();
     * obj.set(key,value,timestamp);
     * String param_2 = obj.get(key,timestamp);
     */

    public static void main(String[] args){
        TimeMap map = new TimeMap();
        map.set("foo", "bar2", 4);
        System.out.println(map.get("foo", 4));
        System.out.println(map.get("foo", 5));
    }
}