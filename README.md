
<img width="256" height="180" alt="logo" src="https://github.com/user-attachments/assets/d5d2816b-f148-444c-b8eb-cf5b9405aa3a" /><br>

# 💸 WEAVUS_BANK_WEB_TEAM_PROJECT

Spring BootとSpring Securityを活用した銀行アプリケーションです。会員登録・ログイン・口座開設・振込・取引履歴表示など、基本的な銀行機能に加え、為替レート・郵便番号・金融ニュースの外部APIも活用しています。

<br><br>

## 🎯 開発目的

- Spring Securityによる認証・認可（ログイン／ログアウト）
- 会員登録と郵便番号APIを使った住所自動入力
- 口座作成と最大2口座までの管理
- 入出金・送金などの基本的な取引処理
- 外部APIを利用した実践的な銀行サービスの実装

<br><br>

## 🕛 開発期間

- 要件定義/基本設計期間 : 7月3日 ~ 7月7日
- 開発期間 : 7月7日 ~ 7月16日

<br><br>

## 🧩 主な機能

### 👤 ユーザー機能
- 郵便番号APIを用いて、住所の自動入力機能付き会員登録
- ログイン / ログアウト処理

### 🏦 口座機能
- 自動で口座番号を生成（最大2口座まで）

### 💰 取引機能
- 他ユーザーへの振込
- 残高不足時はエラーメッセージを表示
- 取引履歴を「全体」「出金」「入金」でフィルター可能

### 🔗 外部API
- 📈 為替レート取得API  
- 🏣 郵便番号API  
- 📰 金融ニュースAPI

<br><br>

## 🛠 使用技術

![Java 17](https://img.shields.io/badge/Java-17-007396?logo=java&logoColor=white)
![Spring Boot 3.4.7](https://img.shields.io/badge/Spring_Boot-3.4.7-6DB33F?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?logo=springsecurity&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2-Database-blue?logo=h2&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-DB1F29?logo=mybatis&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-25A162?logo=junit&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?logo=thymeleaf&logoColor=white)

<br><br>

## 🖥 画面構成

| 📝 会員登録画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1Oux5XXHSU-IN-z_ANuXZMDzC9TcMDJfK3prxNJUfHBw/edit?gid=0#gid=0) |

![-Chrome2025-07-1709-33-18-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/9168273b-1659-4735-bdfa-74a1b40b2a4e)<br>


| 🔐 ログイン画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1SXXUprHHZidPBEh9RtzKgrSBP-abRFdkofEKEnj3xvQ/edit?gid=0#gid=0) |

![-Chrome2025-07-1709-35-40-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/604c81bd-b785-4ac2-bd0a-550f764740b3)<br>


| 🏠 メイン画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1uzQ8i2kqknaoUJi-BggIRW7yA344FJKcZH_Uu5QwaFs/edit?gid=62595112#gid=62595112) |

![-Chrome2025-07-1709-37-09-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/0011a3bb-fc24-4258-afa0-6e52f2ce0917)<br>


| 🛠 会員情報編集画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1Ie9tur_m7OD7j4M5gyH5azxpBaX7AYuRgJ3Ped8Xq_c/edit?gid=0#gid=0) |

![MYPAGE-Chrome2025-07-1709-45-44-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/4c4a6856-8a30-4273-91eb-1928bf97f8fe)<br>


| 🏦 口座開設画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1A85k9rVbdjujobfqciOWPuYqS2uYYHyKKf7av-CmGOs/edit?gid=0#gid=0) |

![-Chrome2025-07-1709-39-50-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/276bde02-efa1-4bf2-8885-7b34576ee979)<br>


| 💸 振込画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1TUS2qzg7EEWoNS2kyuT1P0upgMGd7rN-N7HLVI2nfgo/edit?gid=0#gid=0) |

![-Chrome2025-07-1709-42-30-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/75104176-7e79-4d68-9b90-f987da8911b5)<br>


| 📊 取引履歴画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1Mizwa1XWfeLWeNwP_A-yFE1o5EnixtR3CLz3_6DUn9s/edit?gid=0#gid=0) |

![-Chrome2025-07-1709-44-16-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/17b107b7-b7a3-4796-84de-1d443b311ea2)<br>


| 👤 マイページ | [Google Sheet](https://docs.google.com/spreadsheets/d/1rhVuuzdr6RTq3veOHmWOSELFM8Xt2p_jSyAzWE9FEko/edit?gid=0#gid=0) |

![MYPAGE-Chrome2025-07-1709-45-19-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/c4f46cbd-8ad0-4238-a564-17aa6e5e6e83)<br>


| 🪧 ナビバ | [Google Sheet](https://docs.google.com/spreadsheets/d/1CFtX6bLJUVxWQFGSjU5B5cPnsnZKP-GiGaxRqmP4Lbk/edit?gid=0#gid=0) |

![MYPAGE-Chrome2025-07-1709-56-50-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/d311f8d0-bb0b-4469-b067-a43118fc5700)


<br><br>

## 🤜🤛 開発者
- ソルグァンジン (https://github.com/SeolGwangzin)
- ソウハク (https://github.com/lakkal1201)
