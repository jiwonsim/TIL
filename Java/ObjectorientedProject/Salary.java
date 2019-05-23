import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int arg[] = new int[3];
        arg[0] = 2;
        arg[1] = 5;
        arg[2] = 9;
        Developer doo = new Developer("Doori", arg[0]);
        Developer chan = new Developer("Chankoo", arg[1]);
        Developer won = new Developer("Jiwon", arg[2]);

        System.out.println(doo.salary());
        System.out.println(chan.salary());
        System.out.println(won.salary());
    }
}

class Developer {
    String name;
    int career;

    Developer(String name, int career) {
        this.name = name;
        this.career = career;
    }

    String level() {
        if (career < 3) return "초급";
        else if (career < 7) return "중급";
        else return "고급";
    }

    int salary() {
        if (level().equals("초급")) return 2800;
        else if (level().equals("중급")) return 3500;
        else return 4500;
    }
}
