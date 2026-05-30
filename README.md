# Contact Us

A Java web application where visitors submit a contact request through a public form, and an administrator logs in to view and manage those requests. Built on the MVC pattern with Servlets, JSP, and PostgreSQL.

## Features

- Public contact form with server-side validation (required fields + email format)
- Submissions stored in PostgreSQL
- Password-protected admin area
- Admin dashboard listing requests grouped into Active and Archived
- Archive / unarchive any request
- Session-based authentication with a filter guarding all admin URLs
- Logout that invalidates the session and prevents back-button access to protected pages

## Tech Stack

- Java 21
- Jakarta Servlets & JSP (JSTL)
- Apache Tomcat 11
- PostgreSQL
- Maven
- BCrypt (jBCrypt) for password hashing

## Project Structure

    src/main/
    ├── java/
    │   ├── controllers/   Servlets (request handling)
    │   ├── daos/          Database access objects
    │   ├── models/        POJOs (ContactRequest, Admin)
    │   ├── filters/       AuthFilter (guards /admin/*)
    │   └── utils/         DBConnection, PasswordUtil, HashGenerator
    └── webapp/
        ├── css/
        └── WEB-INF/views/ JSP pages

## Setup

1. **Database** — create a PostgreSQL database named `contactus_database` and run:

```sql
   CREATE TABLE contact_requests (
       id          SERIAL PRIMARY KEY,
       full_name   VARCHAR(100) NOT NULL,
       email       VARCHAR(150) NOT NULL,
       message     TEXT         NOT NULL,
       status      VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE',
       created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
   );

   CREATE TABLE admins (
       id             SERIAL PRIMARY KEY,
       username       VARCHAR(50)  UNIQUE NOT NULL,
       password_hash  VARCHAR(255) NOT NULL,
       created_at     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
   );
```

2. **Seed an admin** — run `HashGenerator` to produce a BCrypt hash for your chosen password, then insert it:

```sql
   INSERT INTO admins (username, password_hash)
   VALUES ('admin', 'PASTE_THE_GENERATED_HASH_HERE');
```

3. **Configure the connection** — set your PostgreSQL username and password in `utils/DBConnection.java`.

4. **Run** — deploy to Tomcat 11 with the context root set to `/`, then open `http://localhost:8080/contactus`.

## Routes

| URL                          | Method | Description                       |
|------------------------------|--------|-----------------------------------|
| `/contactus`                 | GET    | Show the contact form             |
| `/contactus`                 | POST   | Validate and save a submission    |
| `/admin/login`               | GET    | Show the login page               |
| `/admin/contactus/requests`  | GET    | List requests (Active / Archived) |
| `/admin/contactus/archive`   | POST   | Archive or unarchive a request    |
| `/admin/logout`              | GET    | Invalidate the session            |
