import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*

   Google interview guarantee the songs are shuffled

 */
public class CdPlayer {

    List<Song> songs;
    Random rand = new Random();

    public CdPlayer(List<Song> songs){
        this.songs = songs;
    }

    static class Song {
        final private String name;
        public Song(String name){
            this.name = name;
        }
        public String toString(){
            return this.name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Song that = (Song) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public List<Song> getSongs(){
        return this.songs;
    }

    public void shuffle(){
        int size = songs.size();
        for ( int i = size-1; i > 0 ;  --i ){
            int j = rand.nextInt(i);
            Collections.swap(this.songs,i,j);
        }
    }

    public static void main(String[] args){
        var songs = Stream.of("one","two","three","four","five","six","seven","eight","nine","ten").map(Song::new).collect(Collectors.toList());
        CdPlayer cdPlayer = new CdPlayer(songs);
        System.out.println(songs);
        cdPlayer.shuffle();
        System.out.println(cdPlayer.getSongs());
    }
}