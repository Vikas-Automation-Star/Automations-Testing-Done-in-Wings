ECHO ON
for /f %%i in ("%0") do set curpath=%~dp0
cd /d %curpath%

REM Delete the allure-results directory if it exists
if exist allure-results (
    rmdir /s /q allure-results
)

call mvn clean install -DTestNGFile=TestNG/sampleSuite.xml
call mvn compile exec:java
