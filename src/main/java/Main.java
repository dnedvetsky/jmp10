import dbc.DatabaseActions;
import org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    private static DatabaseActions databaseActions = new DatabaseActions();


    public static void main(String[] args) {

        databaseActions.createFriendshipsTable();
        databaseActions.createPostsTable();
        databaseActions.createUsersTable();
        databaseActions.createLikesTable();
        addMultipleUsers(5);
        addMultipleFriendships(10);
        addMultiplePosts(10);
        addMultipleLikes(10);
        databaseActions.printNames(30, 15);
    }

    private static void addMultiplePosts(int postsNo) {
        int uID = databaseActions.getLatestID("Users");
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < postsNo; i++) {
            Random random = new Random();
            int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
            int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
            long randomDay = minDay + random.nextInt(maxDay - minDay);
            list.add(Arrays.asList(String.valueOf(1 + new Random().nextInt(uID - 1)),
                    RandomStringUtils.randomAlphabetic(15),
                    LocalDate.ofEpochDay(randomDay).getEra().toString()));
        }
        databaseActions.executePostsBatch(list);
    }

    private static void addMultipleUsers(int userNo) {
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < userNo; i++) {
            Random random = new Random();
            int minDay = (int) LocalDate.of(2000, 1, 1).toEpochDay();
            int maxDay = (int) LocalDate.of(2002, 1, 1).toEpochDay();
            long randomDay = minDay + random.nextInt(maxDay - minDay);
            list.add(Arrays.asList(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
                    LocalDate.ofEpochDay(randomDay).toString()));
        }
        databaseActions.executeUsersBatch(list);
    }

    private static void addMultipleFriendships(int frNo) {
        List<List<String>> list = new ArrayList<>();
        int ID = databaseActions.getLatestID("Users");
        for (int i = 0; i < frNo; i++) {
            Random random = new Random();

            int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
            int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
            long randomDay = minDay + random.nextInt(maxDay - minDay);
            list.add(Arrays.asList(String.valueOf(1 + new Random().nextInt(ID - 1)),
                    String.valueOf(1 + new Random().nextInt(ID - 1)),
                    LocalDate.ofEpochDay(randomDay).toString()));
        }
        databaseActions.executeFriendsBatch(list);
    }


    private static void addMultipleLikes(int frNo) {
        List<List<String>> list = new ArrayList<>();
        int uID = databaseActions.getLatestID("Users");
        int pID = databaseActions.getLatestID("Posts");
        for (int i = 0; i < frNo; i++) {
            Random random = new Random();

            int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
            int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
            long randomDay = minDay + random.nextInt(maxDay - minDay);
            list.add(Arrays.asList(String.valueOf(1 + new Random().nextInt(pID - 1)),
                    String.valueOf(1 + new Random().nextInt(uID - 1)),
                    LocalDate.ofEpochDay(randomDay).toString()));
        }
        databaseActions.executeLikesBatch(list);
    }
}