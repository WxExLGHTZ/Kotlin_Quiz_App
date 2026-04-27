# QuizApp

An Android trivia quiz app with 50 German general knowledge questions, randomized question selection per game, color-coded answer feedback, and a persistent Top 5 leaderboard backed by a Room database.

## Motivation

A quiz application for testing and expanding general knowledge in a casual, game-like format. Each round presents 5 randomly selected questions from a pool of 50, with immediate visual feedback on correct and incorrect answers. Scores are persisted locally so players can track their best results over time.

## Features

- Presents 5 randomly selected questions per game from a pool of 50 German trivia questions
- Shows 4 answer options per question with color-coded feedback (green for correct, red for wrong)
- Tracks score in real-time with a progress bar showing current question number
- Saves scores to a local Room database and displays a Top 5 leaderboard
- Validates player name input before starting a new game
- Disables answer options after submission to prevent changing answers
- Adapts the action button text contextually: "Antworten" → "Nächste Frage" → "Zum Ergebnis"

## Tech Stack

| Category | Technology |
|----------|-----------|
| Language | Kotlin |
| Platform | Android (API 31) |
| Architecture | MVVM (ViewModel, LiveData, Data Binding) |
| Database | Room (SQLite) |
| Navigation | Jetpack Navigation Component with SafeArgs |
| IDE | Android Studio |

## Architecture

The app follows the MVVM pattern with four fragments managed by the Jetpack Navigation Component. Each fragment has a dedicated ViewModel and ViewModelFactory. User interactions in the fragments are forwarded to the ViewModels, which update LiveData properties that the fragments observe to update the UI. Data Binding connects the XML layouts directly to the ViewModels for click handlers.

The navigation flow passes data between fragments via SafeArgs: the player name travels from `TitleFragment` to `QuestionFragment`, and both name and score are passed from `QuestionFragment` to `ResultFragment`. The Room database stores player scores through a DAO with coroutine-based insert operations, and the `ScoreFragment` reads the Top 5 entries sorted by points.

```
TitleFragment ──(name)──► QuestionFragment ──(name, score)──► ResultFragment ──► TitleFragment
      │                                                                              
      └──► ScoreFragment (Top 5 leaderboard)
```

## Getting Started

### Prerequisites

- Android Studio
- Android device or emulator (API 31+)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/quizapp.git
   ```

2. Open the project in Android Studio.

3. Connect your device or start an emulator.

4. Click **Run** to build and install the application.

## Usage

1. Enter your name on the title screen and tap **Start**.
2. Read each question and select one of the four answer options.
3. Tap **Antworten** to submit your answer. The correct answer highlights in green; a wrong selection highlights in red.
4. Tap **Nächste Frage** to proceed to the next question.
5. After the final question, tap **Zum Ergebnis** to see your score.
6. If your score is greater than 0, it is saved to the leaderboard.
7. View the Top 5 leaderboard from the title screen via the score button.


## Authors

- William Eppel
- Siwar Aouididi
