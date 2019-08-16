call runcrud
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo rundcrud cannot start application
goto fail

:browser
start  http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Address not found
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.