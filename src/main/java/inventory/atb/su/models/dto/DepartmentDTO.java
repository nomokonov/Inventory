package inventory.atb.su.models.dto;

public class DepartmentDTO {
    private String codeDepartment;
    private String nameDepartment;

    public DepartmentDTO(String codeDepartment, String nameDepartment) {
        this.codeDepartment = codeDepartment;
        this.nameDepartment = nameDepartment;
    }

    public String getCodeDepartment() {
        return codeDepartment;
    }

    public void setCodeDepartment(String codeDepartment) {
        this.codeDepartment = codeDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }
}
