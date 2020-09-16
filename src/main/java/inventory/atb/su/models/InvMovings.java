package inventory.atb.su.models;

import inventory.atb.su.models.dto.DepartmentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
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

}
