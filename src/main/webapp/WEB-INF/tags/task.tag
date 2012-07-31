<%@ attribute name="task" required="true" %>
<div class="task">
    <c:if test="${item.hasItems()">
        <p <c:if test="task.done"> class="done"</c:if>>
            <c:out value="${task.content}"/>
        </p>
        <c:forEach var="childItem" items="${task.items}">
            <t:renderItem item="${childItem}"/>
        </c:forEach>
    </c:if>
</div>