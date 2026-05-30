<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Contact Requests</title>
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

    <main class="admin">

        <header class="admin-top">
            <h1>Contact Requests</h1>
            <a class="logout" href="${pageContext.request.contextPath}/admin/logout">Logout</a>
        </header>

        <h2>Active (${activeRequests.size()})</h2>

        <c:choose>
            <c:when test="${empty activeRequests}">
                <p>No active requests.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Message</th>
                            <th>Received</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="req" items="${activeRequests}">
                            <tr>
                                <td><c:out value="${req.fullName}"/></td>
                                <td><c:out value="${req.email}"/></td>
                                <td><c:out value="${req.message}"/></td>
                                <td>${req.createdAt}</td>
                                <td>
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

        <h2>Archived (${archivedRequests.size()})</h2>

        <c:choose>
            <c:when test="${empty archivedRequests}">
                <p>No archived requests.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Message</th>
                            <th>Received</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="req" items="${archivedRequests}">
                            <tr class="archived">
                                <td><c:out value="${req.fullName}"/></td>
                                <td><c:out value="${req.email}"/></td>
                                <td><c:out value="${req.message}"/></td>
                                <td>${req.createdAt}</td>
                                <td>
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