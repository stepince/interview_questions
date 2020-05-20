import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CdPlayer {

    private Boolean on = true;
    static class Song {
        final private String name;
        public Song(String name){
            this.name = name;
        }
        public String toString(){
            return this.name;
        }
    }

    public synchronized  void play(Song s){
        assert s != null;
    }

    private void shuffle( List<Song> songs ){
        Random rand = new Random();
        int size = songs.size();
        for ( int i = 0; i < songs.size(); ++i ){
            Collections.swap(songs, i ,  (int)(i + (rand.nextFloat() * size )) % size );
        }
    }

    public void stop(){
        this.on = false;
    }

    public void playAll(List<Song> songs){
        this.on = true;
        outer: while(true) {
            shuffle(songs);
            for (Song song : songs) {
                synchronized (this) {
                    if (!this.on) break outer;
                }
                play(song);
            }
        }
    }

    public static void main(String[] args){
        var songs = Stream.of("one","two","three","four","five","six","seven","eight","nine","ten").map(Song::new).collect(Collectors.toList());
        CdPlayer cdPlayer = new CdPlayer();
        new Thread(() -> cdPlayer.playAll(songs)).start();
        try {
            Thread.sleep(1000);
        }
        catch( Exception e){
            System.err.println(e.toString());
        }
        cdPlayer.stop();
        System.out.println(songs);
    }




















}