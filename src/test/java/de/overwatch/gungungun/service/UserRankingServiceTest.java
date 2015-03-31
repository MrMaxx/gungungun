package de.overwatch.gungungun.service;


import de.overwatch.gungungun.domain.User;
import de.overwatch.gungungun.service.ranking.PagedUserList;
import de.overwatch.gungungun.service.ranking.UserRankingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class UserRankingServiceTest {

    private UserRankingService userRankingService = new UserRankingService();

    private User user1;
    private User user2;
    private User user3;
    private User user4;


    @Before
    public void init(){
        this.userRankingService = new UserRankingService();

        this.user1 = createUser(100);
        this.user2 = createUser(200);
        this.user3 = createUser(300);
        this.user4 = createUser(250);
    }


    private User createUser(int score){
        Random random = new Random();
        long userId = random.nextLong();

        User user = new User();
        user.setId(userId);
        user.setLogin("userName[" + userId + "]");
        user.setScore(score);
        return user;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonExistingChangeScore()throws Exception{
        userRankingService.addUser(user1);
        userRankingService.changeScore(user2.getId(), Integer.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonExistingGetUserPage()throws Exception{
        userRankingService.addUser(user1);
        userRankingService.getUserRanking(user2.getId(),2);
    }

    @Test
    public void testOutOfBoundsPages()throws Exception{
        userRankingService.addUser(user1);

        assertEmptyPagesUserList(-10, 20, userRankingService.getUserPage(-10,20));
        assertEmptyPagesUserList(1, -20, userRankingService.getUserPage(1,-20));
    }

    private void assertEmptyPagesUserList( int page, int pagesize, PagedUserList pagedUserList){
        Assert.assertEquals(page, pagedUserList.getPage());
        Assert.assertEquals(pagesize, pagedUserList.getPageSize());
        Assert.assertEquals(0, pagedUserList.getUsers().size());
    }

    @Test
    public void testRegularBuildup()throws Exception{

        userRankingService.addUser(user1);
        userRankingService.addUser(user2);
        userRankingService.addUser(user3);
        userRankingService.addUser(user4);


        PagedUserList page = userRankingService.getUserPage(1,2);


        Assert.assertEquals(1, page.getPage());
        Assert.assertEquals(2, page.getPageSize());
        Assert.assertEquals(4, page.getTotalSize());

        Assert.assertEquals(2, page.getUsers().size());

        Assert.assertEquals(user3.getId(), page.getUsers().get(0).getId());
        Assert.assertEquals(user4.getId(), page.getUsers().get(1).getId());

    }

    @Test
    public void testUserPage()throws Exception{

        userRankingService.addUser(user1);
        userRankingService.addUser(user2);
        userRankingService.addUser(user3);
        userRankingService.addUser(user4);


        PagedUserList page = userRankingService.getUserRanking(user1.getId(),2);


        Assert.assertEquals(2, page.getPage());
        Assert.assertEquals(2, page.getPageSize());
        Assert.assertEquals(4, page.getTotalSize());

        Assert.assertEquals(2, page.getUsers().size());

        Assert.assertEquals(user2.getId(), page.getUsers().get(0).getId());
        Assert.assertEquals(user1.getId(), page.getUsers().get(1).getId());

    }

}
