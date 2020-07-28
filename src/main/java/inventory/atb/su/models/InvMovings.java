package inventory.atb.su.models;

import inventory.atb.su.models.dto.DepartmentDTO;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movings")
public class InvMovings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne()
    @JoinColumn(name = "fromexcelid")
    private FromExcelData fromExcelData;
    @Column(name = "MOL")
    private String mol;
    @Column(name = "codedepartment")
    private String codeDepartment;
    @Column(name = "namedepartment")
    private String nameDepartment;

    public InvMovings() {
    }

    public InvMovings(String mol, DepartmentDTO departmentByCode, FromExcelData fromExcelData) {
        this.codeDepartment = departmentByCode.getCodeDepartment();
        this.nameDepartment = departmentByCode.getNameDepartment();
        this.mol = mol;
        this.fromExcelData = fromExcelData;
    }

    public String getMol() {
        return mol;
    }

    public void setMol(String mol) {
        this.mol = mol;
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

    public FromExcelData getFromExcelData() {
        return fromExcelData;
    }

    public void setFromExcelData(FromExcelData fromExcelData) {
        this.fromExcelData = fromExcelData;
    }

    public Long getId() {
        return  id;
    }
}
