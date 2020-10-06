# iOS 



[JaeSungLEE/iOSInterviewquestions](https://github.com/JeaSungLEE/iOSInterviewquestions)
[JaeYeopHan/Interview_Quesition_for_Beginner](https://github.com/JaeYeopHan/Interview_Question_for_Beginner/tree/master/iOS)



## App Life Cycle

![img](https://blog.kakaocdn.net/dn/TTVg0/btqFDwMCRoj/4UlHdjB8WVtysF1ktIDqgk/img.png)

- **Not Running** 

  앱이 실행되지 않았거나, 완전히 종료되어 동작하지 않는 상태입니다. 

- **Foreground** 

  - **Inactive**

    앱이 Foreground에서 실행 중이지만, 사용자로부터 이벤트를 받을 수 없는 상태입니다. 멀티 태스킹 창으로 진입하거나 앱 실행 중 전화, 알림 등에 의해 앱을 사용할 수 없게 되는 경우에 이 상태로 진입합니다. 

  - **Active**

    일반적으로 앱이 화면에 떠있을 때의 상태로, 사용자로부터 이벤트를 받을 수 있는 상태입니다.

- **Background** 

  - **Running**

    홈 화면으로 나가거나 다른 앱으로 전환되어 현재 앱이 실질적인 동작을 하지 않는 상태를 의미합니다. 화면이 꺼져도 노래가 나오거나 타이머가 진행되는 작업, 이미지를 다운 받는 작업 등을 이 상태에서 할 수 있습니다. 

    Background가 지속되는 시간은 iOS 7 이전까지 10분으로 고정되어 있었지만, iOS 7버전 이후로는 선언되는 변수들과 빌드 환경에 따라 다르게 정해집니다. 

    ```swift
    print(UIApplication.shared.backgroundTimeRemaining)
    ```

    위 코드를 작성하면 해당 프로젝트마다의 background 지속 시간을 알 수가 있습니다. 

  - **Suspended**

    앱을 다시 실행했을 때 최근 작업을 빠르게 로드하기 위해 메모리에 관한 데이터만 저장되어 있는 상태를 의미합니다. 앱이 Background에 진입했을 때 다른 작업을 하지 않으면, Suspended 상태로 진입하게 됩니다. 또한, iOS에서 메모리가 부족해지는 상황이 왔을 때 Suspended 상태의 앱이 가장 먼저 메모리에서 해제됩니다. 그래서 사용자가 앱을 종료한 적이 없음에도 앱을 실행할 때 처음부터 다시 실행됩니다. 



## forground와 background 환경에서의 제약 사항 

Background 상태에서 동작할 수 있는 이벤트 처리는 Xcoded의 Capabilities에서 확인할 수 있습니다. 

![image-20201006020048907](/Users/simjiwon/Library/Application Support/typora-user-images/image-20201006020048907.png)

![image-20201006020114099](/Users/simjiwon/Library/Application Support/typora-user-images/image-20201006020114099.png)

background 에서 사용할 수 있도록 Apple이 허용한 부분은 위와 같은 기능입니다.

1. **Audio, AirPlay, and Picture in Picture** 

   background에서 들을 수 있는 컨텐츠를 재생하거나 오디오를 녹음합니다. 여기서 AirPlay를 사용하여 스트리밍 오디오 또는 비디오 콘텐츠가 포함됩니다. Picture in Picture은 비디오 콘텐츠가 듀얼로 재생되는 것을 의미합니다. Youtube premium에서 제공하는 백그라운드 재생이 그 예입니다. 

   특이하게 음악, 영상 관련 앱이 아닌 경우 이 기능을 체크하게 되면 AppStore 심사에서 Reject을 받습니다. 

2. **Location updates** 

   백그라운드에서 실행 중에도 GPS가 변할 때마다 이벤트를 처리할 수 있습니다. 

3. **Background fetch** 

   시스템이 사용자의 앱의 사용 패턴을 익혀 Fetch 합니다. 

4. **Remote notifications**

   FCM payload에 "content-available":true 가 포함된 경우 App을 깨워 Event 처리를 합니다. 



[강동희/iOS-Background-Mode](https://medium.com/cashwalk/ios-background-mode-9bf921f1c55b)
[AppleDeveloper/Adding-Capabilities-to-Your-App](https://developer.apple.com/documentation/xcode/adding_capabilities_to_your_app)

## 앱이 In-Active 상태가 되는 시나리오 



![img](https://media.vlpt.us/images/cskim/post/73f20c94-bf55-4d28-8965-0d779a3b0565/image.png)





[JuheeKim/iOS-Application-State](https://caution-dev.github.io/ios/2019/03/14/iOS-Application-state.html)
[@cskim/iOs-App-생명주기Life-Cycle](https://velog.io/@cskim/iOS-App-%EC%83%9D%EB%AA%85%EC%A3%BC%EA%B8%B0Life-Cycle)



## AppDelegate

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

## AppDelegate와 SceneDelegate 

![post-thumbnail](https://media.vlpt.us/images/dev-lena/post/49fa9a11-3e8d-4f28-9a4a-34cbcd07a8b1/D4VfgIv.png)

iOS 13 이전에는 AppDelegate.swift만 자동 생성되며, window라는 개념이 있었습니다. iOS 13부터는 AppDelegate.swift와 SceneDelegate.swift로 분리되고 window 개념이 scene으로 바뀌었습니다. 하나의 앱에 하나의 window만 있었던 전과 달리 하나의 앱에서 여러 개의 scene을 가질 수 있게 되었습니다. 

AppDelegate의 역할 중 UI의 상태를 알 수 있었던 UILifeCycle에 대한 부분을 SceneDelegate가 담당하게 되었습니다. 그리고 AppDelegate에는 Session Lifecycle에 대한 역할로, Scene Session이 생성되거나 삭제될 때 AppDelegate에 알리는 메소드가 추가되었습니다. Scene Session은 앱에서 생성한 모든 scene의 정보를 관리하는 역할을 합니다. 

```swift
//  AppDelegate.swift

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        return true
    }

    // MARK: UISceneSession Lifecycle

    func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
        // Called when a new scene session is being created.
        // Use this method to select a configuration to create the new scene with.
        return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
    }

    func application(_ application: UIApplication, didDiscardSceneSessions sceneSessions: Set<UISceneSession>) {
        // Called when the user discards a scene session.
        // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
        // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
    }
}


```

```swift
//  SceneDelegate.swift

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?


    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        // Use this method to optionally configure and attach the UIWindow `window` to the provided UIWindowScene `scene`.
        // If using a storyboard, the `window` property will automatically be initialized and attached to the scene.
        // This delegate does not imply the connecting scene or session are new (see `application:configurationForConnectingSceneSession` instead).
        guard let _ = (scene as? UIWindowScene) else { return }
    }

    func sceneDidDisconnect(_ scene: UIScene) {
        // Called as the scene is being released by the system.
        // This occurs shortly after the scene enters the background, or when its session is discarded.
        // Release any resources associated with this scene that can be re-created the next time the scene connects.
        // The scene may re-connect later, as its session was not neccessarily discarded (see `application:didDiscardSceneSessions` instead).
    }

    func sceneDidBecomeActive(_ scene: UIScene) {
        // Called when the scene has moved from an inactive state to an active state.
        // Use this method to restart any tasks that were paused (or not yet started) when the scene was inactive.
    }

    func sceneWillResignActive(_ scene: UIScene) {
        // Called when the scene will move from an active state to an inactive state.
        // This may occur due to temporary interruptions (ex. an incoming phone call).
    }

    func sceneWillEnterForeground(_ scene: UIScene) {
        // Called as the scene transitions from the background to the foreground.
        // Use this method to undo the changes made on entering the background.
    }

    func sceneDidEnterBackground(_ scene: UIScene) {
        // Called as the scene transitions from the foreground to the background.
        // Use this method to save data, release shared resources, and store enough scene-specific state information
        // to restore the scene back to its current state.
    }
}
```



Scene은 UI의 하나의 인스턴스를 나타내는 window를 갖고 있습니다. 그리고 각각의 scene은  UIWindowSceneDelegate을 위임 받는데 이는 UI 인스턴스의 생명 주기를 관리하는 역할을 합니다. 각 Scene들은 같은 메모리와 앱 프로세스 공간을 공유하면서 서로 동시에 실행하게 됩니다. 결과적으로 하나의 앱은 여러 scene과 scene delegate 객체를 동시에 활성화할 수 있게 됩니다. 

AppDelegate에서 관리하는 UISessionScene 객체는 scene의 고유 런타임 인스턴스를 관리합니다. 사용자가 앱에 새로운 scene을 추가하거나 요청하게 되면, 시스템은 그 scene을 추적하는 session 객체를 생성합니다. session에는 고유한 식별자와 scene의 구성 세부사항이 들어있습니다. 그래서 UIKit은 해당 scene의 생명 주기동안 session 정보를 유지하며 scene을 클로징하는 것에 대응하여 그 session을 파괴합니다. 

SceneDelegate가 추가되었기 때문에 AppDelegate의 역할을 바뀌었습니다. 이전에는 foreground와 background 전환 시에 앱의 상태를 업데이트 하는 등의 주요 생명 주기 이벤트를 관리했습니다. 이제는 앱의 scene을 configure 하고, 앱 밖에서 발생한 알림에 대응하기, 앱 자체를 타겟하는 이벤트에 대응하는 것, 앱의 데이터 구조를 초기화하는 등의 특정 객체에 한정하지 않고 앱 전체를 관리하는 역할을 수행합니다. 



[dev-lena/AppDelegate와-SceneDelegate](https://velog.io/@dev-lena/iOS-AppDelegate%EC%99%80-SceneDelegate)



## 상태 변화에 따라 다른 동작을 처리하기 위한 앱 딜리게이트 메서드

iOS 12 이전까지는 전체 LifeCycle을 UIApplicationDelegate에서 관리하고 있었지만, iOS 13부터는 Scene이라는 개념이 도입되면서 하나의 앱이 여러 UI Life Cycle을 갖게 되었습니다. 각각의 Scene에서 관리하는 UI Life Cycle은 SceneDelegate에서 관리하며, Process Life Cycle은 AppDelegate에서 관리하게 됩니다. 그렇기 때문에 각 경우에 수행되는 메서드는 다릅니다. 

#### Respond to App-Based Life Cycle

![img](https://blog.kakaocdn.net/dn/qor41/btqFElcJ27z/4BDWmBfkNKOsdnnAVAZ0Tk/img.png)

1. **Starting App**: Not Running ➜ In-active ➜ Active

   ```swift
   application(_:didFinishLaunchingWithOptions:)
   ```

   앱이 실행되고 앱을 화면에 띄우기 위한 모든 설정이 완료된 뒤에, 실제로 화면에 나타나기 직전에 호출됩니다. UIWindow를 생성하는 등의 작업을 수행합니다. Storyboard를 사용한다면 entry point를 찾아 내부적으로 UIWindow를 생성합니다. 

   ```swift
   applicationDidBecomeActive(_:)
   ```

   앱이 In-active에서 Active 상태로 전환됐을 때 호출됩니다. 

2. **To Background(offscreen)**: Active ➜ In-active ➜ Background (➜ Suspended)

   ```swift
   applicationWillResignActive(_:)
   ```

   앱이 Active 상태에서 In-active 상태로 전환될 때 호출됩니다. 

   ```swift
   applicationDidEnterBackground(_:)
   ```

   앱이 Background 상태로 전환됐을 때 호출됩니다. 

3. **To Foreground(onscreen)**: Background ➜ In-active ➜ Active 

   ```swift 
   applicationWillEnterForeground(_:) 
   ```

   앱이 Background에서 In-active 상태로 전환될 때 호출됩니다. 

   ```swift
   applicationDidBecomeActive(_:) 
   ```

   앱이 In-active에서 Active 상태로 전환될 때 호출됩니다. 

4. **Terminating App**: Background/Suspended ➜ Not Running 

   ```swift
   applicationWillTerminate(_:)
   ```

   앱이 사용자에 의해 종료될 때 호출됩니다. 시스템에 의해 예기치 못한 상황에서 종료될 때 호출되지 않습니다. 



#### Respond to Scene-Based Life Cycle 

Scene을 사용하지 않는다면 모든 Process/UI Life Cycle 이벤트는 모두 AppDelegate로 전달됩니다. iOS 13 환경이라고 해도 Scene을 지원하지 않는다면 모든 event가 AppDelegate로 전달되며, App-Based Life Cycle로 동작합니다.

 ![img](https://blog.kakaocdn.net/dn/msyYp/btqFGyQkNoh/fqN6ADOvKVe9rkQOcZEdf0/img.png)

1. **Starting App** 

   ```swift
   application(_:didFinishLaunchingWithOptions:) 
   ```

   앱이 실행되고 앱을 화면에 띄우기 위한 모든 설정이 완료된 뒤에, 실제로 화면에 나타나기 직전에 호출됩니다. 

2. **Scene 연결**

   앱이 실행되면 UIKit에서 Scene을 연결합니다. Scene이 연결되고 화면에 나타나기까지 과정에서 다음 메서드들이 호출됩니다.

   ```swift
   application(_:configurationForConnection:options:)
   ```

   새로운 Scene을 만들고 UIKit과 연결하기 위한 Configuration을 지정합니다. 여기서 Configuration은 Scene의 delegation 객체를 지정하는 등 Scene을 연결하기 위한 정보가 들어있는 UISceneConfiguration 객체를 의미합니다. 일반적으로 info.plist에 추가된 기본값을 사용해 생성합니다. 

   ```swift
   scene(_:willConnectTo:options)
   ```

   Scene이 연결될 것임을 delegate에 알려줍니다. 기존에 application(_:didFinishLaunchingWithOptions)에서 했던 UIWindow 생성 작업을 이 메서드에서 할 수 있습니다. Storyboard를 사용한다면 entry point를 찾아 내부적으로 UIWindow를 생성합니다. 

   ```swift
   sceneDidBecomeActive(_:)
   ```

   앱이 In-active에서 Active 상태로 전환됐을 때 호출됩니다. 

3. **To Background(offscreen)**: Active ➜ In-active ➜ Background (➜ Suspended)

   ```swift
   sceneWillResignActive(_:)
   ```

   앱이 Active 상태에서 In-active 상태로 전환될 때 호출됩니다. 

   ```swift
   sceneDidEnterBackground(_:)
   ```

   앱이 Background 상태로 전환됐을 때 호출됩니다. 

4. **To Foreground(onscreen)**: Background ➜ In-active ➜ Active 

   ```swift 
   sceneWillEnterForeground(_:) 
   ```

   앱이 Background에서 In-active 상태로 전환될 때 호출됩니다. 

   ```swift
   sceneDidBecomeActive(_:) 
   ```

   앱이 In-active에서 Active 상태로 전환될 때 호출됩니다. 

5. **Scene 연결 해제**

   ```swift
   sceneDidDisconnected(_:)
   ```

   delegate에 UIKit에 연결된 Scene의 연결을 해제할 것을 요청합니다. 

   ```swift 
   application(_:didDiscardSceneSessions:) 
   ```

   사용자가 멀티태스킹 화면(app switcher)에서 한 개 이상의 Scene을 종료시켰을 때 호출합니다. 

   ```swift
   applicationWillTerminate(_:)
   ```

   앱이 사용자에 의해 종료될 때 호출됩니다. 시스템에 의해 예기치 못한 상황에서 종료될 때 호출되지 않습니다. 

[cskime/Application-Life-Cycle](https://cskime.tistory.com/11?category=801416)



## View Life Cycle

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

## Delegate vs Notification vs KVO 

이 두 가지 패턴은 View와 ViewController 각각의 관계에서 소통이 필요할 때 사용합니다. 하지만 객체가 그 자체로 존재하며, 다른 객체에 종속되어 동작하는 것을 원하지 않을 때 사용됩니다. 



- **Delegate**
  - Delegate는 보통 Protocol을 정의하여 사용합니다. Protocol은 기능 명세서의 역할을 하며 Delegate로 지정된 객체가 해야 하는 메소드들의 원형을 적어 놓습니다. Delegate 역할을 하려는 객체는 이 Protocol을 따르며, 원형만 있던 메소드들의 원형을 구현하게 됩니다. 그러면 이전 객체는 어떤 이벤트가 일어났을 때 delegate로 지정한 객체에 알려줄 수가 있습니다. 
  - 장점 
    - 매우 엄격한 Syntax로 프로포콜에 필요한 메소드들이 명확히 명시됩니다. 
    - 로직의 흐름을 따라가기가 쉽습니다. 
    - 커뮤니케이션 과정을 유지하고 모니터링하는 제 3의 객체가 필요하지 않습니다.
    - 프로토콜이 컨트롤러의 범위 안에서 정의됩니다. 
  - 단점 
    - 많은 줄의 코드가 필요합니다. 
    - 많은 객체들에게 이벤트를 알려주는 것이 어렵고 비효율적입니다. 
- **Notification**
  - NotificationCenter라는 싱글톤 객체를 통해 이벤트의 발생 여부를 옵저버를 등록한 객체들에게 Notification을 post 하는 방식으로 사용합니다. NotificationName이라는 key 값을 통해 보내고 받을 수 있습니다.
  - 장점 
    - 많은 줄의 코드가 필요하지 않습니다. 
    - 다수의 객체들에게 동시에 이벤트의 발생을 알려줄 수가 있습니다. 
  - 단점
    - Notification Post 이후 정보를 받을 수 없습니다. 
    - key 값으로 Notification 이름과 userInfo 를 서로 맞추기 때문에 컴파일 시 구독이 잘 되고 있는지, 올바르게 userInfo의 value를 받아오는지 체크가 불가능합니다. 



[Alpaca/Delegation-Notification-그리고-KVO](https://medium.com/@Alpaca_iOSStudy/delegation-notification-%EA%B7%B8%EB%A6%AC%EA%B3%A0-kvo-82de909bd29)
[이동건의 군옥수수수/Key-Value Observing in Swift4](https://baked-corn.tistory.com/126)

## assign vs weak 

ARC이 생기면서 strong과 weak 개념이 만들어졌습니다. weak은 기존의 assign과 비슷한 개념이지만, assign은 객체가 해제되어도 포인터 값이 변하지 않는 데에 weak은 nil로 초기화해줍니다. 따라서 assign의 문제점인 객체가 해제되어도 포인터 값이 남아 있어 접근 시에 프로그램이 죽는 경우를 weak의 도입으로 방지할 수 있게 되었습니다. 

## Frame vs Bounds 

UIView는 frame과 bounds라는 CGRect 타입의 View의 크기와 사이즈를 반환하는 프로퍼티를 갖고 있습니다. 이 둘의 차이는 superview에 따라 달라집니다. frame은 superview의 좌표 시스템 내에서 view의 위치와 크기를 나타내며, bounds는 view의 위치와 크기를 자신의 좌표 시스템 안에서 나타낸다는 차이가 있습니다.

[애기지원/Frame과 Bounds](https://blog.naver.com/jiiw0n/222009884692)

## 데이터를 저장하는 방법들

1. **UserDefaults** 
   - UserDefaults는 plist에 Key-value 값을 쌍으로 저장합니다. 관계가 복잡하지 않고 작은 범위의 데이터를 저장할 때 사용합니다. key는 언제나 String 값이고, value는 Any, Float, Double, Int, Bool, URL 등 다양한 값을 가집니다. 
   - 장점 
     - 간단한 정보를 저장 및 불러오기가 편리합니다. 
     - 싱글톤이기 때문에 모든 곳에서 접근 가능합니다. 
   - 단점
     - plist 파일에 저장되기 때문에 보안상 강력하지 않습니다. 
     - UserDefaults는 기본적으로 데이터들을 캐시하여 in-memory로 관리합니다. 이는 앱 실행 속도에 영향을 미칩니다. 
2. **CoreData**
   - CoreData는 객체 그래프를 관리하는 프레임워크로 데이터를 지속적으로 저장할 수 있는 기능을 제공합니다. 그렇다고 데이터베이스라고 말할 수 없으며, 객체 그래프를 디스크에 저장하여 지속적으로 지니는 기능을 제공한다는 것입니다. 
   - 장점 
     - In-Memory 방식이라 쉽고 빠르게 접근할 수 있습니다. 
   - 단점
     - In-Memory이기 때문에 더 많은 메모리를 필요로 하고 더 많은 저장 공간을 사용합니다. 
3. **Sqlite**
   - 서버가 필요없는 SQL 데이터베이스 엔진을 구현합니다. 
   - 장점
     - 설정이 간편합니다. (Zero-configuration) 
     - 여러 프로세스와 스레드로부터 안전하게 접근할 수 있습니다. 
     - SQL 문법을 이용하기 때문에 대중적입니다. 
   - 단점
     - CoreData보다 저장 공간이 적고 fetch가 더 느립니다. 
4. **Realm**
   - iOS와 Android 같은 모바일 플랫폼을 위해 설계된 데이터베이스 플랫폼입니다. 기존의 정형 데이터베이스와 달리 NoSQL 데이터베이스를 지향합니다. 
   - 장점 
     - 설치와 설정이 쉽고 빠릅니다. 
     - 다른 로컬 DB보다 뛰어난 성능을 가지고 있습니다. 
   - 단점 
     - 쓰레드별 객체 관리가 필요하기 때문에 다중 쓰레드 환경에서는 러닝커브가 존재합니다. 
     - iOS 8부터 지원합니다. 
     - 다양한 쿼리를 지원하지 않습니다. 



[jeffreyfulton/userdefaults-limitations-and-alternatives](https://jeffreyfulton.ca/blog/2018/02/userdefaults-limitations-and-alternatives)
[jake-kim/CoreData](https://ios-development.tistory.com/89)
[jojo_devstory/Realm이란?](https://velog.io/@jojo_devstory/Android-Realm%EC%9D%B4%EB%9E%80-SQLite%EB%B3%B4%EB%8B%A4-%EC%A2%8B%EB%8B%A4%EB%8D%98%EB%8D%B0)
[devmjun/iOS에서 사용할 수 있는 데이터베이스 비교하기](https://devmjun.github.io/archive/iOS_Databases-SQLLite_vs_Core_Data_vs_Realm)

## 동적 바인딩

## NotificationCenter 동작 방식과 활용 방안 

![img](https://t1.daumcdn.net/cfile/tistory/99E985335A12E50F1F)



특정 객체가 NotificationCenter에 등록된 Event를 발생시키면(post) 해당 Event를 처리할 것이라고 등록된 Observer들이 Event에 대한 행동을 취하도록 Broadcast 하는 방식으로 동작합니다. 



[이동건의 군옥수수수/NotificationCenter](https://baked-corn.tistory.com/42)



## POP의 장단점



프로토콜 지향 프로그래밍은 애플에서 지향하는 프로그래밍 패러다임으로, 객체 지향 프로그래밍이 가지는 불편함을 개선하기 위해 등장하였습니다. 

OOP는 슈퍼클래스에 너무 종속적이기 때문에 서브클래스가 자신에게 필요 없는 변수나 함수를 무조건 물려 받는 수 밖에 없습니다. 슈퍼클래스 역시 몇몇의 서브클래스에만 필요한 코드들이 슈퍼클래스에 무한 추가되면서 코드가 복잡해지고 크기만 커지게 됩니다. 또한, OOP의 경우 Value type을 사용할 수 없어 다중 스레드 환경에서 원본 데이터가 바뀐다는 단점이 있습니다. 

애플은 Swift 2부터 Extension을 제시하여, Protocol과 Extension의 조합으로 Value type의 상속과 구조체를 구현할 수 있게끔 만들었습니다. 

- 장점 
  - 슈퍼클래스와 서브클래스 간의 의존적인 관계와 달리, 프로토콜 기반의 구조에서는 프로토콜에 정의된 인터페이스를 확인해 구현해놓으면 됩니다. 슈퍼클래스를 상속받는 서브클래스와 달리 같은 프로토콜을 따르는 사이에서도 끈끈히 엮여있는 부분이 없어 각각 독립적이며 안전합니다. 
  - 상속 구조에서는 오직 하나의 슈퍼클래스만 가질 수 있었으나, 프로토콜의 경우에 다수의 프로토콜을 따를 수 있습니다. 따라서 수평적으로 기능을 확장시킬 수 있습니다. 
  - 제네릭을 활용하면 Type safe와 Flexible code 모두를 잡을 수 있습니다. 
- 단점
  - Objective-C 프로토콜에  Swift Extension을 붙여도 Protocol default implimentation이 구현되지 않습니다. (?) 
  - 자주 사용되는 Delegate, DataSource 등 프레임워크 프로토콜에 기본 구현이 불가합니다. 



[Alpaca/Protocol Oriented Programming](https://medium.com/@Alpaca_iOSStudy/protocol-oriented-programming-pop-2db7d4d02747)
[jinShine/프로토콜 지향 프로그래밍](https://jinshine.github.io/2018/09/11/Swift/18.%ED%94%84%EB%A1%9C%ED%86%A0%EC%BD%9C%20%EC%A7%80%ED%96%A5%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D(Protocol-Oriented%20Programming)/)
[찜토끼/객체지향 프로그래밍과 프로토콜 지향 프로그래밍](https://wlaxhrl.tistory.com/77)
[RealmTeam/Swift에서 프로토콜 중심 프로그래밍하기](https://academy.realm.io/kr/posts/protocol-oriented-programming-in-swift/)



## UIView의 Layer 객체

UIView에는 렌더링, 레이아웃, 애니메이션 등을 관리하는 코어 애니메이션 클래스인 CALayer가 있습니다. 모든 UIView는 CALAyer 객체인 layer 프로퍼티를 갖고 있으며, shadow, rounded corner, colored border, 3D transform, masking contents, animation 등 화면에 대한 특성을 조절할 수 있는 기능을 제공해줍니다. layer는 뒷단 레이어로 알려져있고, view hierarchy의 개념과 매우 유사하게 layer tree라는 구조가 있습니다. 

## UIApplication 객체의 컨트롤러 역할은 어디에 구현해야 하는가?

[겸손할 겸/현재 실행 중(혹은 실행할) 앱의 최상 뷰 컨트롤러 얻기](https://g-y-e-o-m.tistory.com/93)

## UINavigationController의 역할

![img](https://simajune.github.io/img/posting/UINavigationController2.png)

UINavigationController는 계층적 컨텐츠를 탐색하기 위해 스택 기반으로 구성된 Container View Controller입니다. UINavigationController는 네비게이션 스택의 가장 위에 있는 뷰와 네비게이션이나 특정 역할을 담당하는 뷰, 2개를 함께 보여줍니다. 구조는 ViewControllers(Array)와 NavigationBar Toolbar로 이루어져 있습니다. NavigationBar를 통해 ViewController의 상태를 관리합니다. 



[Tejay/UINavigationController](https://simajune.github.io/2017/10/10/UINavigationController/)
[etst/네비게이션 컨트롤러](https://etst.tistory.com/84)



## 모든 ViewController 객체의 상위 클래스는 무엇이고 그 역할은 무엇인가? 

window



[꼬마상어의 생각](https://littleshark.tistory.com/43)
[Zedd0202/UIWindow. 그리고 UIView](https://zeddios.tistory.com/283)

## Global DispatcherQueue의 Qos에는 어떤 종류가 있는지, 각각 어떤 의미인지 설명하시오

## Object 객체에 대하여 





# ARC



# Autolayout

## 오토레이아웃을 코드로 작성하는 방법(3가지) 

## 스토리보드를 이용했을 때의 장단점

## Safearea에 대하여

## Left Constraint와 Leading Constarint의 차이점 



