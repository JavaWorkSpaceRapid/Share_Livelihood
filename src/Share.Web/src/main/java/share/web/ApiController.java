package share.web;

import share.base.result.LiveResult;

public class ApiController
{
    public <E> LiveResult<E> ok(E src) {
        return new LiveResult<E>(1, null, src);
    }

    public LiveResult<Boolean> ok() {
        return new LiveResult<Boolean>(1, null, true);
    }
}
