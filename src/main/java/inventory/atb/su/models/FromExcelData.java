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
    private Long id;
    private String name;
    private String inv_number;
    private Integer quantity;
    private String unit;
    private Double price;
    private String type_TMC;
    private String type_utilize;
    private String code_group;
    private String name_group;
    private String MOL;
    private String code_department;
    private String name_department;
    private String locations;
    private Boolean non_system;
    private Date date_creation;
    private Date date_postings;
    private Date date_exploit;
    private Date date_closin;
    private String old_inv_number;

    protected FromExcelData() {
    }

    public FromExcelData(String name, String inv_number, Integer quantity, String unit,
                         Double price, String type_TMC, String type_utilize,
                         String code_group, String name_group, String MOL, String code_department,
                         String name_department, String locations, Boolean non_system, Date date_creation,
                         Date date_postings, Date date_exploit, Date date_closin, String old_inv_number) {
        this.name = name;
        this.inv_number = inv_number;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.type_TMC = type_TMC;
        this.type_utilize = type_utilize;
        this.code_group = code_group;
        this.name_group = name_group;
        this.MOL = MOL;
        this.code_department = code_department;
        this.name_department = name_department;
        this.locations = locations;
        this.non_system = non_system;
        this.date_creation = date_creation;
        this.date_postings = date_postings;
        this.date_exploit = date_exploit;
        this.date_closin = date_closin;
        this.old_inv_number = old_inv_number;
    }

//    public FromExcelData(String name, String inv_number, double quantity, String unit,
//                         double price, String type_TMC, String type_utilize,
//                         String code_group, String name_group, String MOL, String code_department,
//                         String name_department, String locations, String non_system, java.util.Date date_creation,
//                         java.util.Date date_postings, java.util.Date date_exploit, java.util.Date date_closin, String old_inv_number) {
//
//    }

    public FromExcelData(String name, String inv_number, double quantity, String unit,
                         double price, String type_TMC, String type_utilize,
                         String code_group, String name_group, String MOL, String code_department,
                         String name_department, String locations, String non_system, java.util.Date date_creation,
                         java.util.Date date_postings, java.util.Date date_exploit, java.util.Date date_closin, String old_inv_number) {
        this.name = name;
        this.inv_number = inv_number;
        this.quantity = Double.valueOf(quantity).intValue();
        this.unit = unit;
        this.price = price;
        this.type_TMC = type_TMC;
        this.type_utilize = type_utilize;
        this.code_group = code_group;
        this.name_group = name_group;
        this.MOL = MOL;
        this.code_department = code_department;
        this.name_department = name_department;
        this.locations = locations;
        this.non_system = non_system.toUpperCase().equals("ДА") ? true : false;
        this.date_creation = date_creation!= null ? new Date(date_creation.getTime()): null;
        this.date_postings = date_postings!= null ? new Date(date_postings.getTime()): null;
        this.date_exploit = date_exploit!= null ? new Date(date_exploit.getTime()): null;
        this.date_closin = date_closin!= null ? new Date( date_closin.getTime()) : null;
        this.old_inv_number = old_inv_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInv_number() {
        return inv_number;
    }

    public void setInv_number(String inv_number) {
        this.inv_number = inv_number;
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

    public String getType_TMC() {
        return type_TMC;
    }

    public void setType_TMC(String type_TMC) {
        this.type_TMC = type_TMC;
    }

    public String getType_utilize() {
        return type_utilize;
    }

    public void setType_utilize(String type_utilize) {
        this.type_utilize = type_utilize;
    }

    public String getCode_group() {
        return code_group;
    }

    public void setCode_group(String code_group) {
        this.code_group = code_group;
    }

    public String getName_group() {
        return name_group;
    }

    public void setName_group(String name_group) {
        this.name_group = name_group;
    }

    public String getMOL() {
        return MOL;
    }

    public void setMOL(String MOL) {
        this.MOL = MOL;
    }

    public String getCode_department() {
        return code_department;
    }

    public void setCode_department(String code_department) {
        this.code_department = code_department;
    }

    public String getName_department() {
        return name_department;
    }

    public void setName_department(String name_department) {
        this.name_department = name_department;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public Boolean getNon_system() {
        return non_system;
    }

    public void setNon_system(Boolean non_system) {
        this.non_system = non_system;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_postings() {
        return date_postings;
    }

    public void setDate_postings(Date date_postings) {
        this.date_postings = date_postings;
    }

    public Date getDate_exploit() {
        return date_exploit;
    }

    public void setDate_exploit(Date date_exploit) {
        this.date_exploit = date_exploit;
    }

    public Date getDate_closin() {
        return date_closin;
    }

    public void setDate_closin(Date date_closin) {
        this.date_closin = date_closin;
    }

    public String getOld_inv_number() {
        return old_inv_number;
    }

    public void setOld_inv_number(String old_inv_number) {
        this.old_inv_number = old_inv_number;
    }
}
