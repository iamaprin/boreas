package io.vilya.boreas;

import java.io.Serializable;
import java.lang.reflect.Field;
import javax.validation.constraints.NotNull;

/**
 * @author iamaprin
 * @time 2017年6月18日 下午9:23:32
 */
@SuppressWarnings("serial")
public abstract class BaseBean implements Serializable {
    
    public void verify() {
        Class<? extends BaseBean> clazz = 
                this.getClass();
        
        Field[] fields = clazz.getDeclaredFields();
        Object value;
        boolean accessible;
        for (Field field : fields) {
            if (!field.isAnnotationPresent(NotNull.class)) {
                continue;
            }
            
            accessible = field.isAccessible();
            if (!accessible) {
                field.setAccessible(true);
            }
            
            try {
                value = field.get(this);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new BoreasException(e);
            }
            
            field.setAccessible(accessible);
            
            // this field can not be null
            if (value == null) {
                throw new FieldVerifyFailedException();
            }
        }
    }
    
}
