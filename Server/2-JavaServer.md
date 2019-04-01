# JSP와 Servlet

**오늘의 목표** :   
JSP와 Servlet의 관계와 차이를 알아보자.


## JSP(Java Server Page)

Java 언어를 기반으로 하는 Server Side 스크립트 언어  
HTML 코드에 Java 코드를 넣어 동적인 웹 페이지를 생성하는 웹 어플리케이션 도구  
브라우저는 기본적으로 HTML 형식의 문서만 표시가능하여 사용자가 JSP 페이지를 요청하게 되면 웹 서버(웹 컨테이너)는 JSP를 Servlet으로 변환한 후에 JVM을 통해 Java Code를 실행하게 되고 그 결과를 HTML 코드 형식으로 변환해서 웹 페이지를 만들어 사용자에게 전송한다.  
    
    
## Servlet(Server Side Applet)
Server와 Applet의 약어로 동적인 웹 페이지를 생성하는 서버 측 프로그램 혹은 그 사양을 말한다. 초창기 CGI 방식 웹 서버의 성능 개선을 목적으로 나왔다.  
(Applet = 웹 브라우저에 담겨 실행되는 작은 자바 응용 프로그램)  
쉽게 표현하자면 웹 서버 상에서 실행되는 .class 파일이다.  
반드시 javax.servlet.Servlet 인터페이스를 Implements 해야만 한다.  


## JSP와 Servlet의 관계? 
JSP와 Servlet의 차이는 없다! 기능의 차이는 없고 역할의 차이만 있다. JSP는 Server Side 스크립트 언어라고 했지만 JSP는 사실 Servlet이다.  
서버 사이드 Java 프로그램은 일반적인 응용 프로그램과 다르다. 응용 프로그램은 그 프로그램을 기동시켜 실행하지만, 서버 사이드 Java 프로그램은 그렇지 않다. 서버 사이드에는 Java 서버가 있고 그 속에서 움직이는 프로그램을 개발하는 구조로 돼있다.  
예를 들어 Web Browser에서 Applet이라는 작은 프로그램을 포함하여 움직이는 것과 같다. Applet은 응용 프로그램이 아니고, 작은 프로그램을 실행하는 틀이 있어 그 틀에 맞춰 만들어지는 프로그램을 Web Page에 끼워넣으면 자동으로 인식돼 움직인다.  
서버 사이드도 마찬가지다. Java 서버에는 프로그램을 작동시키기 위한 구조가 준비되어있다. 그리고 그 구조에 따라 프로그램을 만들고 포함해두었다. 사용자가 그 프로그램에 할당된 URL에 엑세스하면 Java 서버는 그 프로그램을 실행하도록 ㅗ대있다.
이 'Java 서버에서 움직이는 작은 프로그램'이 Servlet이다.   
<br>
그렇담 JSP란? Servlet은 Java 프로그램이기 때문에 모두 Java로 코딩해야 한다. 내부의 처리뿐 아니라 클라이언트에 표시되는 HTML 코드도 모두 Java로 써야한다. 그래서 **더 간편하게 서버 사이드 Java를 사용할 수 있도록** 하는 것이 JSP이다. Java 서버가 수행하고 있으며 간단한 태그를 사용하여 작성된 Java 코드를 바로 실행하는 것은 사실 없다.  
Java 서버는 JSP 코드를 읽어 들여 그것을 Serlvet 소스 코드로 변환한다. 그렇게 생성된 Servlet 소스 코드를 컴파일하고, 서블릿을 생성하여 그것을 호출한다. 즉 **JSP가 서블릿**이 되는 것이다. 

![image](https://user-images.githubusercontent.com/28748103/55271995-97e1c200-52f9-11e9-9c47-1b63a3376618.png)


Servlet 예제 코드 👇

```bash
public ThreeParams extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContenetType("text/html"); 
		printWriter out = response.getWriter(); 

		String title = "Reading Three Request Parameters"; 
		String docType = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n";

		out.println(docType + 
			"<HTML>\n" +
			"<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
			"<BODY BGCOLOR=\"CENTER\">" + title + "</H1>\n" + 
			"<UL>\n" +
			"<LI><B>param1</B>: " + request.getParameter("param1") + "\n" +
			"<LI><B>param2</B>: " + request.getParameter("param2") + "\n" +
			"<LI><B>param3</B>: " + request.getParameter("param3") + "\n" +
			"</UL>\n" +
			"</BODY></HTML>"
			); 
	}
}
```

JSP 예제 코드 👇


```javascript
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
	<TITLE>Reading Three Request Parameters</TITLE>
	<LINK REL=STYLESHEET HREF="JSP-Styles.css" TYPE="text/css"/>
</HEAD>

<BODY>
	<H1>Reading Three Request Parameters</H1>
	<UL>
		<LI><B>param1</B> : <%= request.getParameter("param1") %></LI>
		<LI><B>param2</B> : <%= request.getParameter("param2") %></LI>
		<LI><B>param3</B> : <%= request.getParameter("param3") %></LI>
	</UL>
</BODY>
</HTML>

```


<hr/>

참고 사이트  
https://kit2013.tistory.com/79  
https://araikuma.tistory.com/275  
https://gmlwjd9405.github.io/2018/11/04/servlet-vs-jsp.html  