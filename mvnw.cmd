@echo off
setlocal

set "ROOT_DIR=%~dp0"
set "MAVEN_DIR=%ROOT_DIR%.tools\apache-maven-3.9.9"
set "MAVEN_CMD=%MAVEN_DIR%\bin\mvn.cmd"

if not defined JAVA_HOME (
    if exist "C:\Program Files\Eclipse Adoptium\jdk-17.0.18.8-hotspot\bin\java.exe" (
        set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.18.8-hotspot"
    )
)

if not exist "%JAVA_HOME%\bin\java.exe" (
    echo Java was not found. Install JDK 17 and try again.
    exit /b 1
)

if not exist "%MAVEN_CMD%" (
    echo Local Maven was not found in .tools. Download or restore apache-maven-3.9.9 first.
    exit /b 1
)

set "PATH=%JAVA_HOME%\bin;%MAVEN_DIR%\bin;%PATH%"
call "%MAVEN_CMD%" %*

