# 일급 객체 



스위프트는 객체지향 언어이자 동시에 함수형 언어이다. 함수형 언어에서는 함수를 일급 객체로 취급하기 때문에 일급 객체에 대한 정확한 이해가 있어야 한다. 



#### 일급 객체의 특성

1. 객체가 런타임에도 생성이 가능해야 한다.
2. 인자값으로 객체를 전달할 수 있어야 한다.
3. 반환값으로 객체를 사용할 수 있어야 한다.
4. 변수나 데이터 구조 안에 저장할 수 있어야 한다.
5. 할당에 사용된 이름과 관계없이 고유한 구별이 가능해야 한다. 



위의 일급 함수의 특성이 함수에도 똑같이 적용된다. 



## 일급 함수의 특성 

#### 1. 변수나 상수에 함수를 대입할 수 있다. 



```swift
func foo(value: Int) -> String {
	return "Input value is \(value)"
}

let fn = foo(value: 3)
```



위의 코드를 보면 변수에 함수를 대입한 것을 볼 수가 있다. 하지만 함수 자체를 대입하는 것은 아니다. 함수를 대입하기 위해서는 타입에 대해서 알아야 한다. 변수에 함수를 대입하면 그 변수는 특정 타입을 갖게 되는데, 이 타입을 **함수 타입**이라고 한다. 함수 타입은 일반적으로 함수의 형태를 축약한 형태로 사용하며, 함수 명이나 파라미터 명은 필요하지 않아 생략할 수 있다. 그저 어떤 값을 받고 어떤 값을 반환하는지만 표기한다. 



```swift
(인자 타입1, 인자 타입2, ...) -> 반환 타입
```



반환 값이 없으면 `Void`라고 표기해주면 되고, 인자 값이 없는 경우에는 `()` 로 나타내주면 된다.  그래서 위의 예시 함수 foo(value:) 같은 경우에는 아래처럼 함수 타입 형태로 나타낼 수 있다. 



```swift
func foo(value: Int) -> String {
	return "Input value is \(value)"
}

let fn: (Int) -> String 
```



#### 2. 함수의 반환 타입으로 함수를 사용할 수 있다.

함수가 함수를 반환한다는 것은 아래의 예를 이용해서 이해해보자. 

```swift
func calc(_ operand: String) -> (Int, Int) -> Int {
  switch operand {
    case "+" : return plus
    case "-" : return minus
    case "/" : return divide 
    case "*" : return times 
    default : return plus
  }
}

func plus(a: Int, b: Int) -> Int { return a+b }
func minus(a: Int, b: Int) -> Int { return a-b }
func divide(a: Int, b: Int) -> Int { 
  guard b != 0 else { return 0 }
  return a/b 
}
func times(a: Int, b: Int) -> Int { return a*b }
```

`calc` 함수는 인자로 받은 `operand` 를 기준으로 다른 함수를 반환한다. 그 함수의 타입은 `(Int, Int) -> Int` 형태로 두 개의 `Int` 인자를 연산한 결과를 반환한다. 



#### 3. 함수의 인자 값으로 함수를 사용할 수 있다.

함수를 입력받는 인자 값은 함수 타입으로 정의되어야 한다. 

```swift
func divide(base: Int, 
           success sCallBack: () -> Void, 
           fail fCallBack: () -> Void) -> Int {
  guard base != 0 else {
    fCallBack() 
    return 0 
  }
  
  defer {
    sCallBak() 
  }
  return 100/base 
}

func successThrough() {
  print("성공")
}

func failThrough() {
  print("실패")
}

divide(base: 30, success: successThrough, fail: failThrough)
```

위의 코드는 함수 인자를 사용해 콜백을 처리하고 있다. 함수의 내부 코드를 수정하지 않고도 외부에서 함수 내부의 실행 과정에 간섭할 수가 있다. 완전히 실행 흐름을 꺾지는 못하지만, 그 실행 흐름에 합류해 추가 기능을 수행하는 것은 가능하다. 