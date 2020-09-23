import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*

   Google interview guarantee the songs are shuffled

 */
public class CdPlayer {

    List<Song> songs;

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
            Song song = (Song) o;
            return Objects.equals(name, song.name);
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
        Random rand = new Random();
        int size = songs.size();
        List<Song> copy = new ArrayList<>(songs);
        for ( int i = 0; i < size; ++i ){
            int j = (int)(i + (rand.nextFloat() * size )) %size;
            Collections.swap(copy, i ,  j );
        }

        for ( int i = 0, j = 0; i < size; ++j ){
            Song a = songs.get(i);
            Song b = copy.get(j%size);
            if ( b != null && !a.equals(b) ){
                songs.set(i++,b);
                copy.set(j%size,null);
            }
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