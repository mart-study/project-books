# project-books
Books project : 
This project is a simple web service for searching, retrieving, and downloading (description and image) from google books.

API URLs are listed below:
- books/search?title={books's title}&author={books's author} : search book by title and/or author. title parameter is mandatory. 
- books/{book_id} : get book's information by id
- books/download/{book_id}/description : download book's description in txt file
- books/download/{book_id}/image : download book's thumbnail in jpeg format

Project instructions before running the application:
- add file : google-books.properties in classpath
- in google-books.properties, add base-url={base url for goole books api} and api-key={your api key for credential}

For information about the base url and api key can be found in this link https://developers.google.com/books/docs/v1/using
