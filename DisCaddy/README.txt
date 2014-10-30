/********************************************************************************************/
TEAM MEMBERS
/********************************************************************************************/

Nick Dechant (ntd252)
Scott Stephens (sts768)
Nick Kubala (nk4682)

/********************************************************************************************/
BRIEF INSTRUCTIONS ON APP USE
/********************************************************************************************/

This app functions as a scorecard app for disc golf. Disc golf works just like normal ball golf, where the object is to get a disc into the basket of all 18 holes in as few strokes as possible. Each hole has a par associated with it (usually between 2 and 5 strokes).

The main functionality of the app is to create and save scorecards with player profiles associated with them. The app contains databases that save player profiles (with names, favorite courses and discs associated with them), courses (with names and pars associated with them), and scorecards (with game names, course names, player names, and scores associated with them).

To create a new scorecard, click the "New Scorecard" button. This will take the user to a screen where the course can be selected.

Once a course has been selected, the next step is to select the active players. The user will be taken to a screen where a list of players that have been created in the app is shown, and can be selected from to add to the new scorecard. Once all participating players have been added/created, the scorecard will be created.

The scorecard will consist of a screen for each hole on the course. On these screens, each player will be displayed along with his/her score. There are buttons for incrementing and decrementing the scores as needed.

Once the game is over, scorecards can be saved for later use.

/********************************************************************************************/
FEATURES COMPLETED
/********************************************************************************************/

Player profile creation and viewing (with name, favorite course/disc). Pictures to be added later.

Basic scorecard: active players, scores for each player, ability to update scores as needed.

Course location: Google Map API used to locate closest disc golf courses.

/********************************************************************************************/
FEATURES NOT YET COMPLETED
/********************************************************************************************/

Course selection: Currently the user cannot select the course to play. This is the most integral functionality that is lacking in the app right now. We had issues with database population (not sure where course information can be obtained and stored within the app), but it will be resolved before beta.

Disc Information: There is no integrated disc information feature. This will be completed before beta.

UI Tweaking: Most of the UI is a bit bland. This will obviously be changed before beta release.

Full GPS Location Data: Instead of using the Google Maps API, the app will eventually use the phone's location data to find nearby courses. Searching Google essentially does the same thing for the time being.

/********************************************************************************************/
FEATURES ADDED THAT WEREN'T SPECIFIED IN PROTOTYPE
/********************************************************************************************/

N/A

/********************************************************************************************/
CODE CHUNKS/CLASSES OBTAINED FROM OTHER SOURCES
/********************************************************************************************/

The main code we took from other sources was code for custom adapters for displaying information. (http://www.vogella.com/tutorials/AndroidListView/article.html#adapterown_custom) This mainly applies to the ScorecardCustomAdapter class.

We used a bit of help for SQL queries as well. This applies to the PlayerDbAdapter, CourseDbAdapter, and ScorecardDbAdapter classes.

/********************************************************************************************/
ORIGINAL CODE/CLASSES
/********************************************************************************************/

All of the classes contained in this project are original, with the exception of snippets contained in the four classes mentioned above.