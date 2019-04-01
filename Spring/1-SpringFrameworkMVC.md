# Spring Framework의 MVC 모델 구조

🎵Current Joys - A Different Age

Spring Framework는 기존에 사용 모델 방식(JSP, JavaBean)의 단점인 복잡한 코드와 컨트롤러와의 구분이 애매해 유지보수의 어려움을 보완하였다. 
Spring Framework를 이용하면 <strong>Model, View, Controller</strong> 3가지 조화로 로직이 확실하게 분리되어 유지보수가 쉬워진다. 


<br>

## MVC 형식의 Spring Framework 구조 
<p align="center">
<img src="https://user-images.githubusercontent.com/28748103/55096668-a9f71100-50fd-11e9-9780-94d73c648194.png">
</p>

1. 웹 브라우저에서 Controller로 요청을 한다.
2. Controller는 Model로 구체화 작업을 한다. 
3. Model은 JavaBean 역할로 Resource를 사용하고 받는다.
4. View는 Controller와 Model 사이에서 서로 주고 받는다.
5. View가 웹 브라우저에 응답을 한다.


MVC의 핵심은 비즈니스 로직과 프레젠테이션 로직의 분리이다. 
레이어별 역할이 뚜렷하여 협업이 용이하고 디자인과 코딩의 분리가 자연스럽다.
비즈니스 로직은 재사용 가능하도록 자바 클래스로 독립적으로 존재하며,
뷰는 어떤 것이든 상관 없어야 한다. (JSP, Velocity, Freemarker ..) 

## DispatcherServlet이란? 
Spring의 MVC는 다른 MVC 프레임워크와 마찬가지로 컨트롤러를 사용하여 요청을 처리한다. (request-driven) 중앙 서블릿이 요청을 컨트롤러로 디스패치하고 웹 어플리케이션 개발의 여러 기능을 제공한다. 하지만 Spring의 DispatcherServlet은 더 많은 일을 한다.<br>
 일단 Spring MVC는 <strong> DispatcherServlet의 등장으로 web.xml의 역할이 축소되었다. </strong> 서블릿을 URL로 활용하기 위해서는 web.xml에 등록해야 했지만 이제는 DispatcherServlet이 해당 어플리케이션으로 들어오는 요청을 모두 핸들링해준다. DispatcherServlet을 이용해 웹 개발을 한다면 앞으로 서블렛 파일을 만들 필요도 없고 @MVC의 혜택을 얻을 수 있다. <br> 
 DispatcherServlet을 이용한다는 것은 Spring에서 제공하는 @MVC를 이용하겠다는 뜻이다. @MVC는 설계자체를 모델1방식으로 할 수 없게 만드는 데다가 구현하기는 까다롭지만 활용성이 높다고 배웠던 모델2방식을 모델1보다 쉽게 만들 수 있도록 환경을 조성해준다. (@MVC로 코드를 작성하는 방식이 모델2와 비슷하여 모델2방식이라고 부른다) <br>
## MVC 패턴의 흐름 
![image](https://user-images.githubusercontent.com/28748103/55096691-b2e7e280-50fd-11e9-8175-1d23f9c77aa1.png)

1. 사용자는 인터넷 브라우저를 통해 웹사이트에 접속한다.<br>
2. 웹 서버(Nginx 또는 Apache Httpd)는 WAS(Tomcat 등)에 Reverse Proxy 처리를 수행, DeispatcherServlet에서는 요청을 받는다. <br>
Front Controller는 모든 요청을 단일 지점으로 이동시키며 요청 흐름제어를 중앙으로 집중화한다. <br>
3.  특정 컨트롤러를 위임하기 위해 HandlerMapping의 도움을 받는다. 이 부분은 BeanNameUrlHandlerMapping과 DefaultAnnotationHandlerMapping이 기본으로 탑재되어 있기 때문에 특별한 경우가 아니면 따로 설정할 필요가 없다. <br>
HandlerMapping은 요청 URL에 매핑된 Controller를 선택해 DispatcherServlet에 반환한다. DispatcherServlet은 HandlerMapping의 도움으로 선택된 Controller에 요청을 보낸다. (그림에서는 HandlerMapping이 직접 보내주는 것으로 그렸다.)
<br>Controller는 사용자가 직접 구현해주는 부분이다. @MVC는 매우 다양한 코딩방식과 직관적이고 편리한 컨트롤러 작성방법을 제공한다.
4. Controller는 비즈니스 로직을 실행하고 처리 결과를 모델로 설정하여 반환한다. 
5. 그때 이 이름을 ViewResolver가 먼저 받아 해당하는 View가 존재하는 검색한다. DispatcherServlet은 ViewResolver의 도움을 받아서 View 이름에 매핑된 View를 반환한다. 
6. View는 화면을 템플릿 작업을 수행하여 반환하고 DispatcherServlet에 의해 사용자의 브라우저로 반환한다. 복잡해보이지만 DispatcherServlet 전략에서 사용자가 직접 구현해야할 부분은 Controller와 View 밖에 없다. 나머지 HandlerMapping이나 Resolver는 대략적인 흐름만 알고 있다가 나중에 필요한 클래스를 컨텍스트에 등록시키만 하면 그만이다. 



## DispatcherServlet의 개념 
Servlet Container에서 HTTP 프로토콜을 통해 들어오는 모든 요청을 프레젠테이션 계층의 제일 앞에 둬서 중앙집중식으로 처리해주는 프론트 컨트롤러    
(Front Controller : 주로 서블릿 컨테이너의 제일 앞에서 서버로 들어오는 클라이언트의 모든 요청을 받아 처리해주는 컨트롤러이다. MVC 구조에서 함께 사용되는 패턴이다.)  
클라이언트로부터 어떤 요청이 오면 Tomcat과 같은 Servlet Container가 요청을 받는다. 이때 제일 앞에서 서버로 들어오는 모든 요청을 처리하는 Front Controller를 Spring에서 정의하였고이를 Dispatcher-Servlet 이라고 한다.  
그래서 공통처리 작업을 Dispatcher 서블릿이 처리한 후에 적절한 세부 컨트롤러로 작업을 위임해준다.  

<br>
<hr/>
https://hunit.tistory.com/185  
https://m.blog.naver.com/PostView.nhn?  blogId=yysvip&logNo=220137322793&proxyReferer=https%3A%2F%2Fwww.google.com%2F  
http://springmvc.egloos.com/504151  
https://hunit.tistory.com/189  
https://mangkyu.tistory.com/18
