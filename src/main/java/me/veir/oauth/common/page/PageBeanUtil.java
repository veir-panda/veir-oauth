package me.veir.oauth.common.page;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Veir, veir.xw@gmail.com
 * @create 2019/3/26 10:31
 */
public class PageBeanUtil {
    /**
     * 计算数据库分页查询的第一条数据下标
     * @param page 当前页
     * @param pageSize 页数大小
     * @return
     */
    public static int getStartIndex(int page, int pageSize){
        if (page < 1 || pageSize < 1){
            throw new IllegalArgumentException(MessageFormat.format("无效的页码：page={0}, pageSize={1}", page, pageSize));
        }
        return (page-1) * pageSize;
    }
    /**
     * 生成前端表格显示所需的数据
     * @param data 主要数据
     * @param op 操作
     * @param total 数据总条数
     * @param pageSize 当前页显示数据大小
     * @param enumData 表格列枚举类
     * @param <E> 枚举类，且实现PageBeanColumnInterface接口
     * @return
     */
    public static <E extends Enum<E>> PageBean generateGridData(List data, Map<String, String> op, long total, int pageSize, Class<E> enumData){
        long totalPage = 0;
        if (pageSize > 0){
            totalPage = (total + pageSize - 1) / pageSize;//总页数
        }
        PageBean.PageBeanBuilder builder = PageBean.builder();
        builder = builder
                .total(total)
                .totalPage(totalPage)
                .data(data)
                .op(op);
        for (Enum<E> enumVal: enumData.getEnumConstants()) {
            if (enumVal instanceof PageBeanColumnInterface){
                builder.columns((PageBeanColumnInterface)enumVal);
            }else {
                throw new IllegalStateException(enumData.getSimpleName() + " 不是 " + PageBeanColumnInterface.class.getSimpleName() + "的实现类");
            }
        }
        return builder.build();
    }

    /**
     * 生成前端表格显示所需的数据
     * @param data
     * @param op
     * @param total
     * @param pageSize
     * @param enumData
     * @return
     */
    public static PageBean generateGridData(List data, Map<String, String> op, long total, int pageSize, List<PageBeanColumnInterface> enumData){
        long totalPage = 0;
        if (pageSize > 0){
            totalPage = (total + pageSize - 1) / pageSize;//总页数
        }
        PageBean.PageBeanBuilder builder = PageBean.builder();
        builder = builder
                .total(total)
                .totalPage(totalPage)
                .data(data)
                .op(op);
        for (PageBeanColumnInterface pageBeanColumn: enumData) {
            builder.columns(pageBeanColumn);
        }
        return builder.build();
    }

    /**
     * 与generateGridData方法功能一样，其中最后一个参数是表格的列
     * @param data
     * @param op
     * @param total
     * @param pageSize
     * @param columnList
     * @return
     */
    public static PageBean generateGridDataForNoColumn(List<List<String>> data, Map<String, String> op, int total, int pageSize, List<String> columnList){

        int totalPage = 0;
        if (pageSize > 0){
            totalPage = (total + pageSize - 1) / pageSize;//总页数
        }
        PageBean.PageBeanBuilder builder = PageBean.builder();
        builder = builder
                .total(total)
                .totalPage(totalPage)
                .data(listToMap(data, columnList))
                .op(op);
        List<PageBean.Column> columns = columnList.stream().map(col -> new PageBean.Column(col, col)).collect(Collectors.toList());
        builder.columns(columns);
        return builder.build();
    }

    /**
     * 生成带有行号的表格数据
     * @param data
     * @param op
     * @param total
     * @param pageSize
     * @param columnList
     * @return
     */
    public static PageBean generateGridDataWithLineNum(List<List<String>> data, Map<String, String> op,
                                                       int page, int total, int pageSize, List<String> columnList){
        /*String lineNumColumnName = "$hccs$_lineNum";//行号的表头名字
        int start = getStartIndex(page, pageSize) + 1;//行号从1开始
        columnList.add(0, ProfileSummaryColumnUtil.getSummaryColumnName(columnList, lineNumColumnName));
        for (List<String> line : data) {
            line.add(0, String.valueOf(start++));
        }*/

        int totalPage = 0;
        if (pageSize > 0){
            totalPage = (total + pageSize - 1) / pageSize;//总页数
        }
        PageBean.PageBeanBuilder builder = PageBean.builder();
        builder = builder
                .total(total)
                .totalPage(totalPage)
                .data(listToMap(data, columnList))
                .op(op);
        List<PageBean.Column> columns = columnList.stream().map(col -> new PageBean.Column(col, col)).collect(Collectors.toList());
        builder.columns(columns);
        return builder.build();
    }

    public static List<Map> listToMap(List<List<String>> data, List<String> columns){
        return data.stream()
                .map(row -> rowToMap(row, columns))
                .collect(Collectors.toList());
    }

    private static Map<String, String> rowToMap(List<String> row, List<String> columns) {
        Map<String, String> entry = new HashMap<>();
        for (int i = 0; i < columns.size(); i++) {
            entry.put(columns.get(i), row.get(i));
        }
        return entry;
    }
}
