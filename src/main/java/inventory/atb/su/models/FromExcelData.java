package inventory.atb.su.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "fromexcel")
public class FromExcelData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "invnumber")
    private String invNumber;
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "unit")
    private String unit;
    @Column(name = "price")
    private Double price;
    @Column(name = "typetmc")
    private String typeTMC;
    @Column(name = "typeutilize")
    private String typeUtilize;
    @Column(name = "codegroup")
    private String codeGroup;
    @Column(name = "namegroup")
    private String nameGroup;
    @Column(name = "MOL")
    private String mol;
    @Column(name = "molcodedepartment")
    private String molCodeDepartment;
    @Column(name = "codedepartment")
    private String codeDepartment;
    @Column(name = "namedepartment")
    private String nameDepartment;
    @Column(name = "nonsystem")
    private String nonSystem;
    @Column(name = "datecreation")
    private LocalDate dateCreation;
    @Column(name = "datepostings")
    private LocalDate datePostings;
    @Column(name = "dateexploit")
    private LocalDate dateExploit;
    @Column(name = "dateclosin")
    private LocalDate dateClosin;
    @OneToOne(mappedBy = "fromExcelData", cascade = CascadeType.ALL)
    private InvMovings invMovings;

    public FromExcelData() {
    }

    public FromExcelData(String name, String invNumber, double quantity, String unit,
                         double price, String typeTMC, String typeUtilize,
                         String codeGroup, String nameGroup, String mol, String molCodeDepartment, String codeDepartment,
                         String nameDepartment, String nonSystem, Date dateCreation,
                         Date datePostings, Date dateExploit, Date dateClosin) {
        this.name = name;
        this.invNumber = invNumber;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.typeTMC = typeTMC;
        this.typeUtilize = typeUtilize;
        this.codeGroup = codeGroup;
        this.nameGroup = nameGroup;
        this.mol = mol;
        this.molCodeDepartment = molCodeDepartment;
        this.codeDepartment = codeDepartment;
        this.nameDepartment = nameDepartment;
        this.nonSystem = nonSystem;
        this.dateCreation = dateCreation != null ? new java.sql.Date(dateCreation.getTime()).toLocalDate() : null;
        this.datePostings = datePostings != null ? new java.sql.Date(datePostings.getTime()).toLocalDate() : null;
        this.dateExploit = dateExploit != null ? new java.sql.Date(dateExploit.getTime()).toLocalDate() : null;
        this.dateClosin = dateClosin != null ? new java.sql.Date(dateClosin.getTime()).toLocalDate() : null;
        this.invMovings = null;
    }

}
