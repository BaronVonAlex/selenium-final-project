Step 1
Create repository selenium-final-project and do the following
(IMPORTANT)
This project revolves around https://swoop.ge
Try your best to utilize your Java knowledge when storing data from websites during this project.

Step 2
Create test class in src/test/java named HolidayPageTests.java and automate following cases:
descendingOrderTest:
Go to 'დასვენება' section.
Find the most expensive offer among ALL offers.
Proceed to sort offers from most expensive to least expensive.
Validate that the most expensive offer is displayed first in the list.

ascendingOrderTest:
Go to 'დასვენება' section.
Find the least expensive offer among ALL offers.
Proceed to sort offers from least expensive to most expensive.
Validate that the least expensive offer is displayed first in the list.

filterTest:
Go to 'დასვენება' section.
Check 'კოტეჯი' filter on the left side.
Validate that ALL offers contain word 'კოტეჯი'.
Proceed to sort offers from least expensive to most expensive.
Validate that the least expensive offer is displayed first in the list.

priceRangeTest:
Go to 'დასვენება' section.
Specify price range of your choice on the left side.
Validate that ALL offers fall under the price range you specified.

Step 3
Create test class in src/test/java named LandingPageTests.java and automate following cases:

activeCategoryTest:
Hover on 'კატეგორიები', choose 'სპორტი'->'კარტინგი' from the dropdown.
Validate that the URL is https://www.swoop.ge/category/2058/sporti/kartingi
Validate that the 'კარტინგი' element on the left side has #6E7CFA color

logoTest:
Go to 'დასვენება' section.
Click on Swoop logo
Validate that the logo takes the user back to landingPage
Step 4
Create test class in src/test/java named MoviePageTests.java
Go to 'კინო'
Select the first movie in the returned list and click on ‘ყიდვა’ button
Scroll vertically (if necessary), and horizontally and choose ‘კავეა ისთ ფოინთი’
Check that only ‘კავეა ისთ ფოინთი’ options are returned
Click on last date and then click on last option
Check in opened popup that movie name, cinema and datetime is valid
Choose any vacant place
Register for a new account
Fill all fields with valid data except for email
Validate that error message ‘მეილის ფორმატი არასწორია!' has appeared.

Step 5
Create testng.xml and configure all cross-browser testing (Chrome, Edge, Firefox).
Cross-browser tests should run in parallel to each other.

Step 6
Push everything and add tikomjavanadze18@gmail.com and irinainashvili1999@gmail.com as reviewers