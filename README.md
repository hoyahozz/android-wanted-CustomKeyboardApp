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


# 이현섭

https://user-images.githubusercontent.com/35682233/195629876-03ce9f00-712f-4fe1-9b90-eba514506998.mp4

기본적인 키보드 ui

```kotlin
private lateinit var qwertyKeyboardLayout: LinearLayout
    private val qwertyMainKeyboardText = arrayOf(
        arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
        arrayOf("ㅂ", "ㅈ", "ㄷ", "ㄱ", "ㅅ", "ㅛ", "ㅕ", "ㅑ", "ㅐ", "ㅔ"),
        arrayOf("ㅁ", "ㄴ", "ㅇ", "ㄹ", "ㅎ", "ㅗ", "ㅓ", "ㅏ", "ㅣ"),
        arrayOf(context.getString(R.string.key_shift), "ㅋ", "ㅌ", "ㅊ", "ㅍ", "ㅠ", "ㅜ", "ㅡ", context.getString(R.string.key_back)),
        arrayOf(
            context.getString(R.string.key_special),
            context.getString(R.string.key_short),
            ",",
            context.getString(R.string.key_space),
            ".",
            context.getString(R.string.key_enter)
        )
    )
    private val qwertySubKeyboardText = arrayOf(
        arrayOf("ㅃ", "ㅉ", "ㄸ", "ㄲ", "ㅆ", "/", "<", ">", "ㅒ", "ㅖ"),
        arrayOf("!", "@", "#", "%", "^", "&", "*", "(", ")"),
        arrayOf("", "-", "\'", "\"", ":", ";", ",", "?")
    )
```

모든앱에 사용하게 만들기

manifest

```kotlin
<service
            android:name=".presentation.keyboard.KeyBoardService"
            android:enabled="true"
            android:exported="true"
            android:label="MyKeyboard"
            android:permission="android.permission.BIND_INPUT_METHOD">
            <meta-data
                android:name="android.view.im"
                android:resource="@xml/xml"/>

            <intent-filter>
                <action android:name="android.view.InputMethod"/>
            </intent-filter>
        </service>
```

쉬프트 키 눌럿을 때 구현

```kotlin
private fun changeCaps() {
        for (keyNum in qwertyMainKeyboardText[SHIFT_CHANGE_LINE].indices) {
            val layout = layoutLines[SHIFT_CHANGE_LINE].children.toList()
            val mainKeyText = layout[keyNum].findViewById<TextView>(R.id.main_key_text)

            if (isCaps) {
                ...
						}
        isCaps = !isCaps
    }
```

해당 ui에서 글씨를 바꾸게 했습니다.
