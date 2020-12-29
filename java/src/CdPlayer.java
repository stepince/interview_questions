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
        List<Song> songsCopy = new ArrayList<>(songs);
        for ( int i = 0; i < size; ++i ){
            Collections.swap(songsCopy, i, rand.nextInt(size) );
        }

        for ( int i = 0, j = 0; i < size; ++i, ++j ){
            int jIdx = j%size;
            Song song = songsCopy.get(i);
            while ( song == null || song.equals(songs.get(i)) ) {
                jIdx=++j%size ;
                song= songsCopy.get(jIdx);
            }
            songs.set(i,song);
            songsCopy.set(jIdx,null);
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