package factory;

import model.Track;

public class TrackFactory {

    private TrackFactory() {}

    public static Track createMp3Track(
            String title,
            String artist,
            String genre,
            int duration
    ) {

        return new Track.Builder()
                .setTitle(title)
                .setArtist(artist)
                .setGenre(genre)
                .setDuration(duration)
                .setBitrate(320)
                .build();
    }

    public static Track createFlacTrack(
            String title,
            String artist,
            String genre,
            int duration
    ) {

        return new Track.Builder()
                .setTitle(title)
                .setArtist(artist)
                .setGenre(genre)
                .setDuration(duration)
                .setBitrate(1000)
                .build();
    }
}