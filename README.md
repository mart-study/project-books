# project-books
Books project : 
This project is a simple web service for searching, retrieving, and downloading (description and image) from google books.

API URLs are listed below:
- books/search?title={books's title}&author={books's author} : search book by title and/or author. title parameter is mandatory. 
- books/{book_id} : get book's information by id.
- books/download/{book_id}/description : download book's description in txt file.
- books/download/{book_id}/image : download book's thumbnail in jpeg format.

Project instructions before running the application:
- in google-books.properties, add api-key={your api key for credential}

For information about api key can be found in this link https://cloud.google.com/docs/authentication/api-keys?visit_id=637927766577934480-1480192527&rd=1.
More about Google Books API : https://developers.google.com/books/docs/overview

Technology used:
Spring Boot
Maven
Rest API
Rest Template
JUnit and Mockito
