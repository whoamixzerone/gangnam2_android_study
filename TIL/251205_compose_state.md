# Compose
## State
### 상태 관리의 중요성
- UI는 사용자와의 상호작용에 따라 동적으로 변화
- 상태(state)는 UI의 현재 상황을 나타내며, 사용자 입력, 네트워크 응답, 애플리케이션 로직 등 다양한 요소에 의해 변경될 수 있다
- 상태를 적절히 관리하지 않으면 UI가 일관되지 않거나, 예기치 않은 동작을 초래할 수 있다

## Composable의 상태 관리
- 컴포즈는 `rememberXXX` 형식의 상태 관리용 함수를 제공
- 상태가 변경되면 **Compose**는 자동으로 UI를 재구성하여 최신 상태를 반영(웹으로 치자면 **리렌더링**)

## 사용 예시
`mutableStateOf()` 사용 시 변수에 직접적으로 값이 저장이 되지 않고, `State`의 필드인 `value`에 저장 되기 때문에 `변수.value`로 값을 사용할 수 있다. 

```kotlin
@Composable
fun Counter() {
    val counter = remember { mutableStateOf(0) }
    
    Column {
        Text("Count: ${counter.value}")
    }
}
```

## 구성 변경시 State 유지 (화면 회전, 언어 설정 변경, 테마 변경 등)
화면 회전 시(구성 변경) UI가 재구성 되면서 초기화가 되는데 `rememberSaveable`을 사용하면 유지된다.

## by 델리게이티드 프로퍼티
델리게이티드 프로퍼티를 사용하여 getter/setter 접근을 편리하게 할 수 있다.  
즉, 이전에 `변수.value`로 접근 하던 것을 원래 처럼 `변수`로만 사용 하면 된다.  
단, by를 쓰려면 `getValue`, `setValue`를 import 해야만 사용할 수 있다.

```kotlin
@Composable
fun Counter() {
    val counter by remember { mutableStateOf(0) }
    
    Column {
        Text("Count: $counter")
    }
}
```

## Compose에서의 상태 관리
- Compose는 일반 전역변수의 변경에 대해서 상태 변화를 감지하지 못한다
- **MutableState**: 상태 변화를 감지하고 UI 업데이트
- **remember**: 컴포지션 범위에서 상태 유지
- **rememberSaveable**: 구성 변경(화면 회전 등)이 발생하면 `remember`만으로는 상태가 유지되지 않기 때문에, `rememberSaveable`로 상태 유지
