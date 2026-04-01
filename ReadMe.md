# TrackIP 🌐

A clean, dark-themed Android app to look up geolocation data for any IP address — built with Java and MVVM architecture.

---

## 📱 Screenshots

<p>
  <img src="screenshots/home.png" width="250"/>
  &nbsp;&nbsp;&nbsp;
  <img src="screenshots/result.png" width="250"/>
</p>

---

## ✨ Features

- 🔍 Look up country, city and coordinates for any IP address
- 🌐 Clean dark themed UI
- ⚡ Fast response using ip-api.com
- 🔄 Handles loading, success and error states
- ✅ Input validation and user friendly error messages
- 📱 Tested on real device

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Architecture | MVVM |
| Network | Retrofit + Gson |
| UI | ConstraintLayout + ViewBinding |
| API | ip-api.com (free, no key needed) |

---

## 📂 Project Structure
```
app/src/main/java/com/example/trackip/
├── MainActivity.java
├── IpViewModel.java
├── ApiClient.java
├── ApiService.java
└── IpResponse.java
```

---

## 🚀 How to Run

1. Clone the repo
```bash
   git clone https://github.com/yourusername/TrackIP.git
```
2. Open in **Android Studio**
3. Let Gradle sync finish
4. Run on emulator or physical device

> No API key required — ip-api.com free endpoint is used

---

## 🌐 API Used

[ip-api.com](http://ip-api.com) — Free IP geolocation API, no key required.

Returns country, city, lat/lon and more.

> Free tier allows 45 requests per minute

---

## 📌 Notes

- HTTP is used instead of HTTPS — cleartext traffic is enabled for ip-api.com
- Repository layer is skipped — API is called directly from ViewModel

---

## 👨‍💻 Author

**Hanooq**  
[GitHub](https://github.com/hanook-solve/TrackIP.git)