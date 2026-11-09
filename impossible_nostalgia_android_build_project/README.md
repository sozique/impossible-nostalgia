# Impossible Nostalgia — offline Android installation

An Android wrapper for Anna Soz's *Impossible Nostalgia*. The HTML, approximately 30 original scenes, and MP3 audio are bundled in `app/src/main/assets/www` and load from the device itself. The application requests no internet permission and contains no Netlify interface.

## Current status

This archive is a **ready-to-build Android project, not a compiled APK**. It has not been compiled or tested on physical Honor devices yet. Build the debug APK with Android Studio or the GitHub Actions workflow described below.

## Modes

- **Interactive:** the original navigation interface, sounds, and scene selection. Scenes also advance after approximately 38 seconds without action, as in the existing game.
- **Installation:** select `View → Installation mode (fullscreen)`. The Windows-style controls disappear, and the image fills the screen. Scenes change automatically every 22–44 seconds with the existing subtle light/glitch animations. Long-press the image for **1.8 seconds** to return to the navigation interface.
- Sounds are **off by default**. Enable them via `ENABLE AUDIO`, `Sound`, or `Ambience` before switching to fullscreen installation mode. The main audio file is built in, with no streaming.
- The display is kept awake while the app is open. Make sure the phone has stable power and ventilation when used inside an installation.

## Build a test APK without installing Android Studio on your Mac

1. Create a GitHub repository (can be private for testing). Upload **all the contents of this project folder**, including the `.github/workflows/build-apk.yml` file, to its root.
2. In GitHub, open the **Actions** tab and allow workflows if prompted.
3. Select **Build offline Android APK → Run workflow**.
4. Wait for the workflow to complete. Open the successful run and download the artifact **`impossible-nostalgia-android-test-apk`**.
5. Unzip that artifact to get `app-debug.apk` and copy it to each Honor phone.
6. Open the APK on the phone and authorize installation from that source if Android asks. Disable that authorization again after installing.

The workflow uses the Android SDK and Gradle on GitHub's build machine. It is a *debug-signed, test-only APK*. A public release requires an app-specific release signing key and additional testing.

## Build locally (if you have Android Studio)

Import this folder as an Android Gradle project, let Gradle sync, and select **Build → Build APK(s)**, or run `gradle :app:assembleDebug` with Android SDK and Gradle configured. The generated file will be at `app/build/outputs/apk/debug/app-debug.apk`.

## Compatibility and caveats

- Minimum Android 8.0 (API 26). Confirm the installed Android version and the device's Android System WebView. Honor 30 Pro+, V30 Pro, and 20 Pro still need real-device testing.
- App opens in immersive fullscreen and uses `FLAG_KEEP_SCREEN_ON`. There is no hidden network connection, account, or telemetry in the wrapper.
- This uses a local `file:///android_asset` WebView origin (no service worker). The existing audio files and image data are local. Validate both sound tracks and all visual effects on each phone before exhibiting.
- For updates, rebuild and reinstall an APK with the same package ID. A debug key generated on a different build machine may require removing the earlier debug app first (which clears its local data).
- Current app ID is `com.annasoz.impossiblenostalgia`. Package/version should be finalized before distribution beyond the installation.

## The project is not the Netlify website

The Netlify site remains untouched and may continue as a test version. This native project does not display or fetch content from `impossible-nostalgia.netlify.app`.
