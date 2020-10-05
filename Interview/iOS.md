# iOS 



[JaeSungLEE/iOSInterviewquestions](https://github.com/JeaSungLEE/iOSInterviewquestions)
[JaeYeopHan/Interview_Quesition_for_Beginner](https://github.com/JaeYeopHan/Interview_Question_for_Beginner/tree/master/iOS)



### App Life Cycle

![img](https://blog.kakaocdn.net/dn/TTVg0/btqFDwMCRoj/4UlHdjB8WVtysF1ktIDqgk/img.png)

1. **Not Running** 

   앱이 실행되지 않았거나, 완전히 종료되어 동작하지 않는 상태입니다. 

2. **Inactive**

   앱이 실행 중이지만, 사용자로부터 이벤트를 받을 수 없는 상태입니다. 멀티 태스킹 창으로 진입하거나 앱 실행 중 전화, 알림 등에 의해 앱을 사용할 수 없게 되는 경우에 이 상태로 진입합니다. 

3. **Active**

   앱이 실제로 실행 중이고 사용자로부터 이벤트를 받아 상호작용할 수 있는 상태입니다. Active와 Inactive 상태를 통틀어 Foreground 상태라고 하며, Background에서 Foreground로 이동할 때 반드시 Inactive 상태를 거칩니다. 

4. **Background**

   홈 화면으로 나가거나 다른 앱으로 전환되어 현재 앱이 실질적인 동작을 하지 않는 상태를 의미합니다. 화면이 꺼져도 노래가 나오거나 타이머가 진행되는 작업, 이미지를 다운 받는 작업 등을 이 상태에서 할 수 있습니다. 

5. **Suspended**

   앱을 다시 실행했을 때 최근 작업을 빠르게 로드하기 위해 메모리에 관한 데이터만 저장되어 있는 상태를 의미합니다. 앱이 Background에 진입했을 때 다른 작업을 하지 않으면, Suspended 상태로 진입하게 됩니다. 또한, iOS에서 메모리가 부족해지는 상황이 왔을 때 Suspended 상태의 앱이 가장 먼저 메모리에서 해제됩니다. 그래서 사용자가 앱을 종료한 적이 없음에도 앱을 실행할 때 처음부터 다시 실행됩니다. 



### forground와 background 환경에서의 제약 사항 

### 앱이 In-Active 상태가 되는 시나리오 

### AppDelegate

프로젝트를 생성할 때 AppDeleagte 클래스가 정의된 AppDelegate.swift 파일이 생성됩니다. AppDelegate 클래스의 인스턴스는 <u>앱 상태의 변화에 따라 응답하는 콘텐츠를 그리는 window</u>를 제공합니다. 또한, <u>Entry point와 앱의 입력 이벤트를 전달하는 Run Loop를 생성</u>합니다. 이 작업은 파일의 가장 위에 표시된 UIApplicationMain 속성인 **@UIApplicaitonMain** 에 의해 수행됩니다. 

UIApplicationMain 속성을 사용하는 것은 UIApplicationMain 함수를 호출하고, AppDelegate 클래스의 이름을 딜리게이트 클래스의 이름으로 넘기는 것과 같습니다. UIApplicationMain 속성을 사용하면, 시스템은 어플리케이션 객체를 생성합니다. 어플리케이션 객체는 앱의 라이프 사이클을 관리하는 역할을 수행합니다. 또한, 시스템은 AppDelegate 클래스의 인스턴스를 생성하고, 어플리케이션 객체를 사임합니다. 끝으로 시스템은 앱을 런칭합니다. 

AppDelegate 클래스는 우리가 새로운 프로젝트를 만들 때마다 자동으로 생성됩니다. 우리가 특별히 일반적이지 않는 작업을 수행하지 않는 한, 우리는 이 클래스를 이용해야 합니다. AppDelegate 클래스는 UIApplicationDelegate 프로토콜을 채택합니다. 이 프로토콜은 앱의 상태 변화를 감지하고 app-level 이벤트를 다루기 위한 많은 메서드를 정의합니다. AppDelegate 클래스는 오직 하나의 프로퍼티인 window를 포함하고 있습니다. 

```swift
var window: UIWindow?
```

이 프로퍼티는 앱의 윈도우의 참조를 저장하고 있습니다. 이 윈도우는 앱의 뷰 계층에서 가장 상위를 차지하고 있으며, 앱의 모든 콘텐츠가 그려지는 공간입니다. 윈도우 프로퍼티가 옵셔널이라는 것(즉, 어떤 포인트에서 nil이 됨을 의미한다는 것)을 기억합시다!

AppDelegate 클래스는 또한 delegate 메소드를 포함하고 있습니다. 

```swift
func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool

func applicationWillResignActive(_ application: UIApplication)

func applicationDidEnterBackground(_ application: UIApplication)

func applicationWillEnterForeground(_ application: UIApplication)

func applicationDidBecomeActive(_ application: UIApplication)

func applicationWillTerminate(_ application: UIApplication)
```

이 메소드들은 어플리케이션 객체가 앱 딜리게이트와 소통하도록 합니다. 예를 들어, 앱의 상태가 변화하는 동안 어플리케이션 객체는 관련된 딜리게이트 메소드를 호출해 앱이 응답할 수 있는 기회를 줍니다. 

각 딜리게이트 메소드는 기본 동작이 있습니다. 만약 우리가 AppDeleagte 클래스에서 구현을 비워두거나 삭제하면, 메소드가 호출될 때 기본적인 행위를 수행합니다. 



[monibu1548/AppDelegate의-역할과-메소드](http://monibu1548.github.io/2018/08/28/appdelegate/)
[Zedd0202/AppDelegate.swift의-역할](https://zeddios.tistory.com/218)
[DocumentationArchive/Start-Developing-iOS-Apps](https://developer.apple.com/library/archive/referencelibrary/GettingStarted/DevelopiOSAppsSwift/BuildABasicUI.html#//apple_ref/doc/uid/TP40015214-CH5-SW1)

### 상태 변화에 따라 다른 동작을 처리하기 위한 앱 딜리게이트 메서드

### View 객체에 대해 

### View Life Cycle

![img](https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F998D703359F037C907)



1. **loadView**
2. **viewDidLoad** 
   - 이 메소드는 뷰 컨트롤러가 메모리에 뷰 계층을 올렸을 때 메소드가 호출 됩니다. (This method is called after the view controller has loaded its view hierarchy into memory) 뷰의 로딩이 완료되었을 때 초기 화면을 구성하는 용도로 사용됩니다. 그래서 처음 한 번만 실행해야 하는 초기화 코드가 있는 경우 해당 메소드에 작성합니다. 
3. **viewWillAppear**
   - 뷰가 이제 나타날 것이라는 신호를 컨트롤러에 알리는 역할을 합니다. 즉, 뷰가 나타나기 직전마다 호출됩니다. 
4. **viewDidAppear**
   - 뷰가 나타났다는 것을 컨트롤러에게 알려주는 역할을 합니다. 뷰가 화면에 나타난 직후에 실행된다는 것이 viewWillAppear()과의 차이점입니다.
5. **viewWillDisappear**
   - 뷰가 삭제 되기 직전에 컨트롤러에게 알려주는 역할을 합니다. 
6. **viewDidDisapper**
   - 뷰가 삭제되었음을 컨트롤러에게 알려주는 역할을 합니다. 
7. **viewDidUnload**



A 뷰와 B 뷰가 있다고 했을 때 순서는 아래와 같습니다. 

**A viewDidLoad** ➜ **A viewWillAppear** ➜ **A viewDidAppear** ➜ **A viewWillDisappear** ➜ B viewDidLoad ➜ B viewWillAppear ➜ **A viewDidDisappear** ➜ B viewDidAppear



[Alpaca/ViewController&ViewLifeCycle](https://medium.com/@Alpaca_iOSStudy/viewcontroller-view-lifecycle-daed5766e02b)
[Zedd0202/ViewController의-생명주기(Life-Cycle)](https://zeddios.tistory.com/43)

### Delegate vs Block vs Notification 

### Memory Management

### assign vs weak 

### Frame vs Bounds 

### 데이터를 저장하는 방법들

### 동적 바인딩

### NotificationCenter 동작 방시과 활용 방안 

### POP의 장단점

### UIView의 Layer 객체

### UIApplication 객체의 컨트롤러 역할은 어디에 구현해야 하는가?

### UINavigationController의 역할

### 모든 ViewController 객체의 상위 클래스는 무엇이고 그 역할은 무엇인가? 

### GDQ의 Qos에는 어떤 종류가 있는지, 각각 어떤 의미인지 설명하시오

### Object 객체에 대하여 





# ARC



# Autolayout

### 오토레이아웃을 코드로 작성하는 방법(3가지) 

### 스토리보드를 이용했을 때의 장단점

### Safearea에 대하여

### Left Constraint와 Leading Constarint의 차이점 



