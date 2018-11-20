package com.min.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName EasyUIDataGridResult.java
 * @Description ok
 * @createTime 2018年11月18日 20:51:00
 */

public class EasyUIDataGridResult<T>  implements Serializable {

    private long total;
    private List<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
