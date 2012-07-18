package com.threeheadedmonkey.teepee;

import com.threeheadedmonkey.teepee.entity.Item;
import com.threeheadedmonkey.teepee.respository.InMemoryRepository;
import com.threeheadedmonkey.teepee.respository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

/**
 * Servlet for reading TaskPaper files and displaying them
 */
@WebServlet("/")
public class TaskPaperServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(TaskPaperServlet.class);

    @Inject
    private ItemRepository itemRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (itemRepository == null) itemRepository = new InMemoryRepository();

        log.info("Getting TaskPaper");

        String key = request.getParameter("key");
        if (key != null && !key.trim().isEmpty()) {
            log.info("Getting for key: " + key);

            // Check the repository for the key and show file if it exists
            Collection<Item> items = itemRepository.get(key.trim());
            if (items != null && !items.isEmpty()) {
                request.setAttribute("items", items);
                getServletContext().getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(request, response);
            }
        } else {

            log.info("No key provided");
            getServletContext().getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("Posting new TaskPaper");

        String key = UUID.randomUUID().toString();
        response.sendRedirect("/?key=" + key);
    }
}
