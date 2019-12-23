package share.livelihood.bussiness.dto.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import share.dto.EntityDto;

@Data
@ApiModel(value = "FileInfoDto", description = "FileInfoDto")
public class FileInfoDto extends EntityDto
{
    @ApiModelProperty(value = "文件Id")
    private String id;
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    @ApiModelProperty(value = "文件大小")
    private Long fileSize;
    @ApiModelProperty(value = "file")
    private String fieldName;
    @ApiModelProperty(value = "内容类型")
    private String contentType;
}
