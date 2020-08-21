package inventory.atb.su.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvNumber() {
        return invNumber;
    }

    public void setInvNumber(String invNumber) {
        this.invNumber = invNumber;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTypeTMC() {
        return typeTMC;
    }

    public void setTypeTMC(String typeTMC) {
        this.typeTMC = typeTMC;
    }

    public String getTypeUtilize() {
        return typeUtilize;
    }

    public void setTypeUtilize(String typeUtilize) {
        this.typeUtilize = typeUtilize;
    }

    public String getCodeGroup() {
        return codeGroup;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getMol() {
        return mol;
    }

    public void setMol(String mol) {
        this.mol = mol;
    }

    public String getMolCodeDepartment() {
        return molCodeDepartment;
    }

    public void setMolCodeDepartment(String molCodeDepartment) {
        this.molCodeDepartment = molCodeDepartment;
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

    public String getNonSystem() {
        return nonSystem;
    }

    public void setNonSystem(String nonSystem) {
        this.nonSystem = nonSystem;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDatePostings() {
        return datePostings;
    }

    public void setDatePostings(LocalDate datePostings) {
        this.datePostings = datePostings;
    }

    public LocalDate getDateExploit() {
        return dateExploit;
    }

    public void setDateExploit(LocalDate dateExploit) {
        this.dateExploit = dateExploit;
    }

    public LocalDate getDateClosin() {
        return dateClosin;
    }

    public void setDateClosin(LocalDate dateClosin) {
        this.dateClosin = dateClosin;
    }

    public InvMovings getInvMovings() {
        return invMovings;
    }

    public void setInvMovings(InvMovings invMovings) {
        this.invMovings = invMovings;
    }
}
