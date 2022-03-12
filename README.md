# OhouseSample

## Demo
![device-2022-03-12-143139 mp4](https://user-images.githubusercontent.com/48344355/158005346-38c7fde9-6129-4d5d-ac11-1a2e8c2aa2c4.gif)


## Architecture
- MVVM + Clean Architecture
- Feature modules
  - app : 메인 화면
  - auth : 인증 로직 및 화면
  - buildSrc : 디펜던시 관리
  - feature
    - detail : 카드 상세 로직 및 화면
    - feed : 사진 피드 로직 및 화면
    - home : 홈 로직 및 화면
  - shared : 공통 로직
  - navigation : 화면 이동 로직

## Tech
- AndroidX
  - Core-ktx
  - Appcompat
  - ConstraintLayout
  - Fragment-ktx
  - SwipeRefreshLayout
- Google
  - Material
- Network
  - Retrofit
  - Moshi
- Coroutines
- Hilt
- Glide
- DataStore
- Test
  - Junit4
  - MockK
  - MockWebServer
  - Core-testing
  - Truth
  - Coroutines-test
