package lab_20;
import java.util.*;
import java.io.*;

public class Jukebox1 {
	ArrayList<Song> songList = new ArrayList<Song>();
	public static void main(String[] args) {
		new Jukebox1().go();
	}
	class ArtistCompare implements Comparator<Song>{
		public int compare(Song one, Song two) {
			return one.getArtist().compareTo(two.getArtist());
		}
	}
	public void go() {
		System.out.println("_____________________________songList_____________________________");
		getSongs();
		System.out.println(songList);
		songList.clear();
		System.out.println();
		
		System.out.println("_____________________________songList + sort_____________________________");
		getSongs();
		Collections.sort(songList);
		System.out.println(songList);
		songList.clear();
		System.out.println();
		
		System.out.println("_____________________________songList + artistSort_____________________________");
		getSongs();
		ArtistCompare artistCompare = new ArtistCompare();
		Collections.sort(songList, artistCompare);
		System.out.println(songList);
		songList.clear();
		System.out.println();
		
		System.out.println("_____________________________songHashSet_____________________________");
		getSongs();
		HashSet<Song> songHashSet = new HashSet<Song>();
		songHashSet.addAll(songList);
		System.out.println(songHashSet);
		songList.clear();
		System.out.println();
		
		System.out.println("_____________________________songTreeSet_____________________________");
		getSongs();
		TreeSet<Song> songTreeSet = new TreeSet<Song>();
		songTreeSet.addAll(songList);
		System.out.println(songTreeSet);
		songList.clear();
		System.out.println();
	}
	void getSongs() {
		try {
			File file = new File("SongList.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine())!=null) addSong(line);
			reader.close();
		}catch(Exception ex) {ex.printStackTrace();}
	}
	void addSong(String lineToParse) {
		String[] tokens = lineToParse.split("/");
		Song nextSong = new Song(tokens[0],tokens[1],tokens[2],tokens[3]);
		songList.add(nextSong);
	}
	class Song implements Comparable<Song>{
		String title, artist, rating, bpm;
		Song(String t, String a, String r, String b){
			title = t; artist = a; rating = r; bpm = b;
		}
		public String getTitle() 	{return title;}
		public String getArtist() 	{return artist;}
		public String getRating() 	{return rating;}
		public String getBpm() 		{return bpm;}
		public String toString() 	{return title;}
		public int compareTo(Song s) {
			return title.compareTo(s.getTitle());
		}
		public boolean equals(Object aSong) {
			Song s = (Song) aSong;
			return getTitle().equals(s.getTitle());
		}
		public int hashCode() {
			return title.hashCode();
		}
	}
}



















