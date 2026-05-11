package model;

public class Track {
    private String title;
    private String artist;
    private String genre;
    private int duration;
    private int bitrate;

    private Track(Builder builder) {
        this.title = builder.title;
        this.artist = builder.artist;
        this.genre = builder.genre;
        this.duration = builder.duration;
        this.bitrate = builder.bitrate;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public int getBitrate() {
        return bitrate;
    }

    public static class Builder {
        private String title;
        private String artist;
        private String genre;
        private int duration;
        private int bitrate;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setArtist(String artist) {
            this.artist = artist;
            return this;
        }

        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setBitrate(int bitrate) {
            this.bitrate = bitrate;
            return this;
        }

        public Track build() {
            return new Track(this);
        }

    }
}