package share.base.exceptions;


import share.base.extensions.EnumExtension;
import share.base.extensions.StringExtension;
import share.base.result.RtnMessage;

public class BaseException extends RuntimeException {
    private int code;
    private String msg;

    /** @deprecated */
    @Deprecated
    public BaseException(Enum e) {
        Object code = EnumExtension.getDescription(e, "code", Description.class);
        String msg = null;
        if (null == msg) {
            msg = StringExtension.asString(EnumExtension.getDescription(e, "description", Description.class));
        }

        this.msg = msg;
        this.code = (Integer)code;
    }

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BaseException(Exception e) {
        super(e.getMessage());
        this.msg = e.getMessage();
    }

    public BaseException(String msg, int code) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.msg;
    }

    public RtnMessage asMessage() {
        return new RtnMessage(this.getMessage(), this.code);
    }
}
