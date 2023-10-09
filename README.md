## Code Sharing Platform 

There is a lot of collaboration in programming: many projects require sharing your code with other developers. Using open web resources such as Pastebin is not always an option. In this project, it is aimed to create a code sharing platform, its own secure version.


The `ApiController` class and `HtmlController` class functionalities are summarized below:

### ApiController:
- **Provides RESTful API:** `ApiController` provides RESTful API endpoints. It responds to requests coming to API endpoints and returns data.
- **Retrieve Code Items:** Retrieves code items associated with a specific ID.
- **Get Latest Codes:** Retrieves the most recently added code items.
- **Add New Code:** Adds a new code item and returns its identifier.
- **Update View and Time Limits:** Updates the view and time limits of code items.
- **Data Format:** Data returned from API endpoints is in JSON format.

### HtmlController:
- **Serves HTML Pages:** `HtmlController` serves HTML pages, representing the user interface.
- **Display Code Items:** Displays a code item associated with a specific ID in HTML format.
- **Show Latest Codes:** Displays the most recently added code items in HTML format.
- **Show New Code Submission Form:** Displays a form for users to submit new code items.
- **Update View and Time Limits:** Updates the view and time limits of code items.
- **Data Format:** HTML pages that constitute the user interface.

`ApiController` provides RESTful APIs and returns data in JSON format, whereas `HtmlController` generates HTML pages and represents the user interface. These two controller classes enable the application to interact both with the outside world (other systems) and users.

### CodeService:
The `CodeService` class is used to manage and store code items. This class provides access to data from `CodeRepository` and executes the business logic.

Its functions are as follows:

1. **`addCodeToStorage` Method:** This method is used to store a code item. It creates a new code item in the database or updates an existing one.

2. **`getCodeFromStorage` Method:** This method retrieves a code item associated with a specific ID. If a code item with a specific ID cannot be found, it throws a `CodeNotFoundException` error.

3. **`updateViewById` Method:** This method updates the view count of a code item associated with a specific ID. If the view count drops to zero, the code item is deleted.

4. **`updateTimeById` Method:** This method updates the time limit of a code item associated with a specific ID. If the time limit has expired, the code item is deleted.

5. **`updateLocalTimeById` Method:** This method updates the time limit of a code item associated with a specific ID. The time limit is calculated based on the creation time of the code item. If the time limit has expired, the code item is deleted.

6. **`getLastCode` Method:** This method retrieves the most recently added code items. However, code items with view or time limits are not included in this list. This method returns a list containing the most recently added code items and the number of code items to be displayed per page.

This class is used to perform operations related to storing, updating, and retrieving code items. These operations are invoked by other classes like `ApiController` and `HtmlController`, and this class manages the database operations.
