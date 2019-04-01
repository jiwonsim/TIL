# Web Server와 WAS

**오늘의 목표** 
WAS와 웹 서버의 차이를 알아보자. 

## Web Server
소프트웨어와 하드웨어로 구분되며 하드웨어는 말 그대로 Web Server가 설치되어 있는 컴퓨터를 말한다. 그리고 소프트웨어 Web Server란 브라우저 클라이언트로부터 HTTP 요청을 받아들이고 HTML 등의 웹 페이지 문서에 반응하는 컴퓨터 프로그램이다.  
클라이언트가 서버에 페이지 요청을 하면 요청을 받아 정적 콘텐츠를 제공하는 서버이며, 클라이언트에서 요청이 올 때 가장 앞에서 요청에 대한 처리를 한다.  
HTTP 프로토콜을 기반으로 하여 브라우저의 요청을 서비스하는 기능을 담당한다.  
ex. Apache, Nginx

## WAS(Web Application Server)
HTTP를 통해 컴퓨터나 장치에 어플리케이션을 수행해주는 미들웨어(소프트웨어 엔진)이다. 동적 서버 콘텐츠를 수행한다는 것으로 일반 Web Server와 구별되며, 주로 Database Server와 같이 수행한다. 한국에서는 WAS로 통칭하지만, 영어권에서는 Application Server로 불린다. Web Server 기능을 구조적으로 분리하여 처리하고자 하는 목적으로 제시된 것이다. 크게 Web Server의 기능과 Container 기능으로 구성한다.  
ex. Tomcat, Jeus, JBoss

## 정리하자면
**Web Server**는 HTML 문서같은 정적 콘텐츠(HTTP Protocol을 통해 읽힐 수 있는 문서)를 처리하는 것이고,  
**Web Application Server**는 ASP, PHP, JSP 등 개발 언어를 읽고 처리하여 동적 컨텐츠, 웹 응용 프로그램 서비스를 처리하는 것이다.  
처리하는 기능은 나누어져 있지만 요새 WAS에는 Web Server 기능을 포함하고 있다고 한다. 






<hr/>
참고 사이트  
https://jeong-pro.tistory.com/84  
https://okky.kr/article/243427  
https://limmmee.tistory.com/4