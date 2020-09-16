import java.util.*;

/**
 * https://leetcode.com/problems/tweet-counts-per-frequency/
 *
 *
 * Your TweetCounts object will be instantiated and called as such:
 * TweetCounts obj = new TweetCounts();
 * obj.recordTweet(tweetName,time);
 * List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 */

public class TweetCounts {

    Map<String, SortedSet<Integer>> map;

    public TweetCounts() {
        map = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        this.map.computeIfAbsent(tweetName, key->new TreeSet<>()).add(time);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        int interval;
        if ( freq.equals("minute") ){
            interval=60;
        }
        else if ( freq.equals("hour") ){
            interval=60*60;
        }
        else {
            interval=60*60*24;
        }
        List<Integer> l = new ArrayList<>();
        for ( int i = startTime; i <= endTime; i+=interval ){
            int end = Math.min(i+interval, endTime + 1);
            int size = this.map.get(tweetName).subSet(i,end).size();
            l.add(size);
        }
        return l;
    }

}

