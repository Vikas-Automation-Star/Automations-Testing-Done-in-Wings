cd "C:\Users\Dell\IdeaProjects\wings-testautomation"
mvn clean install "-DTestNGFile=TestNG/MenuItems/salesSuite.xml"
call mvn compile exec:java
pause