cd "C:\Users\Dell\IdeaProjects\wings-testautomation"
call mvn clean install "-DTestNGFile=TestNG/MenuItems/purchaseSuite.xml"
call mvn compile exec:java