package com.bd.childinfo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@AnalyzerDef(name = "childAtRiskSearchAnalyzer",
  tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
  filters = {
    @TokenFilterDef(factory = LowerCaseFilterFactory.class),
    @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
      @Parameter(name = "language", value = "English")
    })
  })
@Indexed
@JsonIgnoreProperties
@Entity
public class Child extends BaseEntity<Long> implements NonDeletable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Field
	@Analyzer(definition = "childAtRiskSearchAnalyzer")
    @NotNull
    @Size(max = 100)
    private String name;
	
	@Size(max = 100)
	private String motherName;
	 
	@Size(max = 100)
	private String fatherName;
	 
	@Size(max = 100)
	private String specialRemark;
	
	@Size(max = 100)
	private String gender;
	
	@Size(max = 100)
	private String education;
	
	@Size(max = 100)
	private String age;
	
	@Size(max = 100)
	private String height;
	
	@Size(max = 100)
	private String skin;
	
	@Size(max = 100)
	private String health;
	
	@Size(max = 100)
    private String contactPerson;

    @Size(max = 100)
    private String phone;

    @Size(max = 100)
    private String email;
    
    @Field
    @Analyzer(definition = "childAtRiskSearchAnalyzer")
    @NotNull
    @Size(max = 500)
    private String catchPoint;

    @Field
    @Analyzer(definition = "childAtRiskSearchAnalyzer")
    @NotNull
    @Size(max = 500)
    private String address;
    
    @Field
    @Analyzer(definition = "childAtRiskSearchAnalyzer")
    @NotNull
    @Size(max = 500)
    private String thana;
    
    @Field
    @Analyzer(definition = "childAtRiskSearchAnalyzer")
    @NotNull
    @Size(max = 500)
    private String distict;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "risk_id", unique = true, nullable = false, insertable = true, updatable = true)
    private RiskType riskType;

    //@Lob
    //@Column(length = 1000000)
    private byte[] image;
    
    private Boolean deleted = Boolean.FALSE; // by default its false 

    public Child() {
    }
   
	public Child(String name, String motherName, String fatherName) {
		super();
		this.name = name;
		this.motherName = motherName;
		this.fatherName = fatherName;
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

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getSpecialRemark() {
		return specialRemark;
	}

	public void setSpecialRemark(String specialRemark) {
		this.specialRemark = specialRemark;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getCatchPoint() {
		return catchPoint;
	}

	public void setCatchPoint(String catchPoint) {
		this.catchPoint = catchPoint;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getThana() {
		return thana;
	}

	public void setThana(String thana) {
		this.thana = thana;
	}

	public String getDistict() {
		return distict;
	}

	public void setDistict(String distict) {
		this.distict = distict;
	}
	
	public RiskType getRiskType() {
		return riskType;
	}

	public void setRiskType(RiskType riskType) {
		this.riskType = riskType;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
