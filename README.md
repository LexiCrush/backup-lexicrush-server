# Lexicrush Service Layer + Sqlite Storage
Server-side implementation of Lexicrush app. Spring Boot application responsible for generating questions, managing user sessions, and providing a REST API for the client-side application.

## Prerequisites
* Openjdk-17-jdk
* Apache Maven
* SQLite

## Launch Springboot Tomcat Server Locally
1. Clone the repository:
```
git clone https://github.com/<your username>/backup-lexicrush-server.git
```

2. Build the project using Maven:
```
mvn clean package
```

3. Run the Spring Boot application:
```
mvn spring-boot:run
```

4. Serves on port 8080 by default.

# Endpoints

The following endpoints are used in this application:

## GET Requests

### `/api/getq`

Returns a random question.

Example Response:
```json
{
  "question": "Name a US President Whose Last Name Starts With an H?"
}
```

### `/api/bot`

Returns the bot's answer to a question.

Example Request:
```json
{
  "question": "Name a US President Whose Last Name Starts With an H?"
}
```

Example Response:
```json
{
  "answer": "Hoover"
}
```

### `/api/useHint`

Returns a hint as a string.

Example Request:
```json
{
  "question": "Name a Capital of a Country in the World That Starts With a P?"
  "accessToken": "ak2k2 | 101239490..."
}
```

Example Response:
```json
{
  "hint": "P _ r _ s"
}
```

### `/api/getHintCount`

Returns available hint count.

Example Request:
```json
{
  "accessToken": "ak2k2 | 101239490..."
}
```

Example Response:
```json
{
  "hintCount": 3
}
```

### `/api/getCoinCount`

Returns available coins.

Example Request:
```json
{
  "accessToken": "ak2k2 | 101239490..."
}
```

Example Response:
```json
{
  "coinCount": 10
}
```

### `/api/getCurrentScore`

Returns the user's score.

Example Request:
```json
{
  "accessToken": "ak2k2 | 101239490..."
}
```

Example Response:
```json
{
  "score": 20
}
```

### `/api/getHighScore`

Returns the user's high score.

Example Request:
```json
{
  "accessToken": "ak2k2 | 101239490..."
}
```

Example Response:
```json
{
  "highScore": 30
}
```

### `/api/getNumGames`

Returns the number of games played.

Example Request:
```json
{
  "accessToken": "ak2k2 | 101239490..."
}
```

Example Response:
```json
{
  "numGamesPlayed": 5
}
```

### `/api/getHighScoreLeaderboard`

Returns the high score leaderboard as a 2d array.

Example Response:
```json
{
  "leaderboard": [
    ["Bob", "25"],
    ["Alice", "30"],
    ["John", "20"]
  ]
}
```

## POST Requests

### `/api/checkans`

Returns 0 if the answer is incorrect, and length of matched word if correct.

Example Request:
```json
{
  "question": "What is the capital of France?",
  "answer": "Paris"
}
```

Example Response:
```json
{
  "score": 5
}
```

### `/api/updateCurrentScore`

Updates the user's current score.

Example Request:
```json
{
  "accessToken": "eyJhbGciOiJIUzI1N...",
    "question": "Name a state in the USA?"
  "playerAnswer": "New Jersey",
  "botAnswer": "New York",
}
```

Example Response:
```json
{
  "message": "You Win! Your answers was 2 letters longer then the oponents."
}
```

### `/api/endGame`

Ends the game and updates the users stats. Sets a new highscore if applicable.
