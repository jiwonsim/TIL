class User {
    String name;
    boolean isFemale;
    double point;

    User(String name, boolean isFemale) {
        this.name = name;
        this.isFemale = isFemale;
        this.point = Math.random() * 5;
    }

    @Override
    public String toString() {
        return String.format("%s(%s : %.1f)", this.name, this.isFemale ? "여" : "남", this.point);
    }

    static void matching(User[] users) {
        for (int i = 0; i < users.length; i++) {
            for (int j = i + 1; j < users.length; j++) {
                User user1 = users[i];
                User user2 = users[j];

                if (Math.abs(user1.point - user2.point) < 0.8) {
                    if (user1.isFemale != user2.isFemale) {
                        System.out.println(user1.toString() + " - " + user2.toString());
                    }
                }
            }
        }
    }
}

public class Main {
    static boolean FEMALE = true, MALE = false;



    public static void main(String[] args) {
        User[] users = new User[6];
        users[0] = new User("두리", MALE);
        users[1] = new User("바니", FEMALE);
        users[2] = new User("구찌", MALE);
        users[3] = new User("다롱", FEMALE);
        users[4] = new User("초롱", MALE);
        users[5] = new User("까미", FEMALE);

        User.matching(users);
    }
}