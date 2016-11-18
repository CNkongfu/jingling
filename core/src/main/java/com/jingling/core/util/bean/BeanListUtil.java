package com.jingling.core.util.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实体类List与业务类List相互转换工具类
 * 
 * @author CNkongfu
 * @date 2016年11月17日
 */
public class BeanListUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanListUtil.class);

    /**
     * 
     * @param tList 目标List
     * @param d 需要转换成的目标类类型
     * @return
     */
    public static <T, D> List<D> getList(List<T> tList, Class<D> d) {
        try {
            if (tList != null && tList.size() > 0) {
                List<D> dList = new ArrayList<D>();
                for (T t : tList) {
                    D dObject = BeanUtil.getBean(t, d);
                    dList.add(dObject);
                }
                return dList;
            }
        } catch (Exception e) {
            logger.error("BeanListUtil parser error!" + e.getMessage());
        }
        return Collections.emptyList();
    }
}
