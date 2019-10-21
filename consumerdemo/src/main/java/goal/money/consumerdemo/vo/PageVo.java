package goal.money.consumerdemo.vo;

import java.io.Serializable;

public class PageVo implements Serializable {
    private int startPage;
    private int  pageSize;


    public int getStartPage() {
        int i=1;
        if (startPage>=i){
            this.startPage = (startPage-1)*pageSize;
        }else {
            this.startPage=0;
        }
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
