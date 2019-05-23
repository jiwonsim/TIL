import java.util.Random;

class Hero {
    String name;
    int hp = 100;

    Hero(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    void punch(Hero enemy) {
        enemy.hp -= 10;
//        System.out.println("["+this.name+"]의 펀치");
        System.out.printf("[%s]의 펀치\n", this.name);
//        System.out.println(enemy.name + ":" + enemy.hp +"/100");
        System.out.printf("%s : %d/100\n\n", enemy.name, enemy.hp);
    }

    static void battle(Hero hero1, Hero hero2) throws InterruptedException {
        Hero loser = hero1, winner = hero1;
        while (hero1.hp > 0 && hero2.hp > 0) {
            Random random = new Random();
            boolean chance = random.nextBoolean();

            Hero attacker, defender;
            if (chance) {
                attacker = hero1;
                defender = hero2;
            }
            else {
                attacker = hero2;
                defender = hero1;
            }

            attacker.punch(defender);

            Thread.sleep(100);
        }

        if (hero1.hp > 0) {
            System.out.printf("WINNER : %s \n", hero1.name);
            return;
        }
        System.out.printf("WINNER : %s \n", hero2.name);


    }


    @Override
    public String toString() {
        return "Hero[" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ']';
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException{
        Hero doodoo = new Hero("두두");
        Hero chankoo = new Hero("찬구");

        Hero.battle(doodoo, chankoo);
    }
}
