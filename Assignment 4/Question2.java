import java.util.*;

class Song {
    private String id;
    private String title;
    private String artist;
    private int durationSeconds;

    public Song(String id, String title, String artist, int durationSeconds) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }

    public String getFormattedDuration() {
        return String.format("%02d:%02d", durationSeconds / 60, durationSeconds % 60);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return Objects.equals(id, song.id) || 
               (title.equalsIgnoreCase(song.title) && artist.equalsIgnoreCase(song.artist));
    }

    @Override
    public int hashCode() {
        return Objects.hash(title.toLowerCase(), artist.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%s)", id, title, artist, getFormattedDuration());
    }
}

class MediaPlayerQueueEngine {
    private LinkedList<Song> playbackQueue = new LinkedList<>();
    private LinkedList<Song> recentHistory = new LinkedList<>();
    private final int MAX_HISTORY = 10;

    private ListIterator<Song> iterator;
    private Song currentSong = null;
    private boolean movingForward = true;

    public MediaPlayerQueueEngine() {
        this.iterator = playbackQueue.listIterator();
    }

    public void addSongToEnd(Song song) {
        playbackQueue.addLast(song);
        syncIterator();
    }

    public void addSongToFront(Song song) {
        playbackQueue.addFirst(song);
        syncIterator();
    }

    public void insertSongAt(int index, Song song) {
        if (index < 0 || index > playbackQueue.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        ListIterator<Song> it = playbackQueue.listIterator(index);
        it.add(song);
        syncIterator();
    }

    public int removeDuplicates() {
        HashSet<Song> seen = new HashSet<>();
        int removedCount = 0;
        Iterator<Song> it = playbackQueue.iterator();

        while (it.hasNext()) {
            Song s = it.next();
            if (!seen.add(s)) {
                it.remove();
                removedCount++;
            }
        }
        syncIterator();
        return removedCount;
    }

    public void playNext() {
        if (playbackQueue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        if (!movingForward) {
            if (iterator.hasNext()) iterator.next();
            movingForward = true;
        }

        if (iterator.hasNext()) {
            if (currentSong != null) {
                recordHistory(currentSong);
            }
            currentSong = iterator.next();
            System.out.println("▶ Playing Next: " + currentSong);
        } else {
            System.out.println("Reached the end of the playlist.");
        }
    }

    public void playPrevious() {
        if (playbackQueue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        if (movingForward) {
            if (iterator.hasPrevious()) iterator.previous();
            movingForward = false;
        }

        if (iterator.hasPrevious()) {
            currentSong = iterator.previous();
            System.out.println("◀ Playing Previous: " + currentSong);
        } else {
            System.out.println("Already at the start of the playlist.");
        }
    }

    private void recordHistory(Song song) {
        recentHistory.addFirst(song);
        if (recentHistory.size() > MAX_HISTORY) {
            recentHistory.removeLast();
        }
    }

    private void syncIterator() {
        if (currentSong != null) {
            int idx = playbackQueue.indexOf(currentSong);
            if (idx != -1) {
                iterator = playbackQueue.listIterator(idx + 1);
                movingForward = true;
                return;
            }
        }
        iterator = playbackQueue.listIterator();
        movingForward = true;
    }

    public void displayQueue() {
        System.out.println("\n--- Current Playback Queue ---");
        int i = 1;
        for (Song s : playbackQueue) {
            String marker = (s.equals(currentSong)) ? "▶ [PLAYING] " : "            ";
            System.out.println(marker + i + ". " + s);
            i++;
        }
        System.out.println("------------------------------\n");
    }

    public void displayHistory() {
        System.out.println("\n--- Recent Playback History ---");
        if (recentHistory.isEmpty()) {
            System.out.println("(History is empty)");
        } else {
            int i = 1;
            for (Song s : recentHistory) {
                System.out.println("  " + i + ". " + s);
                i++;
            }
        }
        System.out.println("-------------------------------\n");
    }
}

public class Question2 {
    public static void main(String[] args) {
        MediaPlayerQueueEngine player = new MediaPlayerQueueEngine();

        player.addSongToEnd(new Song("S1", "Bohemian Rhapsody", "Queen", 354));
        player.addSongToEnd(new Song("S2", "Hotel California", "Eagles", 391));
        player.addSongToEnd(new Song("S3", "Stairway to Heaven", "Led Zeppelin", 482));

        System.out.println("Adding 'Starboy' to FRONT (Priority Queueing)...");
        player.addSongToFront(new Song("S0", "Starboy", "The Weeknd", 230));

        System.out.println("Inserting 'Blinding Lights' at index 2...");
        player.insertSongAt(2, new Song("S4", "Blinding Lights", "The Weeknd", 200));

        player.addSongToEnd(new Song("S1", "Bohemian Rhapsody", "Queen", 354));
        player.addSongToEnd(new Song("S2", "Hotel California", "Eagles", 391));

        System.out.println("\nQueue before removing duplicates:");
        player.displayQueue();

        System.out.println("Removing duplicate entries efficiently with HashSet...");
        int removed = player.removeDuplicates();
        System.out.println("Removed " + removed + " duplicates.");

        System.out.println("\nQueue after deduplication:");
        player.displayQueue();

        System.out.println("Navigating FORWARD:");
        player.playNext();
        player.playNext();
        player.playNext();

        player.displayQueue();

        System.out.println("Navigating BACKWARD:");
        player.playPrevious();
        player.playPrevious();

        player.displayQueue();

        player.displayHistory();
    }
}
