@echo off
call mvn clean install
if %errorlevel% neq 0 ( 
	exit /b %errorlevel%
)