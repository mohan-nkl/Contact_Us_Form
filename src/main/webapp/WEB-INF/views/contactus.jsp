<%-- ===== PAGE DIRECTIVE: this page outputs HTML as UTF-8 text ===== --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- ===== TAGLIB DIRECTIVE: import JSTL core tags as "c" so we can use
         <c:if>. "jakarta.tags.core" is the URI Tomcat 11 needs. ===== --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Contact Us</title>

    <!-- Link our stylesheet. contextPath = the app's base URL path. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

    <!-- <main>: the primary content of the page (one per page). -->
    <main>

        <!-- <section>: one self-contained block — the whole form box. -->
        <section class="card">

            <!-- <header>: the title area of this section. -->
            <header>
                <h1>Contact Us</h1>
                <p>Please fill this form in a decent manner</p>
            </header>

            <%-- Show the success line ONLY when the URL has ?success=1
                 (set after a successful save + redirect). --%>
            <c:if test="${param.success == '1'}">
                <!-- Green success message -->
                <p class="message success">Thank you! Your message has been sent.</p>
            </c:if>

            <%-- Show the error line ONLY when the servlet set an "error"
                 attribute (validation failed). ${error} prints its text. --%>
            <c:if test="${not empty error}">
                <!-- Red error message -->
                <p class="message error">${error}</p>
            </c:if>

            <!-- The form. action = where data goes; method=post = submitting data. -->
            <form action="${pageContext.request.contextPath}/contactus" method="post">

                <!-- "name" is the key the servlet reads via getParameter("fullName").
                     value="${fullName}" re-fills the box after a validation error. -->
                <label for="fullName">Full Name *</label>
                <input type="text" id="fullName" name="fullName" value="${fullName}">

                <!-- Email field -->
                <label for="email">E-mail *</label>
                <input type="text" id="email" name="email" value="${email}">
                <small>example@example.com</small>

                <!-- Message. <textarea> = a multi-line text box. -->
                <label for="message">Message *</label>
                <textarea id="message" name="message" rows="5">${message}</textarea>

                <!-- Submit button: sends the form to the action URL above. -->
                <button type="submit">SUBMIT</button>

            </form>

        </section>
    </main>

</body>
</html>