package com.imcode.ziya;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-06-01 21:45
 */
public class A implements Serializable {

    @Deprecated
    @Resource(name = "aaa")
    private final static Boolean wuhu = true;
}
