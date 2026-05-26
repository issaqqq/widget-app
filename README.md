readme_content = """# Tap Counter Widget

A minimalist, "Nothing OS" inspired Android home screen widget that keeps track of your taps.

## Features
* **Tap to Count:** Increments the counter from 0 to 99, then resets to 0.
* **Persistent Storage:** Uses `SharedPreferences` to remember your count even if you restart your device or remove/re-add the widget.
* **Minimalist Design:** Features a dark circular background and a custom dot-matrix font inspired by Nothing OS.
* **1x1 Grid Size:** Compact and unobtrusive on your home screen.

## Tech Stack
* **Language:** Kotlin
* **IDE:** Android Studio
* **Core Components:** `AppWidgetProvider`, `RemoteViews`, `PendingIntent`, `SharedPreferences`

## Prerequisites
* Android Studio (Ladybug or newer recommended)
* Minimum SDK: API 24 (Android 7.0)

## Installation & Setup
1. **Clone or Download** this repository and open the project in Android Studio.
2. **Add the Custom Font:**
   * Download a free "Nothing" style dot-matrix font (e.g., NDot).
   * Rename the file to exactly `nothing_font.ttf`.
   * Place the file inside the `app/src/main/res/font/` directory. *(Create the `font` directory if it doesn't exist)*.
3. **Build the Project:** Click the **Sync Project with Gradle Files** button, then click the **Run** (green play button) to install the app on your device or emulator.

## How to Use
1. Once installed, go to your Android device's home screen.
2. Long-press on an empty space and select **Widgets**.
3. Scroll down to find the **Tap Counter** app.
4. Long-press the 1x1 widget preview and drag it onto your home screen.
5. Tap the widget to start counting!

## File Structure Overview
* `TapCounterWidget.kt`: The core logic handling widget updates, tap events, and saving the count.
* `widget_counter.xml`: The visual layout of the widget (circle background and text).
* `tap_counter_info.xml`: The widget configuration file defining its grid size and layout.
* `circle_background.xml`: The drawable file creating the dark grey circular shape.
* `AndroidManifest.xml`: Registers the `AppWidgetProvider` receiver.

## License
This project is open-source and free to use or modify for personal and educational purposes.
"""

file_path = "README.md"
with open(file_path, "w", encoding="utf-8") as f:
    f.write(readme_content)

print(f"File generated at: {file_path}")
