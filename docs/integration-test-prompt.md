# 레시피 검색 통합 테스트
## 검색 + 결과 표시 통합 테스트

검색 + 결과 표시 통합 테스트 구현
시나리오
- 검색창에 키워드를 입력
- 잠시 대기
- Fake Repository에서 결과를 받아
- 리스트로 화면에 표시

테스트 요구사항
- 텍스트 입력 → 잠시 대기 → 리스트가 표시되는지
- ViewModel ↔ Repository ↔ UI 연결 검증
- 실제 서버 ❌, Fake Repository ⭕ (dev 또는 qa 환경)

위와 같이 명령.

### 첫 번째 명령(실패)
"통합 테스트 실패" 한다고 명령

### 두 번째 명령(실패)
```Text
java.lang.RuntimeException: Failed to instantiate test runner class         
  androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner               
...
```
초기화? 부분이 실패해서 "실패 원인" 알려 줌

### 세 번째 명령
- `runBlocking`을 사용하고 검색 데이터와 결과 데이터가 일치하지 않음

"runBlocking 대신에 runTest로 변경해주고, textInput과 검색된 레시피의        
데이터가 일치하지 않는다 맞게 다시 변경해" 명령.

# 레시피 검색 리스트 -> 상세 화면 네비게이션 테스트
시나리오
- 리스트 화면
- 특정 아이템 클릭
- 상세 화면으로 이동
- ID가 ViewModel에 제대로 전달되었는지 확인

테스트 요구사항
- Navigation 발생 여부
- 전달된 ID로 상세 데이터가 정상 표시되는지
- 뒤로 가기 시 상태 유지

위와 같이 명령.

## 첫 번째 명령 (실패)
`composeTestRule.activity` 컴파일 에러 `Unresolved reference 'activity'`

## 두 번째 명령 (실패)
```Text
androidx.compose.ui.test.ComposeTimeoutException: Condition still not satisfied after 5000 ms
at androidx.compose.ui.test.AndroidComposeUiTestEnvironment$AndroidComposeUiTestImpl.waitUntil(ComposeUiTest.android.kt:809)
at androidx.compose.ui.test.junit4.AndroidComposeTestRule.waitUntil(AndroidComposeTestRule.android.kt:378)
at com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.SearchRecipeIntegrationTest$searchRecipe_navigateToDetail_displaysDetails$1.invokeSuspend(SearchRecipeIntegrationTest.kt:125)
at com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.SearchRecipeIntegrationTest$searchRecipe_navigateToDetail_displaysDetails$1.invoke(Unknown Source:8)
at com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.SearchRecipeIntegrationTest$searchRecipe_navigateToDetail_displaysDetails$1.invoke(Unknown Source:4)
...
```

## 세 번째 명령 (실패)
`/clear` 후 이미지 `@path`를 알려주고 구현하라고 명령.

# 레시피 홈 즐겨찾기 토글 + 상태 유지 테스트
이미지 `@path`를 알려주고 구현하라고 명령.

# 레시피 홈 오류 시나리오 테스트
이미지 `@path`를 알려주고 구현하라고 명령.

텍스트만 있는 것보다 앱 실행 화면과 내용을 같이 분석하니깐 테스트 실패가 안되고 성공했습니다. 