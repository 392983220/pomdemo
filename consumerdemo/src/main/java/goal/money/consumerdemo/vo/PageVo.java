package goal.money.consumerdemo.vo;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

public class PageVo implements Serializable {
    @ApiModelProperty("起始页")
    private int startPage;
    @ApiModelProperty("每页多少条")
    private int  pageSize;


    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        int i=1;
        if (startPage>=i){
            this.startPage = (startPage-1)*pageSize;
        }else {
            this.startPage=0;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


}
