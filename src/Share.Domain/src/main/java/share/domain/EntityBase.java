package share.domain;


import lombok.Data;

import java.util.Date;

/**
 * EntityBase
 * Created by 404 on 2016/7/5.
 */
@Data
public class EntityBase
{
    private String addUser;
    private String modifyUser;
    private Date create_time;
    private Date update_time;

    /**
     * Instantiates a new Entity base.
     */
    public EntityBase() {
        this.create_time = new Date();
        this.update_time = new Date();
    }

    /**
     * @param addUser
     * @param modifyUser
     * @param create_time
     * @param update_time
     */
    public EntityBase(String addUser, String modifyUser, Date create_time, Date update_time) {
        this.addUser = addUser;
        this.modifyUser = modifyUser;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
        if (this.create_time == null)
            this.create_time = new Date();
    }

    public void setUpdate_time(Date update_time) {
        if (update_time != null)
            this.update_time = update_time;
    }
}
