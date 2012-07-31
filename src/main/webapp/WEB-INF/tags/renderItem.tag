<%@ attribute name="item" required="true" %>
<c:choose>
    <c:when test="${item.type == "PROJECT"}">
        <t:project project="${item}"/>
    </c:when>
    <c:when test="${item.type == "TASK"}">
        <t:task task="${item}"/>
    </c:when>
    <c:when test="${item.type == "NOTE"}">
        <t:note note="${item}"/>
    </c:when>
</c:choose>