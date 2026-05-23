@echo off
echo Building project...
call mvnw.cmd clean package -DskipTests
if errorlevel 1 (
    echo Build failed!
    pause
    exit /b 1
)

echo Starting application...
start "TPO5 Server" java -jar target\TPO5_SG_S29792-0.0.1-SNAPSHOT.jar

echo Waiting for server to start...
timeout /t 10 /nobreak >nul

start "" http://localhost:9999
pause
