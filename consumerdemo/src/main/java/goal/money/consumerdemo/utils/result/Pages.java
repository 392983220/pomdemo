package goal.money.consumerdemo.utils.result;

import goal.money.providerdemo.dto.ProductInfo;

import java.io.Serializable;
import java.util.List;


public class Pages<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int PAGESIZE = 20;

    private int pagesize = 20;

    private int currentPage = 1;// 当前页

    private int priviousPage = 0;// 上一页

    private int nextPage;// 下一页

    private long totalCount = 0L;// 总条数

    private long totalPage = 0L;// 总页数

    private Integer customPageSize;

    private List<T> currList;

    private ProductInfo productInfo;

    public ProductInfo getProductInfo() {
        return productInfo;
    }
    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }


    public Integer getCustomPageSize() {
        return customPageSize;
    }

    public void setCustomPageSize(Integer customPageSize) {
        this.customPageSize = customPageSize;
    }

    public void setCurrList(List<T> currList) {
        this.currList = currList;
    }

    /**
     * 获取 limit sql 文
     *
     * @return
     */
    public String getPagingSql() {
        int pz;
        if (null == customPageSize) {
            pz = PAGESIZE;
        } else {
            pz = customPageSize;
        }
        currentPage = (currentPage < 1 ? 1 : currentPage);
        StringBuilder sb = new StringBuilder();
        sb.append(" LIMIT");
        sb.append(" " + (currentPage - 1) * pz);
        sb.append("," + pz);
        return sb.toString();
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<T> getCurrList() {
        return currList;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPriviousPage() {
        return priviousPage;
    }

    public void setPriviousPage(int priviousPage) {
        this.priviousPage = priviousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        int pz;
        if (null == customPageSize) {
            pz = PAGESIZE;
        } else {
            pz = customPageSize;
        }
        this.totalCount = totalCount;
        if (totalCount > 0) {
            this.totalPage = this.totalCount % pz == 0 ? this.totalCount
                    / pz : (this.totalCount / pz) + 1;
        } else {
            this.totalPage = 1;
        }
        this.priviousPage = this.currentPage - 1;
        this.nextPage = this.currentPage + 1;
    }

    public long getTotalPage() {
        if (totalCount % pagesize == 0) {
        	totalPage = totalCount / pagesize;
        } else {
        	totalPage = totalCount / pagesize + 1;
        }
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public int getOffset() {
        int pz;
        if (null == customPageSize) {
            pz = PAGESIZE;
        } else {
            pz = customPageSize;
        }
        if (this.currentPage < 1)
            this.currentPage = 1;
        return (this.currentPage - 1) * pz;
    }

    public int getOffset2() {
        int pz;
        if (null == customPageSize) {
            pz = pagesize;
        } else {
            pz = customPageSize;
        }
        if (this.currentPage < 1)
            this.currentPage = 1;
        return (this.currentPage - 1) * pz;
    }
}
