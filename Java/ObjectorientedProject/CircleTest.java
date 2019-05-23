/*
Circle 객체 만들기
문제
요구사항에 맞춰 Circle 클래스를 작성하고, 이를 실행하시오.

요구사항
프로그램 구성
두 개의 클래스로 프로그램을 작성할 것.

CircleTest 클래스
Circle 객체를 테스트
Circle 클래스
원을 나타내는 객체
Circle 클래스
Circle의 필드 구성

반지름
Circle 클래스 생성자 구성

매개변수가 없는 디폴트 생성자
반지름을 1로 초기화
반지름을 매개변수로 하는 생성자
입력 값으로 반지름 초기화
Circle 클래스는 메소드 구성

반지름에 대한 게터/세터

반지름을 반환, 반지름을 수정
원의 면적을 계산하는 메소드

면적 = 반지름 * 반지름 * Math.PI
toString 메소드

반지름 정보를 문자열로 반환
CircleTest 클래스
main() 메소드를 갖으며 이를 통해 아래의 동작을 구현.

원 객체 생성: circle1
circle1의 반지름을 2로 수정
원객체를 생성하되, 반지름이 1~4 범위 내 임의의 정수를 갖도록 함: circle2
circle1을 출력 by toString()
circle2를 출력 by toString()
circle1의 면적을 출력
circle2의 면적을 출력
*/


public class CircleTest {
    public static void main(String[] args) {
        Circle circle1 = new Circle();
        circle1.setRadius(2);
        Circle circle2 = new Circle();
        circle2.setRadius(4);
        System.out.println(circle1.toString());
        System.out.println(circle2.toString());
        System.out.println(String.format("%.2f", circle1.calcSize()));
        System.out.println(String.format("%.2f", circle2.calcSize()));
    }
}


class Circle {
    int radius;

    Circle() {
        this.radius = 1;
    }


    Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double calcSize() {
        return radius * radius * Math.PI;
    }

    @Override
    public String toString() {
        return "Circle [" +
                "radius=" + radius +
                ']';
    }
}