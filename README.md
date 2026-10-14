# JAVED STUDY STUDIO Android APK

Android wrapper for the existing JAVED STUDY STUDIO web app.

- Existing web UI is kept in `app/src/main/assets/index.html`.
- Supabase remains the backend.
- Google Apps Script is not used by the Android wrapper.
- The app uses a WebView and opens WhatsApp links externally.
- GitHub Actions builds a debug APK from the `main` branch.

## Build
GitHub Actions → Build APK → Run workflow.
The generated APK is uploaded as an artifact named `JavedStudyStudio-debug-apk`.
