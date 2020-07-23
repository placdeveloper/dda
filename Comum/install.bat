cd /d %~dp0
call mvn clean install -U
echo Exit Code = %ERRORLEVEL%
if not "%ERRORLEVEL%" == "0" exit /b