package de.overwatch.gungungun.service.ranking;


import java.util.List;

public class PagedUserList {


    private int page;
    private int pageSize;
    private int totalSize;

    private List<UserWithRank> users;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<UserWithRank> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithRank> users) {
        this.users = users;
    }
}
