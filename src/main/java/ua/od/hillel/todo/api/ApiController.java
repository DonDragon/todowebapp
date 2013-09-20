package ua.od.hillel.todo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.od.hillel.todo.dao.TODODao;
import ua.od.hillel.todo.entities.TODOEntry;
import ua.od.hillel.todo.entities.TODOList;
import ua.od.hillel.todo.vo.ListVo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * REST API Endpoints
 */
@Transactional
@Component
@Path("/")
public class ApiController {

    @Autowired
    private TODODao dao;

    @GET
    @Path("/lists")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ListVo> lists() {
        List<ListVo> ls = new ArrayList<ListVo>();
        for (TODOList list : dao.findTODOLists()) {
            ls.add( ListVo.from(list));
        }
        return ls;
    }


}

