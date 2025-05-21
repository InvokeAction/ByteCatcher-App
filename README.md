<p align="center">
  <img src="https://github.com/user-attachments/assets/687267e4-4468-4b12-8c11-f82018fe0a5e" alt="byteCatcher" width="300"/>
</p>

# 📥 ByteCatcher

**ByteCatcher** is a minimal, JavaFX-powered desktop application for downloading videos and audio from popular platforms like **YouTube, Udemy**, and more. It wraps powerful tools like `yt-dlp` and `ffmpeg` under a user-friendly GUI, making media downloading accessible to everyone — no command-line knowledge needed.

## 🚀 Features

- 🎨 JavaFX GUI (built with Scene Builder)
- 🔧 Configurable download directory
- 🎞️ Download videos or audio
- 📂 Format options (MP4, MP3, WebM)
- ⚙️ Uses `yt-dlp` and `ffmpeg` behind the scenes
- 📦 Portable and open source

## 🧰 Tech Stack

| Tech | Purpose |
|------|---------|
| **Java 17** | Core language |
| **JavaFX 21** | GUI framework |
| **Maven** | Build tool and dependency manager |
| **yt-dlp** | Backend media downloader |
| **ffmpeg** | Audio/video processing |
| **FXML** | UI layout definitions |
| **JavaFX Maven Plugin** | Run JavaFX apps via Maven |

## 🛠️ Project Structure

```
ByteCatcher/
├── src/
│   ├── main/
│   │   ├── java/com/bytecatcher/app/
│   │   │   ├── App.java                # JavaFX Launcher
│   │   │   ├── Controller.java         # Main Controller logic
│   │   │   └── helper/Youtube.java     # Download logic
│   │   └── resources/com/bytecatcher/app/
│   │       ├── MainScene.fxml          # GUI layout
│   │       └── images/byteCatcher.png  # Icons and assets
├── pom.xml                             # Maven build config
```

## 📦 Dependencies (Maven)

```xml
<dependencies>
  <!-- JavaFX Core -->
  <dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>21</version>
  </dependency>
  <dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-fxml</artifactId>
    <version>21</version>
  </dependency>
</dependencies>

<build>
  <plugins>
    <plugin>
      <groupId>org.openjfx</groupId>
      <artifactId>javafx-maven-plugin</artifactId>
      <version>0.0.8</version>
      <configuration>
        <mainClass>com.bytecatcher.app.App</mainClass>
      </configuration>
    </plugin>
  </plugins>
</build>
```

## 🧪 How to Run

1. **Clone the project:**

```bash
git clone https://github.com/your-org/ByteCatcher.git
cd ByteCatcher
```

2. **Install Java 17 and Maven** (if not already installed)

3. **Run the app using Maven:**

```bash
mvn clean javafx:run
```

✅ A JavaFX window will open. Enter the video URL and select the download location.

## 🧩 How It Works

- User enters a URL into the GUI
- On clicking **Download**, the app calls a background `Thread`
- This thread uses the `yt-dlp` executable and `ffmpeg` to download and process media
- The download location is picked using a JavaFX `DirectoryChooser`
- The backend logic resides in `Youtube.java` under `helper/`

## 🧑‍💻 How to Contribute

1. 🍴 Fork this repo
2. 👯 Clone your fork
3. 🔨 Create a feature branch (`git checkout -b new-feature`)
4. 👩‍💻 Write code + test
5. 🧼 Format and clean up
6. 📩 Submit a pull request (PR)

Please follow clean code practices and Java naming conventions. Keep GUI changes in FXML where possible.

## 🧭 Roadmap (Planned Features)

- [ ] Video quality selector (360p/720p/1080p)
- [ ] Download progress bar
- [ ] Support for playlists
- [ ] Auto-update mechanism

## 📜 License

This project is open-sourced under the **MIT License** — see the [LICENSE](./LICENSE) file for details.

## 🙌 Credits

- [yt-dlp](https://github.com/yt-dlp/yt-dlp)  
- [ffmpeg](https://ffmpeg.org/)  
- JavaFX, OpenJFX team  
