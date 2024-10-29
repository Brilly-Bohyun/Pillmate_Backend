# 💊 Pillmate

## 🌟 개요
Pillmate는 만성질환 환자 맞춤형 복용 관리 및 건강 관리를 지원하기 위해 설계된 애플리케이션으로, 사용자들에게 편리한 복약 관리 경험을 제공하고자 합니다.
이 저장소는 어플리케이션의 서버 부분인 벡엔드의 소스코드를 담고 있으며 데이터 처리, 사용자 인증, 그리고 여러 서비스와의 통합을 담당합니다.

## 🛠️ 사용 기술
- **🖥️ Java (Spring Boot)**: REST API 개발 및 안정적인 백엔드 로직 구현에 사용되었습니다.
- **📦 Gradle**: 프로젝트 빌드와 종속성 관리를 위한 자동화 도구입니다.
- **💾 MySQL**: 사용자 정보 및 약물 기록을 저장하기 위한 관계형 데이터베이스입니다.
- **📝 Postman**: API 개발과 테스트, 문서화를 위해 활용되었습니다.

## ✨ 기능
- 🔑 사용자 인증 및 권한 부여
- 💊 약물 및 스케줄에 대한 CRUD 작업 기능
- 🌐 클라이언트와의 원활한 상호작용을 위한 RESTful API 제공

## 🚀 설정 방법 (개별적으로 application.properties를 구성하셔야합니다)
1. 저장소를 클론합니다:
   ```sh
   git clone https://github.com/Brilly-Bohyun/Pillmate_Backend.git
   ```
2. 프로젝트 디렉터리로 이동합니다:
   ```sh
   cd Pillmate_Backend
   ```
3. Gradle을 사용하여 프로젝트를 빌드합니다:
   ```sh
   ./gradlew build
   ```
4. 애플리케이션을 실행합니다:
   ```sh
   ./gradlew bootRun
   ```

## 📑 API 문서화
Postman을 활용하여 API 엔드포인트를 문서화하고 있습니다.


## 👥 기여
- **최보현**: 
  - **🖥️ 백엔드 개발**: Java Spring Boot를 활용하여 REST API를 설계하고, 사용자 인증 및 권한 부여 기능을 구현했습니다. 또한, 사용자가 효과적으로 약물과 스케줄을 관리할 수 있도록 CRUD 기능을 포함했습니다.
  - **👤 사용자 관리 API 개발**: 사용자 등록, 로그인, 로그아웃 등 사용자 인증 관련 API를 설계하고 구현하였습니다.
  - **💊 약물 관리 API 개발**: 약물 정보를 위한 CRUD API를 구현하여 사용자가 약물을 등록하고 이를 관리할 수 있도록 지원했습니다.
  - **🗓️ 스케줄 관리 API 개발**: 복약 스케줄을 관리하기 위한 API를 구현하여 사용자가 알림 시간을 설정하고 조정할 수 있도록 하였습니다.
  - **⏰ 알람 설정 API 개발**: 복약 알림을 설정하여 사용자가 설정한 시간에 알림을 받을 수 있도록 알람 기능을 개발하였습니다.
  - **💾 데이터베이스 설계 및 관리**: MySQL을 이용하여 사용자 정보와 약물 기록 데이터베이스를 설계하고 관리하였습니다.
  - **📝 API 문서화 및 테스트**: API 엔드포인트의 문서를 Postman을 통해 작성하고 테스트하였습니다.
