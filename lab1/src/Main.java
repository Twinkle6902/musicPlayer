import composite.MusicComponent;
import composite.Playlist;
import composite.TrackLeaf;
import filter.GenreFilter;
import filter.TrackFilter;
import model.Track;
import player.Player;
import sort.SortByDuration;
import sort.TrackSortStrategy;
import state.StoppedState;
import strategy.*;
import factory.TrackFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // создание треков
        Track t1 = new Track.Builder()
                .setTitle("Rock 1")
                .setArtist("Artist A")
                .setGenre("Rock")
                .setDuration(200)
                .setBitrate(320)
                .build();

        Track t2 = new Track.Builder()
                .setTitle("Pop 1")
                .setArtist("Artist B")
                .setGenre("Pop")
                .setDuration(180)
                .setBitrate(256)
                .build();

        Track t3 = new Track.Builder()
                .setTitle("Rock 2")
                .setArtist("Artist A")
                .setGenre("Rock")
                .setDuration(220)
                .setBitrate(320)
                .build();

        Track t4 = new Track.Builder()
                .setTitle("Jazz 1")
                .setArtist("Artist C")
                .setGenre("Jazz")
                .setDuration(300)
                .setBitrate(192)
                .build();

        // mp3 трек с заданным битрейтом
        Track t5 = TrackFactory.createMp3Track(
                "Rock Song",
                "Artist A",
                "Rock",
                200
        );


        //плейлисты
        MusicComponent rock1 = new TrackLeaf(t1);
        MusicComponent rock2 = new TrackLeaf(t3);
        MusicComponent pop1 = new TrackLeaf(t2);
        MusicComponent jazz1 = new TrackLeaf(t4);

        Playlist rockPlaylist = new Playlist();
        rockPlaylist.add(rock1);
        rockPlaylist.add(rock2);

        Playlist mixedPlaylist = new Playlist();
        mixedPlaylist.add(pop1);
        mixedPlaylist.add(jazz1);


        Playlist mainPlaylist = new Playlist();
        mainPlaylist.add(rockPlaylist);   // вложенный плейлист
        mainPlaylist.add(mixedPlaylist);  // ещё один вложенный

        System.out.println("=== Все треки в главном плейлисте ===");
        List<Track> allTracks = mainPlaylist.getTracks();
        for (Track t : allTracks) {
            System.out.println(t.getTitle() + " | " + t.getGenre());
        }

        //фильтрация
        System.out.println("\n=== Только Rock ===");
        TrackFilter rockFilter = new GenreFilter("Rock");
        List<Track> rockTracks = rockFilter.apply(allTracks);
        for (Track t : rockTracks) {
            System.out.println(t.getTitle());
        }

        //сортировка
        System.out.println("\n=== Сортировка по длительности ===");
        for (Track t : allTracks) {
            System.out.println(t.getTitle() + " - " + t.getDuration());
        }
        TrackSortStrategy strategy = new SortByDuration();
        strategy.sort(allTracks);
        System.out.println("\n=== После сортировки по длительности ===");
        for (Track t : allTracks) {
            System.out.println(t.getTitle() + " - " + t.getDuration());
        }

        //плеер
        Player player = new Player();
        player.setTracks(allTracks);
        player.setState(new StoppedState());

        //последовательный режим
        System.out.println("\n=== Sequential ===");
        player.setStrategy(new SequentialStrategy());

        player.play();
        player.next();
        player.next();
        player.previous();

        //случайный режим
        System.out.println("\n=== Random ===");
        player.setStrategy(new RandomStrategy());

        player.next();
        player.next();

        //повтор одного трека
        System.out.println("\n=== Repeat One ===");
        player.setStrategy(new RepeatOneStrategy());

        player.next();
        player.next();

        //повтор плейлиста
        System.out.println("\n=== Repeat Playlist ===");
        player.setStrategy(new RepeatAllStrategy());

        player.next();
        player.next();
        player.next();
        player.next();
        player.next();
        player.next();

        //состояния
        System.out.println("\n=== State Demo ===");

        player.pause();
        player.play();
        player.stop();
        player.pause(); // проверка поведения

        //фильтрация
        System.out.println("\n=== Только Rock воспроизведение ===");
        player.setTracks(rockTracks);
        player.setStrategy(new SequentialStrategy());

        player.play();
        player.next();
        player.next();
        player.next();
    }
}