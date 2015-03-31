package de.overwatch.gungungun.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.overwatch.gungungun.domain.User;
import de.overwatch.gungungun.repository.UserRepository;
import de.overwatch.gungungun.security.AuthoritiesConstants;
import de.overwatch.gungungun.service.ranking.PagedUserList;
import de.overwatch.gungungun.service.ranking.UserRankingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserRankingService userRankingService;

    /**
     * GET  /users -> get all users.
     */
    @RequestMapping(value = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public @ResponseBody PagedUserList getAll(
            @RequestParam(required = false) Integer pagesize,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Long userId) {

        if(page == null){ page = 1; }
        if(pagesize == null){ pagesize = 30; }


        if(userId != null){
            return userRankingService.getUserRanking(userId, pagesize);
        }

        return userRankingService.getUserPage(page, pagesize);
    }

    /**
     * GET  /users/:login -> get the "login" user.
     */
    @RequestMapping(value = "/users/{login}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public User getUser(@PathVariable String login, HttpServletResponse response) {
        log.debug("REST request to get User : {}", login);
        User user = userRepository.findOneByLogin(login);
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return user;
    }
}
