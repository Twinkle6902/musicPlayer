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

        Playlist rootPlaylist = new Playlist("Main");
        Playlist currentPlaylist = rootPlaylist;

        Player player = new Player();
        player.setState(new StoppedState());
        player.setStrategy(new SequentialStrategy());

        player.setTracks(currentPlaylist.getAllTracks());

        boolean running = true;

        while (running) {
            System.out.println("\nМузыкальный плеер");
            System.out.println("1. Создать плейлист (в корень)");
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
                    System.out.print("Имя плейлиста: ");
                    String playlistName = scanner.nextLine();
                    Playlist newPlaylist = new Playlist(playlistName);
                    rootPlaylist.add(newPlaylist);
                    System.out.println("Плейлист создан в корне");
                    break;

                case 2:
                    System.out.print("Имя родительского плейлиста: ");
                    String parentName = scanner.nextLine();
                    Playlist parent = rootPlaylist.findPlaylist(parentName);

                    if (parent == null) {
                        System.out.println("Плейлист не найден");
                        break;
                    }

                    System.out.print("Имя нового вложенного плейлиста: ");
                    String nestedName = scanner.nextLine();
                    parent.add(new Playlist(nestedName));
                    System.out.println("Вложенный плейлист добавлен");
                    break;

                case 3:
                    System.out.print("В какой плейлист добавить трек?: ");
                    String targetName = scanner.nextLine();
                    Playlist target = rootPlaylist.findPlaylist(targetName);

                    if (target == null) {
                        System.out.println("Плейлист не найден");
                        break;
                    }

                    System.out.print("Название: "); String title = scanner.nextLine();
                    System.out.print("Исполнитель: "); String artist = scanner.nextLine();
                    System.out.print("Жанр: "); String genre = scanner.nextLine();
                    System.out.print("Продолжительность (сек): "); int duration = scanner.nextInt();
                    System.out.print("Битрейт: "); int bitrate = scanner.nextInt();
                    scanner.nextLine();

                    Track track = new Track.Builder()
                            .setTitle(title)
                            .setArtist(artist)
                            .setGenre(genre)
                            .setDuration(duration)
                            .setBitrate(bitrate)
                            .build();

                    target.add(new TrackLeaf(track));

                    player.setTracks(currentPlaylist.getAllTracks());
                    System.out.println("Трек успешно добавлен");
                    break;

                case 4:
                    System.out.println("\nСтруктура библиотеки:");
                    rootPlaylist.showStructure("");
                    break;

                case 5:
                    System.out.print("Введите имя плейлиста для переключения: ");
                    String selectedName = scanner.nextLine();
                    Playlist selected = rootPlaylist.findPlaylist(selectedName);

                    if (selected == null) {
                        System.out.println("Плейлист не найден");
                        break;
                    }

                    currentPlaylist = selected;
                    player.setTracks(currentPlaylist.getAllTracks());
                    System.out.println("Переключено на плейлист: " + selectedName);
                    break;

                case 6: new PlayCommand(player).execute(); break;
                case 7: new PauseCommand(player).execute(); break;
                case 8: new StopCommand(player).execute(); break;
                case 9: new NextCommand(player).execute(); break;
                case 10: new PreviousCommand(player).execute(); break;

                case 11:
                    player.setStrategy(new SequentialStrategy());
                    System.out.println("Режим: Последовательно");
                    break;

                case 12:
                    player.setStrategy(new RandomStrategy());
                    System.out.println("Режим: Случайно");
                    break;

                case 13:
                    player.setStrategy(new RepeatOneStrategy());
                    System.out.println("Режим: Повтор одного трека");
                    break;

                case 14:
                    player.setStrategy(new RepeatAllStrategy());
                    System.out.println("Режим: Повтор плейлиста");
                    break;

                case 15:
                    player.setSortStrategy(new SortByDuration());
                    player.sortCurrentPlaylist();
                    System.out.println("\nОтсортированные треки в текущем плейлисте:");
                    for (Track t : currentPlaylist.getAllTracks()) {
                        System.out.println(t.getTitle() + " [" + t.getDuration() + " сек]");
                    }
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Неверный ввод");
            }
        }
    }
}