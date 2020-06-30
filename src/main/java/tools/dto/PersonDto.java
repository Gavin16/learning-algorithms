package tools.dto;

import lombok.Data;

/**
 * @ClassName: PersonDto
 * @CopyRight: 百果科技
 * @Description:
 * @Author: wufangmin
 * @Date: 2020/4/24 下午 3:47
 * @Version:
 */
@Data
public class PersonDto {

    private Integer age;

    private String name;

    public PersonDto(String name ,Integer age){
        this.name = name;
        this.age = age;
    }

}
