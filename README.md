# 📖 Summary

- 레이아웃 & 사용자 인터페이스를 구현합니다.
- `InputMethodService` 를 활용하여 어느 앱에서나 작동하는 두벌식 한글 키보드를 구현합니다.
- 키보드에 클립보드와 단축키 기능을 구현합니다.

# 👨‍👨‍👦‍👦 Members

<div align="center">
  <table style="font-weight : bold">
      <tr>
          <td align="center">
              <a href="https://github.com/hoyahozz">                 
                  <img alt="김정호" src="https://avatars.githubusercontent.com/hoyahozz" width="80" />            
              </a>
          </td>
          <td align="center">
              <a href="https://github.com/gyurim">                 
                  <img alt="박규림" src="https://avatars.githubusercontent.com/gyurim" width="80" />            
              </a>
          </td>
          <td align="center">
              <a href="https://github.com/INAH-PAK">                 
                  <img alt="박인아" src="https://avatars.githubusercontent.com/INAH-PAK" width="80" />            
              </a>
          </td>
          <td align="center">
              <a href="https://github.com/leehandsub">                 
                  <img alt="이현섭" src="https://avatars.githubusercontent.com/leehandsub" width="80" />            
              </a>
          </td>
          <td align="center">
              <a href="https://github.com/sujin-kk">                 
                  <img alt="임수진" src="https://avatars.githubusercontent.com/sujin-kk" width="80" />            
              </a>
          </td>
      </tr>
      <tr>
          <td align="center">김정호</td>
          <td align="center">박규림</td>
          <td align="center">박인아</td>
          <td align="center">이현섭</td>
          <td align="center">임수진</td>
      </tr>
  </table>
</div>

# 🤝 Convention

## 📚 Branch

- `{behavior}/issue-{number}-{something}` 
- e.g. : `feature/issue-007-data-module`

## 🤔 Issue

- `[{behavior}] {something}`
- e.g. : `[FEATURE] 프로젝트 세팅`

## 🤲 Pull Request

- `[ISSUE-{number}] {something}`
- e.g. : `[ISSUE-007] 데이터 모듈 추가`
- 문장 단위가 아닌, **단어 단위**로 제목을 작성합니다.

## 😊 Commit

- `feat - {something}` : 새로운 기능을 추가했을 때
- `fix - {something}` : 기능 중 버그를 수정했을 때
- `design - {something}` : 디자인 일부를 변경했을 때
- `refactor - {something}` : 코드를 재정비 하였을 때
- `chore - {something}` : 빌드 관련 업무를 수정하거나 패키지 매니저를 수정했을 때
- `docs - {something}` : README와 같은 문서를 변경했을 때
- 문장 단위가 아닌, **단어 단위**로 제목을 작성합니다.

## ⌨️ Coding
- [android-style-guide](https://github.com/PRNDcompany/android-style-guide)의 코딩 컨벤션과 동일하게 진행합니다.

## ⚙️ Tech Stack
`Clean Architecture`, `Multi-Module`, `MVVM`, `Kotlin`, `Hilt`, `Coroutine`, `Room`, `Lifecycle`, `Databinding`, `Livedata`, `Timber`

# ✍️ Implementation

## 김정호

- **프로젝트 베이스 구축**
  - `Clean Architecture` 를 주요 아키텍처 패턴으로 채택 및 구현, 레이어별 모듈 구성
  - `Presentation` 모듈의 경우 `MVVM` 패턴에 따라 베이스 구현
  - `Hilt` 모듈 구현
  - `Room` 데이터베이스 설계 및 구축
  - 규격화된 폰트 적용을 위해 폰트 리소스 구현
- **메인 페이지 UI 구현**
  - 제공된 디자인 시스템에 따라 UI 구현
  - 버튼 클릭 시 `ripple` 이펙트가 나타나도록 구현
  - 반응형으로 모든 기기에서 동일하게 보여지도록 구현
- **다이얼로그 UI 구현**
