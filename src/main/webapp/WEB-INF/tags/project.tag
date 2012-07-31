<%@ attribute name="project" required="true" %>
<section>
    <h2><c:out value="${project.content}"/></h2>
    <c:forEach var="item" items="${items}">
        <t:renderItem item="${item}"/>
    </c:forEach>
</section>