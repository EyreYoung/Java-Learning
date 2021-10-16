package com.ke.page;

import com.ke.serializable.Person;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "Pagination")
public class Pagination<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> pageList;

    private int pageSize;

    private int pageNo;

    private int totalCount;

    public Pagination() {
        super();
    }

    public Pagination(int pageSize, int pageNo) {
        pageSize = pageSize > 0 ? pageSize : 20;
        pageNo = pageNo > 0 ? pageNo : 1;

        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(final List<T> pageList) {
        this.pageList = pageList;
        this.totalCount = pageList.size();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        pageSize = pageSize > 0 ? pageSize : 20;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        pageNo = pageNo > 0 ? pageNo : 1;
        this.pageNo = pageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(final int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        if (totalCount == 0) return 1;
        if (totalCount % pageSize == 0) return totalCount / pageSize;
        return totalCount / pageSize + 1;
    }

    /*
    分页的起始行
     */
    public int getRowOffset() {
        return (pageNo - 1) * pageSize + 1;
    }

    public boolean isFirstPage() {
        if (getTotalPage() <= 0) return true;
        return getPageNo() <= 1;
    }

    public boolean isLastPage() {
        if (getTotalPage() <= 0) return true;
        return getPageNo() >= getTotalPage();
    }

    /*
    下一页页数
     */
    public int getNextPage(){
        if (isLastPage()) return getTotalPage();
        return getPageNo() + 1;
    }

    /*
    上一页页数
     */
    public int getPrePage() {
        if (isFirstPage()) return 1;
        return getPageNo() - 1;
    }

    public static void main(final String[] args) {
        final Pagination<Person> persons = new Pagination<>();
        final List<Person> ps = new ArrayList<>();
        ps.add(new Person("yyd", 24, "淮安"));
        ps.add(new Person("lll", 24, "南京"));
        ps.add(new Person("ccc", 24, "重庆"));
        ps.add(new Person("qqq", 24, "xian"));
        ps.add(new Person("www", 24, "chengdu"));
        ps.add(new Person("eee", 24, "shenzhen"));
        ps.add(new Person("r", 24, "上海"));
        ps.add(new Person("tt", 24, "上海"));
        ps.add(new Person("uuuu", 24, "hongkong"));
        ps.add(new Person("i", 24, "江苏"));
        ps.add(new Person("oo", 24, "上海"));
        ps.add(new Person("gg", 24, "北京"));

        persons.setPageList(ps);
        persons.setPageSize(4);
        persons.setPageNo(4);

        System.out.println(persons.getRowOffset());
        
    }

}
