# 지도를 활용한 실종 동물 신고 웹 서비스 '포인한신' 
** 개발기간 : 2023.03 ~ 2023.12 **
<br/>
<br/>

## 프로젝트 설명 포스터 

<img alt="Poinhanshin Poster" src="https://github.com/user-attachments/assets/af229c9d-7dd5-40e1-b4e6-7650a4cdd65d">

<br/>

## 팀 소개

|                           최무성                           |                             이재성                             |                           유영우                            |                           신종하                           |                           박재영                            |                           구민지                            |
| :--------------------------------------------------------: | :------------------------------------------------------------: | :---------------------------------------------------------: | :--------------------------------------------------------: | :---------------------------------------------------------: | :---------------------------------------------------------: |
| <img width="160px" src="https://github.com/museongchoi.png" /> | <img width="160px" src="https://github.com/fl53hn.png" /> | <img width="160px" src="https://github.com/yoo20370.png" /> | <img width="160px" src="https://github.com/jhShin1557.png" /> | <img width="160px" src="https://github.com/seeksome.png" /> | <img width="160px" src="https://github.com/user0830.png" /> |
|           [@museongchoi](https://github.com/museongchoi)           |         [@fl53hn](https://github.com/fl53hn)         |          [@yoo20370](https://github.com/yoo20370)           |           [@jhShin1557](https://github.com/jhShin1557)           |          [@seeksome](https://github.com/seeksome)           |          [@user0830](https://github.com/user0830)           |
<br/>

### 백엔드 
신종하, 이재성, 유영우, 최무성 

### 프론트
구민지, 박재영

## 프로젝트 소개 
<div>
  반려동물 기르는 사람들이 증가함에 따라 유실 및 유기되는 동물 또한 증가하여 보호소가 부족한 상황에 이르렀다. 그로 인해 많은 유실 및 유기 동물들이 보호받지 못하고 안락사되고 있다. 이 문제를 해결하기 위해 HTML, CSS, JavaScript, Spring Boot를 사용하여 동물 실종 신고 웹 사이트인 포인 한신을 개발하였다. 포인 한신은 유기되거나 실종되는 동물을 지도와 게시판을 활용하여 빠르게 신고하고 구조할 수 있게 하여 안락사를 방지한다. 또한 MBTI 검사를 통해 입양자에게 어울리는 품종을 추천하고 보호 중인 해당 품종 목록을 입양자에게 제공하여 보호 중인 동물을 간편하게 입양하도록 유도한다. 그 외에도 반려인 사이의 원활한 정보교환을 위한 커뮤니티, 보호 중인 동물 정보와 보호소 정보를 제공하는 서비스를 제공한다. 
</div>

## 기술스택
### 백엔드
![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=Java&logoColor=white)
![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white)

### 프론트
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=Bootstrap&logoColor=white)
![Ajax](https://img.shields.io/badge/Ajax-5A29E4?style=for-the-badge&logo=Ajax&logoColor=white)
<br/>
<br/>

## 주요기능 
<p>실종된 동물 혹은 유기된 동물의 위치를 신고하는 기능 </p>
<p>반려인간의 소통을 위한 커뮤니티 기능</p>
<p></p>재미로 자신의 MBTI에 맞는 동물을 추천해주는 기능</p>
</p>유기 동물 정보 및 보호소 정보 제공 기능</p>
<br/>

## 아키텍처 
<img alt="포인한신 아키텍처" src="https://github.com/user-attachments/assets/35ae5fc6-645a-49ae-94e7-d8b65ad9260b"/>
<br/>
<br/>

## 디렉토리 구조 

```
poinhanshin
├─ src
├─ main
├─ java
│  └─ com
│     └─ project
│        └─ poinhanshin
│           ├─ PoinhanshinApplication.java
│           ├─ controller
│           │  ├─ HomeController.java
│           │  ├─ api
│           │  ├─ board
│           │  ├─ login
│           │  ├─ map
│           │  ├─ mbti
│           │  ├─ member
│           │  └─ protectboard
│           ├─ domain
│           │  ├─ api
│           │  ├─ board
│           │  ├─ etc
│           │  ├─ map
│           │  ├─ mbti
│           │  ├─ member
│           │  └─ protectboard
│           ├─ etc
│           ├─ mapper
│           │  ├─ api
│           │  ├─ board
│           │  ├─ login
│           │  ├─ map
│           │  ├─ mbti
│           │  ├─ member
│           │  └─ protectboard
│           └─ service
│              ├─ api
│              ├─ board
│              ├─ login
│              ├─ map
│              ├─ mbti
│              ├─ member
│              └─ protectboard
└─ resources
├─ mapper
├─ static
│  ├─ css
│  ├─ img
│  └─ js
└─ templates
├─ api
├─ board
├─ fragments
├─ login
├─ map
├─ mbti
├─ members
├─ mypage
└─ protect
```

<br/>
