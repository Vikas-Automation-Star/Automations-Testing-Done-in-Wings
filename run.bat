ECHO ON
for /f %%i in ("%0") do set curpath=%~dp0
cd /d %curpath%

 
if exist allure-results (
    rmdir /s /q allure-results
)
 
mvn clean install "-DTestNGFile=TestNG/MenuItems/regressionNew.xml"
call mvn compile exec:java
 