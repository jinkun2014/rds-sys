package me.jinkun.rds.sys.web.form;

/**
 * @Description:  <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class BaseForm {
    protected Long page = 1L;
    protected Long rows = 10L;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }
}
