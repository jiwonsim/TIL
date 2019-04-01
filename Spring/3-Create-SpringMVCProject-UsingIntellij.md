# Intellij로 Spring MVC Project 생성하기



<img width="933" alt="스크린샷 2019-04-01 오후 5 44 48" src="https://user-images.githubusercontent.com/28748103/55314829-f186ef80-54a5-11e9-812d-d71954aa6984.png">

Spring 👉 Spring MVC 선택 후 Next

![image](https://user-images.githubusercontent.com/28748103/55314934-2c892300-54a6-11e9-8eb6-b6a6be8446a9.png)

생성된 프로젝트를 우클릭 👉 Add Framework Support 선택

<img width="852" alt="스크린샷 2019-04-01 오후 5 47 59" src="https://user-images.githubusercontent.com/28748103/55315004-52162c80-54a6-11e9-9581-9772d63a41ce.png">

Maven을 선택한다! 

<img width="1792" alt="스크린샷 2019-04-01 오후 5 48 03" src="https://user-images.githubusercontent.com/28748103/55315041-6528fc80-54a6-11e9-97dc-52c9b28478d2.png">

그러고나면 pom.xml이 이렇게 짜잔 나타난다! 

![image](https://user-images.githubusercontent.com/28748103/55315066-783bcc80-54a6-11e9-9e01-52151f5e0696.png)

단축키 <code>cmd + ;</code> 를 눌러서 Project Structure를 켠다. 

![image](https://user-images.githubusercontent.com/28748103/55315551-7d4d4b80-54a7-11e9-9ef7-05c2a91479ed.png)

Spring, Spring MVC Library를 쓰기 위해 추가적인 작업이 필요하다. 메뉴 중 Artifacts 클릭 👉 Available Elements에 있는 넘들을 /WEB-INF/lib 로 끌어온다. 


![image](https://user-images.githubusercontent.com/28748103/55336253-9968e100-54d7-11e9-8af7-19dac512833d.png)

Run Configuration 설정을 위해 우측 상단에 위치한 Edit Configuration 버튼 클릭 👉 이름을 지정해주고 👉 (화면에는 없지만) 아래에 Fix 버튼을 눌러줌 👉 Apply 


![image](https://user-images.githubusercontent.com/28748103/55315775-f9479380-54a7-11e9-9dd8-22b700aa14e6.png)

/src/main/java/controller 에 class 생성 👉 스트링 값을 리턴해준다. 

![image](https://user-images.githubusercontent.com/28748103/55315795-04022880-54a8-11e9-8133-7c7324cf1fb9.png)

/web 에 class에서 리턴한 스트링 값과 이름이 같은 jsp 파일을 만듦 

![image](https://user-images.githubusercontent.com/28748103/55315887-4166b600-54a8-11e9-80dd-c8e00e441902.png)

뷰를 뿌려주기 위해서 설정을 해줘야 한다.  
/web/WEB-INF/dispatcher-servlet.xml 에 위와 같은 코드를 추가해준다.  
``` prefix ``` 의 value 값은 .jsp 파일이 있는 경로를 입력해줘야 한다. 

![image](https://user-images.githubusercontent.com/28748103/55315905-4cb9e180-54a8-11e9-9167-55bc541c4bfc.png)

web.xml 또한 위처럼 변경해준다. 변경된 부분은 <code> servlet-mapping </code> 에서 ``` url-pattern ``` 부분이다. 

🍄 잠깐만! 🍄  
500 에러가 뜨면서<br>
 ```     Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'cacheManager' is defined
 ```  <br>
 라는 메시지를 뿜는다면?! 
 ![image](https://user-images.githubusercontent.com/28748103/55337193-29f3f100-54d9-11e9-8092-26b52ec3629d.png)
이대로 따라하면 된다! 
  
  
  
  끝!
  

  
<hr>
참고 사이트  
https://cjh5414.github.io/intellij-spring-start 
<br>
에러 참고 사이트 <br>
https://stackoverflow.com/questions/24816502/cachemanager-no-bean-found-not-trying-to-setup-any-cache