<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Contact Us</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

    <main>
        <section class="card">

            <header>
                <h1>Contact Us</h1>
                <p>Please fill this form in a decent manner</p>
            </header>

            <c:if test="${param.success == '1'}">
                <p class="message success">Thank you! Your message has been sent.</p>
            </c:if>

            <c:if test="${not empty error}">
                <p class="message error">${error}</p>
            </c:if>

            <form action="${pageContext.request.contextPath}/contactus" method="post">

                <label for="fullName">Full Name *</label>
                <input type="text" id="fullName" name="fullName" value="${fullName}">

                <label for="email">E-mail *</label>
                <input type="text" id="email" name="email" value="${email}">
                <small>example@example.com</small>

                <label for="message">Message *</label>
                <textarea id="message" name="message" rows="5">${message}</textarea>

                <button type="submit">SUBMIT</button>

            </form>

        </section>
    </main>

</body>
</html>