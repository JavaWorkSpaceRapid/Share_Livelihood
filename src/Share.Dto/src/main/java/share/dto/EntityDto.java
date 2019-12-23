package share.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EntityDto
{
    private String addUser;
    private String modifyUser;
    private Date create_time;
    private Date update_time;
}
