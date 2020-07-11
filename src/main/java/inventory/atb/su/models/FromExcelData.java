package inventory.atb.su.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "fromexcel")
public class FromExcelData {
    //    1	Наименование
    //  2	Инв. №
    //  3	Кол -во
    //  4	Ед. из мер
    //  5	Цена
    //  11	Тип ТМЦ
    //  12	Вид испо льзов
    //  13	Код группы
    //  14	Наимен группы
    //  15	МОЛ
    //  16	Код подр.
    //  17	Подраз деление
    //  18	Место нахож дение
    //  22	Вне сист учет
    //  23	Дата соз дания
    //  24	Дата опри ходов
    //  25	Дата ввода в экспл
    //  26	Дата списания
    //  47	Старый инв. №
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "invnumber")
    private String invNumber;
    @Column(name = "quantity")
    private Integer quantity;
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
    @Column(name = "codedepartment")
    private String codeDepartment;
    @Column(name = "namedepartment")
    private String nameDepartment;
    @Column(name = "locations")
    private String locations;
    @Column(name = "nonsystem")
    private Boolean nonSystem;
    @Column(name = "datecreation")
    private Date dateCreation;
    @Column(name = "datepostings")
    private Date datePostings;
    @Column(name = "dateexploit")
    private Date dateExploit;
    @Column(name = "dateclosin")
    private Date dateClosin;
    @Column(name = "oldinvnumber")
    private String oldInvNumber;

    protected FromExcelData() {
    }

    public FromExcelData(String name, String invNumber, Integer quantity, String unit,
                         Double price, String typeTMC, String typeUtilize,
                         String codeGroup, String nameGroup, String mol, String codeDepartment,
                         String nameDepartment, String locations, Boolean nonSystem, Date dateCreation,
                         Date datePostings, Date dateExploit, Date dateClosin, String oldInvNumber) {
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
        this.codeDepartment = codeDepartment;
        this.nameDepartment = nameDepartment;
        this.locations = locations;
        this.nonSystem = nonSystem;
        this.dateCreation = dateCreation;
        this.datePostings = datePostings;
        this.dateExploit = dateExploit;
        this.dateClosin = dateClosin;
        this.oldInvNumber = oldInvNumber;
    }

    public FromExcelData(String name, String invNumber, double quantity, String unit,
                         double price, String typeTMC, String typeUtilize,
                         String codeGroup, String nameGroup, String mol, String codeDepartment,
                         String nameDepartment, String locations, String nonSystem, java.util.Date dateCreation,
                         java.util.Date datePostings, java.util.Date dateExploit, java.util.Date dateClosin, String oldInvNumber) {
        this.name = name;
        this.invNumber = invNumber;
        this.quantity = Double.valueOf(quantity).intValue();
        this.unit = unit;
        this.price = price;
        this.typeTMC = typeTMC;
        this.typeUtilize = typeUtilize;
        this.codeGroup = codeGroup;
        this.nameGroup = nameGroup;
        this.mol = mol;
        this.codeDepartment = codeDepartment;
        this.nameDepartment = nameDepartment;
        this.locations = locations;
        this.nonSystem = nonSystem.toUpperCase().equals("ДА") ? true : false;
        this.dateCreation = dateCreation != null ? new Date(dateCreation.getTime()): null;
        this.datePostings = datePostings != null ? new Date(datePostings.getTime()): null;
        this.dateExploit = dateExploit != null ? new Date(dateExploit.getTime()): null;
        this.dateClosin = dateClosin != null ? new Date( dateClosin.getTime()) : null;
        this.oldInvNumber = oldInvNumber;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public Boolean getNonSystem() {
        return nonSystem;
    }

    public void setNonSystem(Boolean nonSystem) {
        this.nonSystem = nonSystem;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDatePostings() {
        return datePostings;
    }

    public void setDatePostings(Date datePostings) {
        this.datePostings = datePostings;
    }

    public Date getDateExploit() {
        return dateExploit;
    }

    public void setDateExploit(Date dateExploit) {
        this.dateExploit = dateExploit;
    }

    public Date getDateClosin() {
        return dateClosin;
    }

    public void setDateClosin(Date dateClosin) {
        this.dateClosin = dateClosin;
    }

    public String getOldInvNumber() {
        return oldInvNumber;
    }

    public void setOldInvNumber(String oldInvNumber) {
        this.oldInvNumber = oldInvNumber;
    }
}
