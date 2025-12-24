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

