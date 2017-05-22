package me.jinkun.rds.sys.web.result;

import java.util.List;

public class EUDataGridResult extends BaseResult {

    private long total;
    private List<?> rows;

    public EUDataGridResult() {
    }

    public EUDataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}