#APACHE TOMCAT
## 다운로드 (MAC 기준)
1. [아파치 톰캣 사이트](http://tomcat.apache.org/download-80.cgi) 에 접속하여 Tomcat 8을 받는다. 
2. tar.gz 파일을 다운로드 받는다. 
3. 설치

```bash
$ mv ~/Downloads/apache-tomcat-8.0.9 /usr/local

// 설치
$ sudo rm -f /Library/Tomcat
$ sudo ln -s /usr/local/apache-tomcat-8.0.9 /Library/Tomcat
$ sudo chown -R <login_id> /Library/Tomcat
$ sudo chmod +x /Library/Tomcat/bin/*.sh

// 시작
$ /Library/Tomcat/bin/startup.sh
$ /Library/Tomcat/bin/shutdown.sh
```
<br>


## 개념
 
**Apache**는 아파치 소프트웨어 재단의 오픈소스 프로젝트이다. http 웹 서버로 불리며, 클라이언트 요청(http 요청)이 왔을 때만 응답하는 정적 웹페이지에 사용된다.  
**Tomcat**은 Dynamic Web을 만들기 위한. Web Container, Servlet Container라고 불리며 웹 서버에서 정적으로 처리해야할 데이터를 제외한 JSP, ASP, PHP 등은 Web Container(=Tomcat)에게 전달한다.  

  
**Apache Tomcat**은 Servlet Container(또는 Web Container)만 있는 Web Application Server이다. 
Tomcat이 Apache의 기능 일부를 가져와서 제공해주는 형태이기 때문에 같이 합쳐서 부른다. 

1. Apache만 사용하면 정적인 웹페이지만 처리가 가능하다. 
2. Tomcat만 사용하면 동적인 웹페이지 처리가 가능하지만 Apache에서 필요한 기능을 못 가져온다. 또한 여러 사용자가 요청할 시에 Tomcat에 과부하가 걸린다.
3. Apache와 Tomcat을 같이 쓰면 Apache는 정적인 데이터만 처리하고, JSP 처리는 Web Container로 보내주어 분산처리할 수 있다. 
  
Apache : 80 port  
Tomcat : 8080 port   
(실제로는 80 포트로 다 처리한다. 8080 포트는 아파치가 알아서 보내주기 때문에 수동적으로 포트 처리할 때 빼고는 보기가 힘들다.)

 | 장점 | 단점 
:---|:---:|:---:
Apache <br> (static)| 처리속도가 빠르다 <br> 구조가 단순하여 비용 절감<br>트래픽 과부하에 강함| 정적인 데이터만 처리 가능 <br> 다른 서비스와 상호작용 불가능
Tomcat <br> (dynamic)) | 데이터 흐름이 유동적 <br> DB 등 여러 서비스가 가능 | Apache에 비해 속도가 느림<br> 부가적인 비용이 발생 <br> 트래픽 과부하에 약함

### Web Server와 WAS와 Container


--
아래 사이트에서 모든 버전의 톰캣을 다운로드 받을 수 있다.  
http://archive.apache.org/dist/tomcat/  
    
--
참고 사이트  
*다운로드* : 
https://wonsama.tistory.com/410
*개념* : https://itnewvom.tistory.com/37