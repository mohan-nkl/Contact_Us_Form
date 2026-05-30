<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script>
        window.addEventListener('pageshow', function (event) {
            if (event.persisted) {
                window.location.reload();
            }
        });
    </script>
</head>

<body>

    <main>
        <section class="card">

            <header>
                <h1>Admin Login</h1>
                <p>Sign in to manage contact requests</p>
            </header>

            <c:if test="${not empty error}">
                <p class="message error">${error}</p>
            </c:if>

            <form action="${pageContext.request.contextPath}/admin/login" method="post">

                <label for="username">Username *</label>
                <input type="text" id="username" name="username">

                <label for="password">Password *</label>
                <input type="password" id="password" name="password">

                <button type="submit">LOGIN</button>

            </form>

        </section>
    </main>

</body>
</html>