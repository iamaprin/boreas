package io.vilya.boreas.activemq;

import javax.validation.constraints.NotNull;

import io.vilya.boreas.BaseBean;

/**
 * @author iamaprin
 * @time 2017年6月18日 下午11:10:51
 */
@SuppressWarnings("serial")
public class TestBean extends BaseBean {
    
    @NotNull
    private String a;
    
}
