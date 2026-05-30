<%-- ===== PAGE DIRECTIVE: outputs HTML as UTF-8 text ===== --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- ===== TAGLIB: import JSTL core tags as "c" (for <c:if>) ===== --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
    <!-- Reuse the same stylesheet as the contact form -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <%-- ===== SECURITY: defeat the browser's back-forward cache =====
         If the browser restores this page from memory on Back/Forward,
         event.persisted is true, so we reload to get a fresh page. --%>
    <script>
        window.addEventListener('pageshow', function (event) {
            if (event.persisted) {
                window.location.reload();
            }
        });
    </script>
</head>

<body>

    <!-- <main>: the page's primary content -->
    <main>

        <!-- <section>: the login box (reuses the .card styling) -->
        <section class="card">

            <!-- <header>: the title area -->
            <header>
                <h1>Admin Login</h1>
                <p>Sign in to manage contact requests</p>
            </header>

            <%-- Show an error ONLY when the servlet set an "error" attribute
                 (i.e. the login failed). ${error} prints its text. --%>
            <c:if test="${not empty error}">
                <!-- Red error message -->
                <p class="message error">${error}</p>
            </c:if>

            <!-- The login form posts to /admin/login (the LoginServlet) -->
            <form action="${pageContext.request.contextPath}/admin/login" method="post">

                <!-- Username. "name" is read by getParameter("username"). -->
                <label for="username">Username *</label>
                <input type="text" id="username" name="username">

                <!-- Password. type="password" hides the characters as dots. -->
                <label for="password">Password *</label>
                <input type="password" id="password" name="password">

                <!-- Submit button -->
                <button type="submit">LOGIN</button>

            </form>

        </section>
    </main>

</body>
</html>