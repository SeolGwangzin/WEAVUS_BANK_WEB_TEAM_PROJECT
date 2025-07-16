# 💸 WEAVUS_BANK_WEB_TEAM_PROJECT（Spring Boot）

Spring BootとSpring Securityを活用した銀行アプリケーションです。会員登録・ログイン・口座管理・送金・取引履歴表示など、基本的な銀行機能に加え、為替・郵便番号・金融ニュースの外部APIも活用しています。

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
- 入金、出金、他ユーザーへの送金
- 残高不足時はエラーメッセージを表示
- 取引履歴を「すべて」「出金」「入金」でフィルター可能

### 🔗 外部API
- 📈 為替レート取得API  
- 🏣 郵便番号API  
- 📰 金融ニュースAPI

<br><br>

## 🛠 使用技術

- Java 17
- Spring Boot 3.4.7'
- Spring Security  
- H2 Database  
- MyBatis  
- JUnit  
- Thymeleaf

<br><br>

## 🖥 画面構成

| 🔐 ログイン画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1SXXUprHHZidPBEh9RtzKgrSBP-abRFdkofEKEnj3xvQ/edit?gid=0#gid=0) |

| 📝 会員登録画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1Oux5XXHSU-IN-z_ANuXZMDzC9TcMDJfK3prxNJUfHBw/edit?gid=0#gid=0) |

| 🏠 メイン画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1uzQ8i2kqknaoUJi-BggIRW7yA344FJKcZH_Uu5QwaFs/edit?gid=62595112#gid=62595112) |

| 👤 マイページ | [Google Sheet](https://docs.google.com/spreadsheets/d/1rhVuuzdr6RTq3veOHmWOSELFM8Xt2p_jSyAzWE9FEko/edit?gid=0#gid=0) |

| 🛠 会員情報編集画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1Ie9tur_m7OD7j4M5gyH5azxpBaX7AYuRgJ3Ped8Xq_c/edit?gid=0#gid=0) |

| 🏦 口座開設画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1A85k9rVbdjujobfqciOWPuYqS2uYYHyKKf7av-CmGOs/edit?gid=0#gid=0) |

| 💸 振込画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1TUS2qzg7EEWoNS2kyuT1P0upgMGd7rN-N7HLVI2nfgo/edit?gid=0#gid=0) |

| 📊 取引履歴画面 | [Google Sheet](https://docs.google.com/spreadsheets/d/1Mizwa1XWfeLWeNwP_A-yFE1o5EnixtR3CLz3_6DUn9s/edit?gid=0#gid=0) |

<br><br>

## 🤜🤛 開発者
- ソルグァンジン
- ソウハク
