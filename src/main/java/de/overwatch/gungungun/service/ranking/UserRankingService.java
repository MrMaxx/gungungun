package de.overwatch.gungungun.service.ranking;


import de.overwatch.gungungun.domain.User;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserRankingService {

    private ConcurrentHashMap<Integer, UserWithRank> ranking = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, UserWithRank> userIdToUserWithScoreMap = new ConcurrentHashMap<>();


    public synchronized void addUser(User user) {
        UserWithRank userWithRank = new UserWithRank(user.getId(), user.getLogin(), 0, ranking.size()+1);

        ranking.put(ranking.size()+1, userWithRank);
        userIdToUserWithScoreMap.put(userWithRank.getId(), userWithRank);

        changeScore(userWithRank.getId(), user.getScore());
    }

    public UserWithRank getUserWithRank(Integer userId){
        return userIdToUserWithScoreMap.get(userId);
    }

    public synchronized void changeScore(Long userId, int newScore) {

        UserWithRank userWithRank = userIdToUserWithScoreMap.get(userId);
        if(userWithRank==null){ throw new IllegalArgumentException("There is no User with id = "+userId+" in the ranking.");}

        userWithRank.setScore(newScore);

        if(userWithRank.getRank().equals(Integer.valueOf(1))){ return; }

        Integer rank = userWithRank.getRank()-1;
        while(rank.intValue() >= 1){
            UserWithRank nextUser = ranking.get(rank);
            if(nextUser.getScore() < userWithRank.getScore() ){
                // move down by one
                nextUser.setRank(rank+1);
                ranking.put(rank+1, nextUser);
            }else{
                break;
            }

            rank --;
        }
        userWithRank.setRank(rank+1);
        ranking.put(rank+1, userWithRank);
    }

    public PagedUserList getUserPage(int page, int pagesize) {

        PagedUserList userList = new PagedUserList();
        userList.setPage(page);
        userList.setPageSize(pagesize);
        userList.setTotalSize(ranking.size());

        if( page < 1 || pagesize < 1 || (((page-1) * pagesize)+1) > ranking.size()){

            userList.setUsers(new LinkedList<UserWithRank>());
            return userList;
        }

        userList.setUsers(getPage((((page-1) * pagesize)+1), page*pagesize));

        return userList;
    }

    private List<UserWithRank> getPage( int start, int end){
        List<UserWithRank> result = new LinkedList<UserWithRank>();
        for(int i = start; i <= end; i++){
            UserWithRank userWithRank = ranking.get(i);
            if(userWithRank != null){
                result.add(userWithRank);
            }
        }
        return result;
    }

    public PagedUserList getUserRanking(Long userId, int pagesize) {

        UserWithRank userWithRank = userIdToUserWithScoreMap.get(userId);
        if(userWithRank==null){ throw new IllegalArgumentException("There is no User with id = "+userId+" in the ranking.");}

        int rank = userWithRank.getRank();
        int page = ((int)(rank/pagesize))+1;
        if( rank%pagesize == 0 ){
            page = (int)(rank/pagesize);
        }

        return getUserPage(page, pagesize);
    }
}
