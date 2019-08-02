package me.veir.oauth.common.page;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Veir
 * @Despriction: 对前端表格显示数据的封装
 * @Date: Created at 2018/11/15 10:06
 * @Modify by:
 */
public class PageBean<T> {
    private Map<String, String> op;
    private List<Column> columns;//表格列
    private long total;//总数据条数
    private long totalPage;//总页数
    private List<T> data; //主要数据

    public PageBean(Map<String, String> op, List<Column> columns, long total, long totalPage, List<T> data) {
        this.op = op;
        this.columns = columns;
        this.total = total;
        this.totalPage = totalPage;
        this.data = data;
    }

    public PageBean() {
    }

    public static <T> PageBean.PageBeanBuilder<T> builder() {
        return new PageBean.PageBeanBuilder();
    }

    public Map<String, String> getOp() {
        return this.op;
    }

    public List<Column> getColumns() {
        return this.columns;
    }

    public long getTotal() {
        return this.total;
    }

    public long getTotalPage() {
        return this.totalPage;
    }

    public List<T> getData() {
        return this.data;
    }

    public PageBean<T> setOp(Map<String, String> op) {
        this.op = op;
        return this;
    }

    public PageBean<T> setColumns(List<Column> columns) {
        this.columns = columns;
        return this;
    }

    public PageBean<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public PageBean<T> setTotalPage(long totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public PageBean<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public String toString() {
        return "PageBean(op=" + this.getOp() + ", columns=" + this.getColumns() + ", total=" + this.getTotal() + ", totalPage=" + this.getTotalPage() + ", data=" + this.getData() + ")";
    }

    public static class PageBeanBuilder<T> {
        private Map<String, String> op;
        private List<Column> columns;
        private long total;
        private long totalPage;
        private List<T> data;

        PageBeanBuilder() {
        }

        public PageBean.PageBeanBuilder<T> op(Map<String, String> op) {
            this.op = op;
            return this;
        }

        /**
         * 新增op
         * @param key
         * @param value
         * @return
         */
        public PageBean.PageBeanBuilder<T> op(String key, String value) {
            if (this.op == null) {
                this.op = new HashMap<>();
            }
            this.op.put(key, value);
            return this;
        }

        public PageBean.PageBeanBuilder<T> columns(List<Column> columns) {
            this.columns = columns;
            return this;
        }

        /**
         * 新增column， 传入PageBeanColumnInterface的实例
         * @param columnInterface
         * @return
         */
        public PageBean.PageBeanBuilder<T> columns(PageBeanColumnInterface columnInterface) {
            if (this.columns == null) {
                this.columns = new LinkedList<>();
            }
            this.columns.add(new Column()
                    .setKey(columnInterface.getKey())
                    .setTitle(columnInterface.getTitle()));
            return this;
        }

        public PageBean.PageBeanBuilder<T> total(long total) {
            this.total = total;
            return this;
        }

        public PageBean.PageBeanBuilder<T> totalPage(long totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public PageBean.PageBeanBuilder<T> data(List<T> data) {
            this.data = data;
            return this;
        }

        public PageBean<T> build() {
            return new PageBean(this.op, this.columns, this.total, this.totalPage, this.data);
        }

        public String toString() {
            return "PageBean.PageBeanBuilder(op=" + this.op + ", columns=" + this.columns + ", total=" + this.total + ", totalPage=" + this.totalPage + ", data=" + this.data + ")";
        }
    }

    public static class Column {
        private String title;
        private String key;

        public Column() {
        }

        public Column(String title, String key) {
            this.title = title;
            this.key = key;
        }


        public String getTitle() {
            return this.title;
        }

        public String getKey() {
            return this.key;
        }

        public PageBean.Column setTitle(String title) {
            this.title = title;
            return this;
        }

        public PageBean.Column setKey(String key) {
            this.key = key;
            return this;
        }

        @Override
        public String toString() {
            return "Column{" +
                    "title='" + title + '\'' +
                    ", key='" + key + '\'' +
                    '}';
        }
    }
}
