@echo off
echo Building project...
call mvnw.cmd package -DskipTests
if errorlevel 1 (
    echo Build failed!
    pause
    exit /b 1
)

echo Starting application...
start "TPO5 Server" mvnw.cmd spring-boot:run

echo Waiting for server to start...
timeout /t 10 /nobreak >nul

start "" http://localhost:9999
pause
