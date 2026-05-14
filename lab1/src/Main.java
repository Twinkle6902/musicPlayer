

import command.*;
import composite.*;
import model.Track;
import player.Player;
import state.StoppedState;
import strategy.*;
import sort.SortByDuration;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Playlist rootPlaylist =
                new Playlist("Main");

        Playlist currentPlaylist =
                rootPlaylist;

        Player player = new Player();

        player.setState(
                new StoppedState()
        );

        player.setStrategy(
                new SequentialStrategy()
        );

        player.setTracks(
                currentPlaylist.getTracks()
        );

        boolean running = true;

        while (running) {

            System.out.println("\nМузыкальный плеер");

            System.out.println("1. Создать плейлист");
            System.out.println("2. Создать вложенный плейлист");
            System.out.println("3. Добавить трек в плейлист");
            System.out.println("4. Показать структуру плейлистов");
            System.out.println("5. Выбрать плейлист");

            System.out.println("6. Воспроизвести");
            System.out.println("7. Пауза");
            System.out.println("8. Стоп");
            System.out.println("9. Следующий");
            System.out.println("10. Предыдущий");

            System.out.println("11. Последовательный режим");
            System.out.println("12. Случайный режим");
            System.out.println("13. Повторять один трек");
            System.out.println("14. Повторять весь плейлист");

            System.out.println("15. Сортировка по длительности");

            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print(
                            "Имя плейлиста: "
                    );

                    String playlistName =
                            scanner.nextLine();

                    Playlist playlist =
                            new Playlist(playlistName);

                    rootPlaylist.add(playlist);

                    System.out.println(
                            "Плейлист создан"
                    );

                    break;

                case 2:

                    System.out.print(
                            "Родительский плейлист: "
                    );

                    String parentName =
                            scanner.nextLine();

                    Playlist parent =
                            rootPlaylist.findPlaylist(
                                    parentName
                            );

                    if (parent == null) {

                        System.out.println(
                                "Плейлист не найден"
                        );

                        break;
                    }

                    System.out.print(
                            "Имя вложенного плейлиста: "
                    );

                    String nestedName =
                            scanner.nextLine();

                    Playlist nested =
                            new Playlist(nestedName);

                    parent.add(nested);

                    System.out.println(
                            "Вложенный плейлист добавлен"
                    );

                    break;

                case 3:

                    System.out.print(
                            "Имя плейлиста: "
                    );

                    String targetPlaylist =
                            scanner.nextLine();

                    Playlist target =
                            rootPlaylist.findPlaylist(
                                    targetPlaylist
                            );

                    if (target == null) {

                        System.out.println(
                                "Плейлист не найден"
                        );

                        break;
                    }

                    System.out.print("Название: ");
                    String title =
                            scanner.nextLine();

                    System.out.print("Исполнитель: ");
                    String artist =
                            scanner.nextLine();

                    System.out.print("Жанр: ");
                    String genre =
                            scanner.nextLine();

                    System.out.print("Продолжительность: ");
                    int duration =
                            scanner.nextInt();

                    System.out.print("Битрейт: ");
                    int bitrate =
                            scanner.nextInt();

                    scanner.nextLine();

                    Track track =
                            new Track.Builder()
                                    .setTitle(title)
                                    .setArtist(artist)
                                    .setGenre(genre)
                                    .setDuration(duration)
                                    .setBitrate(bitrate)
                                    .build();

                    target.add(
                            new TrackLeaf(track)
                    );

                    player.setTracks(
                            currentPlaylist.getTracks()
                    );

                    System.out.println(
                            "Трек добавлен"
                    );

                    break;

                case 4:

                    rootPlaylist.showStructure("");

                    break;

                case 5:

                    System.out.print("Имя плейлиста: ");

                    String selectedName =
                            scanner.nextLine();

                    Playlist selected =
                            rootPlaylist.findPlaylist(
                                    selectedName
                            );

                    if (selected == null) {

                        System.out.println(
                                "Плейлист не найден"
                        );

                        break;
                    }

                    currentPlaylist = selected;

                    player.setTracks(
                            currentPlaylist.getTracks()
                    );

                    System.out.println(
                            "Текущий плейлист: "
                                    + selectedName
                    );

                    break;

                case 6:

                    new PlayCommand(player)
                            .execute();

                    break;

                case 7:

                    new PauseCommand(player)
                            .execute();

                    break;

                case 8:

                    new StopCommand(player)
                            .execute();

                    break;

                case 9:

                    new NextCommand(player)
                            .execute();

                    break;

                case 10:

                    new PreviousCommand(player)
                            .execute();

                    break;

                case 11:

                    player.setStrategy(
                            new SequentialStrategy()
                    );

                    System.out.println(
                            "Последовательный режим активирован"
                    );

                    break;

                case 12:

                    player.setStrategy(
                            new RandomStrategy()
                    );

                    System.out.println(
                            "Случайный режим активирован"
                    );

                    break;

                case 13:

                    player.setStrategy(
                            new RepeatOneStrategy()
                    );

                    System.out.println(
                            "Режим повторения одного трека активирован"
                    );

                    break;

                case 14:

                    player.setStrategy(
                            new RepeatAllStrategy()
                    );

                    System.out.println(
                            "Режим повторения плейлиста активирован"
                    );

                    break;

                case 15:

                    player.setSortStrategy(
                            new SortByDuration()
                    );

                    player.sortCurrentPlaylist();

                    System.out.println("\nОтсортированные треки:");

                    for (Track t : currentPlaylist.getTracks()) {

                        System.out.println(
                                t.getTitle()
                                        + " - "
                                        + t.getDuration()
                        );
                    }

                    break;

                case 0:

                    running = false;

                    break;

                default:

                    System.out.println(
                            "Неизвестная команда"
                    );
            }
        }
    }
}