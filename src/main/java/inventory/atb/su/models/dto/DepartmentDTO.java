package inventory.atb.su.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDTO {
    private String codeDepartment;
    private String nameDepartment;

    public DepartmentDTO(String codeDepartment, String nameDepartment) {
        this.codeDepartment = codeDepartment;
        this.nameDepartment = nameDepartment;
    }
}
