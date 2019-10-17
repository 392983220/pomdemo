package goal.money.consumerdemo.vo;

import java.io.Serializable;

public class PageVo implements Serializable {
    private int startPage;
    private int  pageSize;


    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


}
