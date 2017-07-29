package io.vilya.boreas.exception;

/**
 * @author iamaprin
 * @time 2017年6月18日 下午8:44:27
 */
@SuppressWarnings("serial")
public class BoreasException extends RuntimeException {
    
    public BoreasException() {
        super();
    }
    
    public BoreasException(String message) {
        super(message);
    }
    
    public BoreasException(Throwable e) {
        super(e);
    }
    
}
