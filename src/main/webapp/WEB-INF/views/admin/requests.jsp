<%-- ===== PAGE DIRECTIVE: outputs HTML as UTF-8 text ===== --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- ===== TAGLIB: import JSTL core tags as "c" (for <c:if>, <c:choose>, <c:forEach>) ===== --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Contact Requests</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <%-- ===== SECURITY: defeat the browser's back-forward cache =====
         After logout, Safari may restore this page from memory on Back,
         without asking the server. event.persisted is true only then, so we
         force a reload, which hits AuthFilter, finds no session, redirects. --%>
    <script>
        window.addEventListener('pageshow', function (event) {
            if (event.persisted) {
                window.location.reload();
            }
        });
    </script>
</head>

<body>

    <!-- <main>: the page's primary content (wider here for the tables) -->
    <main class="admin">

        <!-- <header>: title on the left, logout link on the right -->
        <header class="admin-top">
            <h1>Contact Requests</h1>
            <a class="logout" href="${pageContext.request.contextPath}/admin/logout">Logout</a>
        </header>

        <%-- ===================== ACTIVE GROUP ===================== --%>
        <!-- ${activeRequests.size()} prints how many items are in the list -->
        <h2>Active (${activeRequests.size()})</h2>

        <%-- <c:choose> is an if/else: show a message if empty, else the table --%>
        <c:choose>
            <c:when test="${empty activeRequests}">
                <p>No active requests.</p>
            </c:when>
            <c:otherwise>
                <!-- <table> is the semantic tag for tabular data -->
                <table>
                    <!-- <thead> = the header row -->
                    <thead>
                        <tr>
                            <th>Name</th><th>Email</th><th>Message</th><th>Received</th><th></th>
                        </tr>
                    </thead>
                    <!-- <tbody> = the data rows -->
                    <tbody>
                        <%-- <c:forEach> loops over the list; each item is "req" --%>
                        <c:forEach var="req" items="${activeRequests}">
                            <tr>
                                <%-- <c:out> prints text but ESCAPES HTML, blocking XSS --%>
                                <td><c:out value="${req.fullName}"/></td>
                                <td><c:out value="${req.email}"/></td>
                                <td><c:out value="${req.message}"/></td>
                                <td>${req.createdAt}</td>
                                <td>
                                    <!-- Tiny form: archive THIS row by its id -->
                                    <form action="${pageContext.request.contextPath}/admin/contactus/archive" method="post">
                                        <input type="hidden" name="id" value="${req.requestId}">
                                        <input type="hidden" name="action" value="archive">
                                        <button type="submit">Archive</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <%-- ===================== ARCHIVED GROUP ===================== --%>
        <h2>Archived (${archivedRequests.size()})</h2>

        <c:choose>
            <c:when test="${empty archivedRequests}">
                <p>No archived requests.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th><th>Email</th><th>Message</th><th>Received</th><th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="req" items="${archivedRequests}">
                            <!-- class="archived" greys the row out via CSS -->
                            <tr class="archived">
                                <td><c:out value="${req.fullName}"/></td>
                                <td><c:out value="${req.email}"/></td>
                                <td><c:out value="${req.message}"/></td>
                                <td>${req.createdAt}</td>
                                <td>
                                    <!-- Same form, but action=unarchive sends it back to Active -->
                                    <form action="${pageContext.request.contextPath}/admin/contactus/archive" method="post">
                                        <input type="hidden" name="id" value="${req.requestId}">
                                        <input type="hidden" name="action" value="unarchive">
                                        <button type="submit">Unarchive</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

    </main>

</body>
</html>